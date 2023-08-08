import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../interfaces/user';
import { CustomDataService } from '../custom-data.service';

export const USER_URL = "http://localhost:8080/api/users";

@Injectable({
  providedIn: 'root'
})
export class UserDataService extends CustomDataService<User>{

  constructor(http: HttpClient) {
    super(http);
  }
  findDatas(): Observable<User[]> {
    
    return this.http.get<User[]>(USER_URL, { headers: this.headers });
  }
  /*searchDatas(assignee : number): Observable<User[]> {
    
    return this.http.get<User[]>("http://localhost:8082/users?assignedTo="+assignee, { headers: this.headers });
  }*/
  findData(id: string): Observable<User> {
    return this.http.get<User>(USER_URL+"/"+id, { headers: this.headers });
  }

  findDataByUsername(username: string): Observable<User> {
    return this.http.get<User>(USER_URL+"/search?username="+username, { headers: this.headers });
  }

  addData(entity: User): Observable<User> {
    
    return this.http.post<User>(USER_URL, new User(entity.id, entity.username, entity.email, entity.password), { headers: this.headers });
  }
  updateData(entity: User): Observable<User> {
    return this.http.post<User>(USER_URL, new User(entity.id, entity.username, entity.email, entity.password), { headers: this.headers });
  }
  deleteData(id: string): Observable<User> {
    return this.http.delete<User>(USER_URL+"/"+id, { headers: this.headers });
  }
}
