import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {globalConfig} from '../../global.config';
import {InfiniteLoaderComponent} from 'ngx-weui/infiniteloader';
import {MemAddress} from './MemAddress';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
  private pageNum: number  = 1;
  private pageSize: number = 20;
  private allDataLoaded; boolean;

  public memAddresses: MemAddress[] = [];
  private mememberId: string = '1';
  private methodUrl: string = 'appguess/maddress/';

  constructor( private route: ActivatedRoute,
               private router: Router,
               private location: Location) {

  }

  ngOnInit() {
    const dataUrl : string = globalConfig.dataUrl;
    const url: string = dataUrl +  `${this.methodUrl}${this.mememberId}?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

  private loadData(url: string ): void {
    fetch(url, {
      method: 'get'
    }).then(response => response.json())
      .then(response => {
        const length: number = response.length;
        if (length === 0) {
          this.allDataLoaded = true;
        }
        for (let i: number = 0; i < length; i++) {
          const mem: MemAddress = response[i];
          this.memAddresses.push(mem);
        }
      }).catch(function(err) {
    });
  }

  onLoadMore(comp: InfiniteLoaderComponent) {
    if (this.allDataLoaded === true) {
      comp.setFinished();
      return;
    }
    comp.resolveLoading();
    this.pageNum += 1;
    const dataUrl: string = globalConfig.dataUrl;
    const url: string = dataUrl + `${this.methodUrl}${this.mememberId}?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    console.log(url);
    this.loadData(url);
  }

  public redirectToDetail(address: MemAddress): void {
    /*let heroId = hero ? hero.id : null;
    // Pass along the hero id if available
    // so that the HeroList component can select that hero.
    // Include a junk 'foo' property for fun.
    this.router.navigate(['/address-detail', { id: heroId, foo: 'foo' }]);*/
  }

  //返回地址列表
  redirectToUesrInfo() {
    this.location.back();
    // this.router.navigate([]);
  }

}
