import { ResourcesService } from './../resources.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-resources-manage',
  templateUrl: './resources-manage.component.html',
  styleUrls: ['./resources-manage.component.css']
})
export class ResourcesManageComponent implements OnInit {

  constructor(private service: ResourcesService) { }

  ngOnInit() {
  }

}
