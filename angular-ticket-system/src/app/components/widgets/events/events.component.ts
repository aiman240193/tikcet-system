import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { WebsocketService } from 'src/app/services/websocket/websocket.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit{

  messages: any;

  constructor(private messageService: WebsocketService){
  }

  ngOnInit(): void {
    this.messageService.getMessages().subscribe(
      (response) => {
        this.messages = response;
      },
      (error) => {
        console.error('An error occurred:', error);
      }
    );
  }

  clear(){
    this.messages.length = 0;
  }
}
