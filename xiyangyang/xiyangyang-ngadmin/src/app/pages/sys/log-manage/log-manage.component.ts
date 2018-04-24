import { LogService } from './../log.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-manage',
  templateUrl: './log-manage.component.html',
  styleUrls: ['./log-manage.component.css']
})
export class LogManageComponent implements OnInit {

  constructor(private service: LogService) { }

  ngOnInit() {
  }

}
