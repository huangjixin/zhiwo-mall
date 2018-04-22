import { PropertyService } from './../property.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-manage',
  templateUrl: './property-manage.component.html',
  styleUrls: ['./property-manage.component.css']
})
export class PropertyManageComponent implements OnInit {

  constructor(private service: PropertyService) { }

  ngOnInit() {
  }

}
