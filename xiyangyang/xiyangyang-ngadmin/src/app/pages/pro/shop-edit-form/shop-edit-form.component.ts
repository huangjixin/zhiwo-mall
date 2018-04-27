import {Component, OnInit} from '@angular/core';
import { Location } from '@angular/common';
import { Shop } from './../shop.model';
import {ShopService} from './../shop.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-shop-edit-form',
  templateUrl: './shop-edit-form.component.html',
  styleUrls: ['./shop-edit-form.component.css']
})
export class ShopEditFormComponent implements OnInit {

  // ID
  public id: String;
  public shop: Shop;
  public shopFormGroup: FormGroup;
  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private shopService: ShopService) {
  }
// 初始化取ID值。
  ngOnInit() {
    this.shop = new Shop();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const shop: Shop =  this.shopService.getDataById(this.id);
      if ( shop !== null ) {
        this.shop = shop;
      }
    }
  }
  // 返回上一级目录。
  back(): void {
    this.location.back();
  }

  submitForm() {
    alert(JSON.stringify(this.shop));
  }
}
