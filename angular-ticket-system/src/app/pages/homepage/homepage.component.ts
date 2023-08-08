import { Component, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

export interface CardLayout {
  columns: number;
  homepage: { cols: number, rows: number };
}

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  cardLayout: Observable<CardLayout> = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          homepage: { cols: 1, rows: 1 },
        };
      }

      return {
        columns: 4,
        homepage: { cols: 4, rows: 1 },
      };
    })
  );

  constructor(public breakpointObserver : BreakpointObserver) { }

}
