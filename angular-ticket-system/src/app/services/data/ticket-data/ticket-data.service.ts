import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ticket } from '../../../interfaces/ticket';
import { CustomDataService } from '../custom-data.service';

export const TICKET_URL = "http://localhost:8080/api/tickets";

@Injectable({
  providedIn: 'root'
})
export class TicketDataService extends CustomDataService<Ticket> {

  constructor(http: HttpClient) {
    super(http);
  }
  findDatas(): Observable<Ticket[]> {
    
    return this.http.get<Ticket[]>(TICKET_URL, { headers: this.headers });
  }
  findDatasByAssignee(assignee : string): Observable<Ticket[]> {
    
    return this.http.get<Ticket[]>(TICKET_URL+"?assignedTo="+assignee, { headers: this.headers });
  }
  findData(id: string): Observable<Ticket> {
    return this.http.get<Ticket>(TICKET_URL+"/"+id, { headers: this.headers });
  }
  addData(entity: Ticket): Observable<Ticket> {
    
    return this.http.post<Ticket>(TICKET_URL, new Ticket(null, entity.title, entity.description, entity.status, entity.createdAt, entity.assignedTo, entity.createdBy), { headers: this.headers });
  }
  updateData(entity: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(TICKET_URL, new Ticket(null, entity.title, entity.description, entity.status, entity.createdAt, entity.assignedTo, entity.createdBy), { headers: this.headers });
  }
  deleteData(id: string): Observable<Ticket> {
    return this.http.delete<Ticket>(TICKET_URL+"/"+id, { headers: this.headers });
  }
  assignTo(ticketId:string, userId:string) : Observable<any> {
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/assignTo?userId="+userId, { headers: this.headers });
  }
  accept(ticketId: string) : Observable<any>{
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/accept", { headers: this.headers });
  }
  decline(ticketId: string) : Observable<any>{
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/decline", { headers: this.headers });
  }
  start(ticketId: string) : Observable<any>{
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/start", { headers: this.headers });
  }
  restart(ticketId: string) : Observable<any>{
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/restart", { headers: this.headers });
  }
  close(ticketId: string) : Observable<any>{
    return this.http.get(TICKET_URL+"/"+ticketId+"/close", { headers: this.headers });
  }
  block(ticketId: string): Observable<any>{
    return this.http.get<boolean>(TICKET_URL+"/"+ticketId+"/block", { headers: this.headers });
  }
}
