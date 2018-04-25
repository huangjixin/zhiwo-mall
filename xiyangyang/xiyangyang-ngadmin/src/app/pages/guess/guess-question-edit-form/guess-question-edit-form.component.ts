import { GuessQuestion } from './../guess-question.model';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from './../question.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-guess-question-edit-form',
  templateUrl: './guess-question-edit-form.component.html',
  styleUrls: ['./guess-question-edit-form.component.css']
})
export class GuessQuestionEditFormComponent implements OnInit {
  // ID
  public id: String;
  public question: GuessQuestion;
  public questionFormGroup: FormGroup;

  constructor(public activeRoute: ActivatedRoute,
    private router: Router, private location: Location, private questionService: QuestionService) {
      // this.questionFormGroup = fb.group({
      //    'name': [null, Validators.required]
      //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.question = new GuessQuestion();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
       const question: GuessQuestion =  this.questionService.getDataById(this.id);
       if ( question !== null ) {
          this.question = question;
       }
    }
  }

  // 返回上一级目录。
  back(): void {
    this.location.back();
  }

  submitForm(value: any) {
    alert(JSON.stringify(value));
  }
}
