import {Category} from './../../mem/member-category.model';
import {GuessCategory} from './../guess-category.model';
import {GuessCategoryService} from './../guess-category.service';
import {GuessQuestion} from './../guess-question.model';
import {ActivatedRoute, Router} from '@angular/router';
import {QuestionService} from './../question.service';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
@Component({selector: 'app-guess-category-edit-form',
 templateUrl: './guess-category-edit-form.component.html',
  styleUrls: ['./guess-category-edit-form.component.css']})
export class GuessCategoryEditFormComponent implements OnInit {
  // ID
  public id : String;
  public category : GuessCategory;
  public categoryFormGroup : FormGroup;

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
     private router : Router,
     private location : Location, private categoryService : GuessCategoryService) {}

  ngOnInit() {
    this.category = new GuessCategory();
    const id : String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const category : GuessCategory = new GuessCategory();
      if (category !== null) {
        this.category = category;
      }
    }
  }

  /**
   * 返回
   */
  back(): void {
    this.location.back();
  }


  onSubmit(): void {
    alert(JSON.stringify(this.category));
  }
}
