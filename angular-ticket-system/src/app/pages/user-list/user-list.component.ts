import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { TableColumn } from '../../components/custom-table/table-column';
import { map, shareReplay, tap } from 'rxjs/operators';
import { UserDataService } from '../../services/data/user-data/user-data.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { UserDialogComponent } from '../../components/dialogs/user-dialog/user-dialog.component';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { User } from 'src/app/interfaces/user';

export interface CardLayout {
  columns: number;
  users: { cols: number, rows: number };
}

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  users: MatTableDataSource<User> = new MatTableDataSource<User>();
  usersTableColumns: TableColumn[]; 

  cardLayout: Observable<CardLayout> = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          users: { cols: 1, rows: 1 },
        };
      }

      return {
        columns: 4,
        users: { cols: 1, rows: 1 },
      };
    })
  );

  constructor( public breakpointObserver : BreakpointObserver, 
    public _snackBar : MatSnackBar,
    public dialog: MatDialog,
    public dataService: UserDataService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadData();
    this.initColumns();
  }

  refresh(): void{
    this.loadData();
  }

  loadData(): void{
    this.getUsers();
  }

  initColumns(): void {
    this.usersTableColumns = [
      {
        name: 'Id',
        dataKey: 'id'
      },
      {
        name: 'First Name',
        dataKey: 'firstName'
      },
      {
        name: 'Last Name',
        dataKey: 'lastName'
      },
      {
        name: 'Email',
        dataKey: 'email'
      }
    ];
  }

  openDialogUser(action : String , obj : any) {
    let obj2 = obj;
    obj2.action = action;
    const dialogRef = this.dialog.open(UserDialogComponent, {
      width: '250px',
      data:obj2
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result.event == 'Add'){
        this.addUser(result.data);
      }else if(result.event == 'Update'){
        this.updateUser(result.data);
      }else if(result.event == 'Delete'){
        this.removeUser(result.data);
      }
    });
  }

  getUsers(): void {
    this.dataService.findDatas().subscribe(users => {
      this.users.data = users as User[]
    });
  }

  addUser(row_obj: any){
    this.dataService.addData(row_obj).subscribe(() => { this.refresh();});
  }

  updateUser(row_obj :any){
    this.dataService.updateData(row_obj).subscribe(() => { this.refresh();});    
  }

  removeUser(row_obj: any){
    this.dataService.deleteData(row_obj.id).subscribe(() => { this.refresh();});
  }


  goToUser(row_obj: any){
    this.router.navigate(['user', row_obj.id]);
  }
}
