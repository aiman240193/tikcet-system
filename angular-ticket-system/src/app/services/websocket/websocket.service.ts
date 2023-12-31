import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { AppComponent } from 'src/app/app.component';
import { EventsComponent } from 'src/app/components/widgets/events/events.component';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  webSocketEndPoint: string = 'http://localhost:8080/ws';
  topic: string = "/topic/ticketEvents";
  stompClient: any;
  appComponent: EventsComponent;
  msg = [];
  constructor(){
      this._connect();
  }
  _connect() {
      console.log("Initialize WebSocket Connection");
      let ws = new SockJS(this.webSocketEndPoint);
      this.stompClient = Stomp.over(ws);
      const _this = this;
      _this.stompClient.connect({}, function (frame) {
          _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
              _this.onMessageReceived(sdkEvent);
          });
          //_this.stompClient.reconnect_delay = 2000;
      }, this.errorCallBack);
  };

  _disconnect() {
      if (this.stompClient !== null) {
          this.stompClient.disconnect();
      }
      console.log("Disconnected");
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
      console.log("errorCallBack -> " + error)
      setTimeout(() => {
          this._connect();
      }, 5000);
  }

/**
* Send message to sever via web socket
* @param {*} message 
*/
  _send(message) {
      console.log("calling logout api via web socket");
      this.stompClient.send("/app/hello", {}, JSON.stringify(message));
  }

  getMessages(): Observable<any[]>{
    return of(this.msg);
  }

  clearMessages(){
    this.msg = [];
  }

  onMessageReceived(message) {
      console.log("Message Recieved from Server :: " + message);
      this.msg.push(message.body)
  }
}
