import { ShopService } from './../shop.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shop-manage',
  templateUrl: './shop-manage.component.html',
  styleUrls: ['./shop-manage.component.css']
})
export class ShopManageComponent implements OnInit {

  constructor(private service: ShopService) { }

  ngOnInit() {
  }

}
