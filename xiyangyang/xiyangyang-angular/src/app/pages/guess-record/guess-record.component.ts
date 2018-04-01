import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {MemGuessRecord} from './MemGuessRecord';
import {globalConfig} from '../../global.config';
import {InfiniteLoaderComponent} from 'ngx-weui/infiniteloader';

@Component({
  selector: 'app-guess-record',
  templateUrl: './guess-record.component.html',
  styleUrls: ['./guess-record.component.css']
})
export class GuessRecordComponent implements OnInit {
  private pageNum: number  = 1;
  private pageSize: number = 20;
  private allDataLoaded; boolean;

  public memGuessRecords: MemGuessRecord[] = [];

  constructor( private route: ActivatedRoute,
               private router: Router,
               private location: Location) {

  }

  ngOnInit() {
    const dataUrl : string = globalConfig.dataUrl;
    const url: string = dataUrl + `appguess/grecord/3?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
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
          const mem: MemGuessRecord = response[i];
          this.memGuessRecords.push(mem);
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
    const url: string = dataUrl + `memember/grecord/3?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

}
