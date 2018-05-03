import {Category} from './../member-category.model';
import { ActivatedRoute, Router } from '@angular/router';
import {MemCategoryService} from './../mem-category.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-mem-category-edit-form',
  templateUrl: './mem-category-edit-form.component.html',
  styleUrls: ['./mem-category-edit-form.component.css']
})
export class MemCategoryEditFormComponent implements OnInit {
  // ID
  public id: String;
  public category: Category;
  public questionFormGroup: FormGroup;
  treeData = [
    {
      'id': '',
      'text': ''},
    {
      'id': 1,
      'text': '娱乐',
      'children': [
        {
          'id': 3,
          'text': '赵丽颖',
          'link': 'pproduct'
        }, {
          'id': 4,
          'text': '李易峰',
          'link': 'pcategory'
        }, {
          'id': 5,
          'text': '李沁',
          'link': 'pproperty'
        }, {
          'id': 23,
          'text': '林允儿',
          'link': 'pshop'
        }
      ]
    }, {
      'id': 6,
      'text': '科技',
      'children': [
        {
          'id': 7,
          'text': '中国十大黑科技',
          'link': 'gquestion'
        }, {
          'id': 8,
          'text': '公鸡中的战斗机'
        }
      ]
    }, {
      'id': 15,
      'text': '老司机专场',
      'children': [
        {
          'id': 16,
          'text': '中国老司机',
          'link': 'mmember'
        }, {
          'id': 17,
          'text': '小司机',
          'link': 'mcategory'
        }
      ]
    }
  ];
  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private memCategoryService: MemCategoryService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.category = new Category();
    const id: String = this.activeRoute.snapshot.params['id'];
    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const category: Category =  this.memCategoryService.getDataById(this.id);
      if ( category !== null ) {
        this.category = category;
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
