import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export abstract class CustomDataService<T> {

  protected headers = new HttpHeaders();
    
  dialogData: any;
  getDialogData() {
    return this.dialogData;
  }
  constructor( protected http: HttpClient,
    /*private communicationManagerService: CommunicationManagerService*/){
      this.headers.set('Access-Control-Allow-Origin', '*');
      this.headers.append("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Access-Control-Allow-Headers, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
      this.headers.append('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, PATCH, OPTIONS');
  }
  
  abstract findDatas(): Observable<T[]>;
  abstract findData(id: string): Observable<T>;
  abstract addData(entity: T): Observable<T>;
  abstract updateData(entity: T): Observable<T>;
  abstract deleteData(id: string): Observable<T>;
}
