import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private client: Client;

  constructor() {
    this.client = new Client();

    // Correctly assign webSocketFactory with SockJS
    this.client.webSocketFactory = () =>
      new SockJS('http://localhost:8080/chat-websocket') as any;
  }

  connect(onMessageReceived: (message: string) => void): void {
    this.client.onConnect = () => {
      this.client.subscribe('/topic/messages', (message) => {
        onMessageReceived(message.body);
      });
    };

    this.client.activate();
  }

  sendMessage(message: string): void {
    this.client.publish({ destination: '/app/sendMessage', body: message });
  }

  disconnect(): void {
    this.client.deactivate();
  }
}
