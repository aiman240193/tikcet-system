import { Component, Input } from '@angular/core';
import { ChartConfiguration, ChartOptions, ChartType } from 'chart.js';

@Component({
  selector: 'app-bar-charts',
  templateUrl: './bar-charts.component.html',
  styleUrls: ['./bar-charts.component.css']
})
export class BarChartsComponent {
  @Input() datas: { labels: string[], values: number[] };
  @Input() label: string;

  public barChartOptions: ChartOptions = {
    responsive: true,
  };

  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins: any = [];

  public chartColors: Array<any> = [
    { // all colors in order
      backgroundColor: ['#c0ffee']
    }
]

  public barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: ['Week1', 'Week2', 'Week3', 'Week4', 'Week5'],
    datasets: [
      { 
        data: [65, 59, 80, 81, 56], 
        label: 'Andamento chiusure',
        backgroundColor: ["lightskyblue"],
        hoverBackgroundColor: ["darkblue"] 
      }
    ]
  };

  constructor() { }

  /*ngOnInit() {
    this.barChartData.datasets[0].data = this.datas.values;
    this.barChartData.labels = this.datas.labels;
    this.barChartData.datasets[0].label = this.label;
  }

  ngOnChanges() {
    this.barChartData.datasets[0].data = this.datas.values;
    this.barChartData.labels = this.datas.labels;
    this.barChartData.datasets[0].label = this.label;
  }*/
}
