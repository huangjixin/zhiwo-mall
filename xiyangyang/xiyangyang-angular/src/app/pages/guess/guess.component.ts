import {Component, OnInit, ViewEncapsulation, ViewChild} from '@angular/core';
import { PopupComponent } from "ngx-weui/popup";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';

import { InfiniteLoaderComponent } from 'ngx-weui/infiniteloader';
import {GuessQuestionEntity} from './GuessQuestionEntity';

@Component({
  selector: 'app-guess',
  templateUrl: './guess.component.html',
  styleUrls: ['./guess.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GuessComponent implements OnInit {
  private pageNum: number  = 1;
  private pageSize: number = 4;
  private allDataLoaded; boolean;

  public guessQuestions: GuessQuestionEntity[] = [];

  @ViewChild('simple')
  simplePopup: PopupComponent;

  constructor() { }

  ngOnInit() {
    // this.guessQuestions = [];
    const url: string = `http://localhost:8080/appguess?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

  private loadData(url: string ): void {
    fetch(url, {
      method: 'get'
    }).then(response => response.json())
      .then(response => {
        const length: number = response.length;
        for (let i: number = 0; i < length; i++) {
          const guessEntity: GuessQuestionEntity = response[i];
          this.guessQuestions.push(guessEntity);
        }
      }).catch(function(err) {
    });
  }

  // items: any[] = Array(20).fill(0).map((v: any, i: number) => i);
  onLoadMore(comp: InfiniteLoaderComponent) {
    this.pageNum+=1;
    const url : string = `http://localhost:8080/appguess?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
    /*Observable.timer(1500).subscribe(() => {

      this.items.push(...Array(10).fill(this.items.length).map((v, i) => v + i));

      if (this.items.length >= 50) {
        comp.setFinished();
        return;
      }
      comp.resolveLoading();
    });*/
  }

}
