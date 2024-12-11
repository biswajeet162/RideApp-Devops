import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../services/web-socket.service';

@Component({
    selector: 'app-chat',
    standalone: false,
    templateUrl: './chat.component.html',
    styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
    message = '';
    messages: string[] = [];

    constructor(private webSocketService: WebSocketService) {}

    ngOnInit(): void {
        this.webSocketService.connect((msg) => {
            this.messages.push(msg);
        });
    }

    sendMessage() {
        if (this.message.trim()) {
            this.webSocketService.sendMessage(this.message);
            this.message = '';
        }
    }
}
