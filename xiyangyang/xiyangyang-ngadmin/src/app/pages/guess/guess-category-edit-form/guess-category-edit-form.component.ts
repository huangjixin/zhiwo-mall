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
}
