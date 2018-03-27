import {Component, OnInit, ViewEncapsulation, ViewChild} from '@angular/core';
import { PopupComponent } from "ngx-weui/popup";
import 'rxjs/add/observable/timer';

import { InfiniteLoaderComponent } from 'ngx-weui/infiniteloader';
import {GuessQuestionEntity} from './GuessQuestionEntity';
import {GuessOptionsEntity} from './GuessOptionsEntity';
import {globalConfig} from '../../global.config';

@Component({
  selector: 'app-guess',
  templateUrl: './guess.component.html',
  styleUrls: ['./guess.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GuessComponent implements OnInit{
  private pageNum: number  = 1;
  private pageSize: number = 20;
  private allDataLoaded; boolean;

  public guessQuestions: GuessQuestionEntity[] = [];
  public guessOptionsEntity: GuessOptionsEntity = new GuessOptionsEntity();

  public betValue: number = 100;

  @ViewChild('betOptionPopup')
  betOptionPopup: PopupComponent;

  constructor() { }

  ngOnInit() {
    const dataUrl : string = globalConfig.dataUrl;
    const url: string = dataUrl + `appguess?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
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
          const guessEntity: GuessQuestionEntity = response[i];
          this.guessQuestions.push(guessEntity);
        }
      }).catch(function(err) {
    });
  }

  onOptionClickHandler(guessOptionsEntity: GuessOptionsEntity) {
    this.guessOptionsEntity = guessOptionsEntity;
    this.betValue = 100;
    this.betOptionPopup.show();
  }

  onLoadMore(comp: InfiniteLoaderComponent) {
    if (this.allDataLoaded === true) {
      comp.setFinished();
      return;
    }
    comp.resolveLoading();
    this.pageNum+=1;
    const url : string = `http://localhost:8080/appguess?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

}
