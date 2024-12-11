import { Injectable } from '@angular/core';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: any | null = null; // Singleton instance
  private isConnected: boolean = false; // Track connection status

  connect(): Promise<void> {
    return new Promise((resolve, reject) => {
      if (this.isConnected) {
        console.log('WebSocket already connected................................');
        resolve(); // Already connected, resolve immediately
        return;
      }

      const socket = new SockJS('http://localhost:8082/ws'); // Update with your backend's base URL
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect(
        {},
        (frame: any) => {
          console.log('Connected to WebSocket:', frame);
          this.isConnected = true;
          resolve(); // Resolve the promise after connection
        },
        (error: any) => {
          console.error('WebSocket connection error:', error);
          reject(error); // Reject the promise on error
        }
      );
    });
  }

  sendRideRequest(rideRequest: any) {
    if (this.stompClient && this.isConnected) {
      this.stompClient.send('/app/user/request', {}, JSON.stringify(rideRequest));
    } else {
      console.error('WebSocket is not connected. Cannot send ride request.');
    }
  }

  subscribeToDriverNotifications(driverId: number, callback: (message: any) => void) {
    if (this.stompClient && this.isConnected) {
      const topic = `/topic/driver/${driverId}`;
      this.stompClient.subscribe(topic, (message: any) => {
        console.log(`Driver notification received for driverId ${driverId}:`, message);
        const decodedMessage = new TextDecoder().decode(message._binaryBody);
        const parsedMessage = JSON.parse(decodedMessage);
        callback(parsedMessage);
      });
    } else {
      console.error('WebSocket is not connected. Cannot subscribe to notifications.');
    }
  }

  subscribeToUserNotifications(userId: string, callback: (message: any) => void) {
    if (this.stompClient && this.isConnected) {
      this.stompClient.subscribe(`/topic/user/${userId}`, (message: any) => {
        callback(JSON.parse(message.body));
      });
    } else {
      console.error('WebSocket is not connected. Cannot subscribe to user notifications.');
    }
  }
}
