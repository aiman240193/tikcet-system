import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSortModule } from '@angular/material/sort';
import { DataPropertyGetterPipe } from './data-property-getter.pipe.ts.service';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { LayoutModule } from '@angular/cdk/layout';
import { NgChartsModule } from 'ng2-charts';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { TicketListComponent } from './pages/ticket-list/ticket-list.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { TicketDetailsComponent } from './pages/ticket-details/ticket-details.component';
import { CardComponent } from './components/card/card.component';
import { CustomTableComponent } from './components/custom-table/custom-table.component';
import { TicketDialogComponent } from './components/dialogs/ticket-dialog/ticket-dialog.component';
import { UserDialogComponent } from './components/dialogs/user-dialog/user-dialog.component';
import { BarChartsComponent } from './components/charts/bar-charts/bar-charts.component';
import { CommentsComponent } from './components/widgets/comments/comments.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { httpInterceptorProviders } from './services/authentication/http-interceptor.service';
import { EventsComponent } from './components/widgets/events/events.component';
@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    DataPropertyGetterPipe,
    TicketListComponent,
    UserListComponent,
    TicketDetailsComponent,
    CardComponent,
    CustomTableComponent,
    TicketDialogComponent,
    UserDialogComponent,
    BarChartsComponent,
    CommentsComponent,
    LoginComponent,
    RegistrationComponent,
    EventsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatFormFieldModule,
    MatSortModule,
    MatIconModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatDialogModule,
    MatSnackBarModule,
    FormsModule,
    MatInputModule,
    MatGridListModule,
    MatMenuModule,
    MatButtonModule,
    LayoutModule,
    NgChartsModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatSelectModule
  ],
  providers: [
    httpInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
