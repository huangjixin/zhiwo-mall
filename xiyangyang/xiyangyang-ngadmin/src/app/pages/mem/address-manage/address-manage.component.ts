import { AddressService } from './../address.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-address-manage',
  templateUrl: './address-manage.component.html',
  styleUrls: ['./address-manage.component.css']
})
export class AddressManageComponent implements OnInit {

  constructor(private service: AddressService) { }

  ngOnInit() {
  }

}
