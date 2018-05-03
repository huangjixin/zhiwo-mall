import {GuessOptions} from './../guess-options.model';
import {GuessQuestion} from './../guess-question.model';
import {ActivatedRoute, Router} from '@angular/router';
import {QuestionService} from './../question.service';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({selector: 'app-guess-question-edit-form', 
templateUrl: './guess-question-edit-form.component.html',
 styleUrls: ['./guess-question-edit-form.component.css']})
export class GuessQuestionEditFormComponent implements OnInit {
  // ID
  public id : String;
  public question : GuessQuestion;
  public questionFormGroup : FormGroup;

  // 是否显示待选项表单。
  public showOptionForm : boolean;
  public option : GuessOptions = new GuessOptions();

  // new 或者 edit
  public mode : String = 'new';

  public guessOptionSelection : any;

treeData = [
    {
      'id': '',
      'text': ''},
    {
      'id': 1,
      'text': '足球',
      'children': [
        {
          'id': 3,
          'text': '西甲',
          'link': 'pproduct'
        }, {
          'id': 4,
          'text': '德甲',
          'link': 'pcategory'
        }, {
          'id': 5,
          'text': '英超',
          'link': 'pproperty'
        }, {
          'id': 23,
          'text': '其它',
          'link': 'pshop'
        }
      ]
    }, {
      'id': 6,
      'text': '篮球',
      'children': [
        {
          'id': 7,
          'text': 'NBA',
          'link': 'gquestion'
        }, {
          'id': 8,
          'text': 'CBA'
        }
      ]
    }, {
      'id': 15,
      'text': '趣味奇偶数',
      'children': [
        {
          'id': 16,
          'text': '广东十选1',
          'link': 'mmember'
        }, {
          'id': 17,
          'text': '福建十选1',
          'link': 'mcategory'
        }
      ]
    }
  ];

  constructor(public activeRoute : ActivatedRoute,
     private router : Router, private location : Location, private questionService : QuestionService) {
    // this.questionFormGroup = fb.group({    'name': [null, Validators.required]
    // });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.question = new GuessQuestion();
    const id : String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const question : GuessQuestion = this
        .questionService
        .getDataById(this.id);
      if (question !== null) {
        this.question = question;
      }
    } else {
      // 模拟数据。
      // for (let index = 0; index < 3; index++) {
      //   const option : GuessOptions = new GuessOptions();
      //   option.name = 'test' + index;
      //   option.betRate = 1.8;
      //   this
      //     .question
      //     .guessOptions
      //     .push(option);
      // }
    }
  }

  // 返回上一级目录。
  back() : void {
    this
      .location
      .back();
  }

  submitForm(value : any) {
    alert(JSON.stringify(value));
  }

  onOptionEditRow(row : GuessOptions) : void {
    this.mode = 'edit';
    this.showOptionForm = true;
    this.option = row;
  }

  // 删除一个选项。
  onOptionDeleteRow(row : GuessOptions) : void {
    const index: number = this
      .question
      .guessOptions
      .indexOf(row);
    this
      .question
      .guessOptions
      .splice(index, 1);
      const temp = this.question.guessOptions.concat();
      this.question.guessOptions = temp;
  }

  addOtpion() : void {
    this.mode = 'new';
    this.showOptionForm = true;
    this.option = new GuessOptions();
  }

  // 保存选项
  saveOption() : void {
    if ( this.mode === 'new') {
      this
        .question
        .guessOptions
        .push(this.option);
        const temp = this.question.guessOptions.concat();
        this.question.guessOptions = temp;
        this.option = new GuessOptions();
    } else if (this.mode === 'edit') {

    }
  }

  onSubmit(): void {
    alert(JSON.stringify(this.question));
  }
}
