import {Component, OnInit, ViewEncapsulation, ViewChild} from '@angular/core';
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
  private pageNum:number  = 1;
  private pageSize:number = 2;
  private allDataLoaded;boolean;

  public guessQuestions:any[] = [];

  constructor() { }

  ngOnInit() {
    // this.guessQuestions = [];
    const url : string = `http://localhost:8080/appguess?pageNum=${this.pageNum}&pageSize=${this.pageSize}`;
    this.loadData(url);
  }

  private loadData(url: string ): void {
    fetch(url, {
      method: 'get'
    }).then(response => response.json())
      .then(function(response) {
        for (let i:number=0;i<response.length;i++){
            let  guessEntity:GuessQuestionEntity = response[i];
            this.guessQuestions.push(guessEntity);
            console.log(guessEntity);
        }

        console.log(this.guessQuestions.length);
        /*this.guessQuestions.concat(response);
        c
        console.log(JSON.stringify(response));*/
      }).catch(function(err) {
      // 出错了;等价于 then 的第二个参数,但这样更好用更直观 :(
    });
  }

  items: any[] = Array(20).fill(0).map((v: any, i: number) => i);
  onLoadMore(comp: InfiniteLoaderComponent) {
    Observable.timer(1500).subscribe(() => {

      this.items.push(...Array(10).fill(this.items.length).map((v, i) => v + i));

      if (this.items.length >= 50) {
        comp.setFinished();
        return;
      }
      comp.resolveLoading();
    });
  }

}
