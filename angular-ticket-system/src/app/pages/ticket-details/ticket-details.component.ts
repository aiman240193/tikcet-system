import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { CommentsComponent } from 'src/app/components/widgets/comments/comments.component';
import { Status } from 'src/app/interfaces/status';
import { Ticket } from 'src/app/interfaces/ticket';
import { User } from 'src/app/interfaces/user';
import { TicketDataService } from 'src/app/services/data/ticket-data/ticket-data.service';
import { UserDataService } from 'src/app/services/data/user-data/user-data.service';

export interface CardLayout {
  columns: number;
  tickets: { cols: number, rows: number };
}

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {
  id: string;
  ticket: Ticket;
  
  currentStatus: Status;
  assignees = new FormControl('');
  user: User;

  allStatus = Status;
  userList: string[] = ['User1', 'User2', 'User3', 'User4'];


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

  constructor(public breakpointObserver: BreakpointObserver,
    private router: Router, private route: ActivatedRoute,
    private ticketData: TicketDataService, private userData: UserDataService) { 
      this.ticket = new Ticket(null, null, null, null, null, null, null);
    }

  ngOnInit(): void {
    this.route.params.subscribe((params) => this.id = params['id']);
    this.ticketData.findData(this.id).subscribe({
      next: data => {
        this.ticket = data;
        if (data.status === Status.TO_BE_ASSIGNED)
          this.currentStatus = Status.TO_BE_ASSIGNED;
        if (data.status === Status.ASSIGNED)
          this.currentStatus = Status.ASSIGNED;
        if (data.status == Status.ACCEPTED)
          this.currentStatus = Status.ACCEPTED;
        if (data.status == Status.DECLINED)
          this.currentStatus = Status.DECLINED;
        if (data.status == Status.STARTED)
          this.currentStatus = Status.STARTED;
        if (data.status == Status.BLOCKED)
          this.currentStatus = Status.BLOCKED;
        if (data.status == Status.CLOSED)
          this.currentStatus = Status.CLOSED;
      },
      error: err => {
      }
    });
    this.userData.findDatas().subscribe({
      next: data => {
        this.userList = data.map(user => user.username);
      },
      error: err => {
      }
    })
  }

  assign() {
    this.userData.findDataByUsername(this.assignees.value).subscribe({
      next: data => {
        this.user = data;
        this.ticketData.assignTo(this.ticket.id, this.user.id).subscribe();
      },
      error: err => {
      }
    })
  }
  accept() {
    this.ticketData.accept(this.ticket.id).subscribe();
    this.refresh();
  }

  decline() {
    this.ticketData.decline(this.ticket.id).subscribe();
    this.refresh();
  }

  start() {
    this.ticketData.start(this.ticket.id).subscribe();
    this.refresh();
  }

  block() {
    this.ticketData.block(this.ticket.id).subscribe();
    this.refresh();
  }

  close() {
    this.ticketData.close(this.ticket.id).subscribe();
    this.refresh();
  }

  restart() {
    this.ticketData.restart(this.ticket.id).subscribe();
    this.refresh();
  }

  refresh(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigateByUrl('tickets/' + this.id);
  }
}
