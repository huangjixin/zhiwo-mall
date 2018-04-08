import {Component, OnInit, ViewEncapsulation, ElementRef, AfterViewChecked, ViewChild} from '@angular/core';
import 'rxjs/add/observable/timer';

import { InfiniteLoaderComponent, PopupComponent } from 'ngx-weui';
import {GuessQuestionEntity} from './GuessQuestionEntity';
import {GuessOptionsEntity} from './GuessOptionsEntity';
import {globalConfig} from '../../global.config';

declare var $:any;

@Component({
  selector: 'app-guess',
  templateUrl: './guess.component.html',
  styleUrls: ['./guess.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GuessComponent implements OnInit{
  public loadmoreText: String = '正在加载';
  public isLoading: Boolean = false;
  public pageNum: number  = 0;
  public pageSize: number = 20;
  public allDataLoaded; Boolean;

  public guessQuestions: GuessQuestionEntity[] = [];
  public guessOptionsEntity: GuessOptionsEntity = new GuessOptionsEntity();

  public betValue: Number = 100;

  @ViewChild('scrollContainer')
  // 获取元素
  public scrollContainer: ElementRef;

  constructor() { }

  ngOnInit() {
    const dataUrl: string = globalConfig.dataUrl;
    const url: string = dataUrl + `appguess/gquestions?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
    $('.wrapper').navbarscroll();

  }

  public loadData(url: string ): void {
    fetch(url, {
      method: 'get'
    }).then(response => response.json())
      .then(response => {
        const length: Number = response.length;
        if (length === 0) {
          this.allDataLoaded = true;
        }
        for (let i: number = 0; i < length; i++) {
          const guessEntity: GuessQuestionEntity = response[i];
          this.guessQuestions.push(guessEntity);
        }
        this.isLoading = false;
      }).catch(function(err) {
      this.isLoading = false;
    });
  }

  onOptionClickHandler(guessOptionsEntity: GuessOptionsEntity) {
    this.guessOptionsEntity = guessOptionsEntity;
    this.betValue = 100;
    // this.betOptionPopup.show();
  }

  loadMore() {
    if (this.allDataLoaded === true) {
      this.isLoading = false;
      return;
    }
    this.isLoading = true;
    this.pageNum += 1;
    const dataUrl: string = globalConfig.dataUrl;
    const url: string = dataUrl + `appguess/gquestions?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

  n/*gAfterViewChecked() {
    console.log(this.scrollContainer.nativeElement.scrollTop + ',' + this.scrollContainer.nativeElement.scrollHeight + ',' + this.scrollContainer.nativeElement.offsetHeight);
    if ((this.scrollContainer.nativeElement.scrollTop + this.scrollContainer.nativeElement.clientHeight)  == this.scrollContainer.nativeElement.scrollHeight) {
      // 如果到底不了 调用接口加载数据，追加到数组中。
      console.log('到底了');
    }
  }*/

  /*public test($event) {
    console.log('通过(scroll)指令监听');
  }*/
}
