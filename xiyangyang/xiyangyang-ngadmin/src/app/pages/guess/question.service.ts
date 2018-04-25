import { GuessQuestion } from './guess-question.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/of';

@Injectable()
export class QuestionService {

  constructor() { }

  getData(): any {
           const total = 200;
            let data = [];
           for (let i: number = 1; i <= total; i++) {
               const question: GuessQuestion = new GuessQuestion();
               question.id = '' + i;
               question.name = '竞猜问题' + i;
               question.questionEndTime = new Date();
               data.push(question);
            }
            return Observable.of({
                total: total,
                rows: data
            });
        }

  getDataById(id: String): any {
      const question: GuessQuestion = new GuessQuestion();
      question.id = id;
      question.name = '竞猜问题' + id;
      question.questionEndTime = new Date();
      return question;
  }
}
