import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { TableColumn } from '../../components/custom-table/table-column';
import { map, shareReplay, tap } from 'rxjs/operators';
import { TicketDataService } from '../../services/data/ticket-data/ticket-data.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { TicketDialogComponent } from '../../components/dialogs/ticket-dialog/ticket-dialog.component';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { CardComponent } from 'src/app/components/card/card.component';
import { CustomTableComponent } from 'src/app/components/custom-table/custom-table.component';

import { Ticket } from 'src/app/interfaces/ticket';
import { Statistics } from 'src/app/interfaces/statistics';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { Status } from 'src/app/interfaces/status';

export interface CardLayout {
  columns: number;
  tickets: { cols: number, rows: number };
}

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent {
  tickets: MatTableDataSource<Ticket> = new MatTableDataSource<Ticket>();
  ticketsTableColumns: TableColumn[]; 

  statistics: Statistics = {
    weeklyCompletions: {
      labels: [],
      values: []
    }
  }

  cardLayout: Observable<CardLayout> = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          tickets: { cols: 1, rows: 1 },
        };
      }

      return {
        columns: 4,
        tickets: { cols: 4, rows: 1 },
      };
    })
  );

  constructor( public breakpointObserver : BreakpointObserver, 
    public _snackBar : MatSnackBar,
    public dialog: MatDialog, public authService: AuthenticationService,
    public dataService: TicketDataService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadData();
    this.initColumns();
  }

  refresh(): void{
    this.loadData();
  }

  loadData(): void{
    this.getStatistics();
    this.getTickets();
  }

  initColumns(): void {
    this.ticketsTableColumns = [
      {
        name: 'Id',
        dataKey: 'id'
      },
      {
        name: 'Title',
        dataKey: 'title'
      },
      {
        name: 'Assigned To',
        dataKey: 'assignedTo'
      },
      {
        name: 'Status',
        dataKey: 'status'
      }
    ];
  }

  getStatistics(): void {
    //TODO
    /*this.statisticsDataService.findData(this.id).subscribe({

      next: statistics => {
        this.statistics.ticket_id = this.id;
        this.statistics.processPieOfBia = statistics.processPieOfBia;
      }

    });*/
  }

  openDialogTicket(action : String , obj : any) {
    let obj2 = obj;
    obj2.action = action;
    const dialogRef = this.dialog.open(TicketDialogComponent, {
      width: '250px',
      data:obj2
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result.event == 'Add'){
        const user = this.authService.getUser();
        result.data.createdBy= user.id;
        result.data.assignedTo= null;
        this.addTicket(result.data);
      }else if(result.event == 'Update'){
        this.updateTicket(result.data);
      }else if(result.event == 'Delete'){
        this.removeTicket(result.data);
      }
    });
  }

  getTickets(): void {
    this.dataService.findDatas().subscribe(tickets => {
      this.tickets.data = tickets as Ticket[]
    });
  }

  addTicket(row_obj: any){
    this.dataService.addData(row_obj).subscribe(() => { this.refresh();});
  }

  updateTicket(row_obj :any){
    this.dataService.updateData(row_obj).subscribe(() => { this.refresh();});    
  }

  removeTicket(row_obj: any){
    this.dataService.deleteData(row_obj.id).subscribe(() => { this.refresh();});
  }


  goToTicket(row_obj: any){
    this.router.navigate(['tickets', row_obj.id]);
  }
}
