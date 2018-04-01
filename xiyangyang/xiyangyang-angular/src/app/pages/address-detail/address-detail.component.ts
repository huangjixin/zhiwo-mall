import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {ActivatedRoute, Params, Router } from  '@angular/router';
import {MemAddress} from '../address/MemAddress';
import {GuessQuestionEntity} from '../guess/GuessQuestionEntity';
import {globalConfig} from '../../global.config';

@Component({
  selector: 'app-address-detail',
  templateUrl: './address-detail.component.html',
  styleUrls: ['./address-detail.component.css']
})
export class AddressDetailComponent implements OnInit {
  private addressId: string;
  private address: MemAddress;
  private dataUrl: string;

  constructor(private router: Router, public activeRouter: ActivatedRoute, private location: Location) {
    // console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }

  ngOnInit() {
    this.address = new MemAddress();
    this.dataUrl = globalConfig.dataUrl;

    this.activeRouter.queryParams.subscribe(params => {
      this.addressId = params['id'];
    });

    if (this.addressId !== null) {
      const url : string = this.dataUrl + `maddress/view/${this.addressId}`;
      this.getAddressById(url);
    }
  }

  getAddressById(url: string): void {
    fetch(url, {
      method: 'get'
    }).then(response => response.json())
      .then(response => {
        this.address = response;
        console.log(JSON.stringify(this.address));
      }).catch(function(err) {
    });
  }

  //返回地址列表
  redirectToAddressList() {
    this.location.back();
    // this.router.navigate([]);
  }
}
