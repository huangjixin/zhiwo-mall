import { ProCategory } from './../pro-category.model';
import { ProCategoryService } from './../pro-category.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-pro-category-edit-form',
  templateUrl: './pro-category-edit-form.component.html',
  styleUrls: ['./pro-category-edit-form.component.css']
})
export class ProCategoryEditFormComponent implements OnInit {

   // ID
   public id : String;
   public category : ProCategory;
   public categoryFormGroup : FormGroup;
 
   treeData = [
    {
      'id': 1,
      'text': '服装',
      'children': [
        {
          'id': 3,
          'text': '男装',
          'link': 'pproduct'
        }, {
          'id': 4,
          'text': '女装',
          'link': 'pcategory'
        }
      ]
    }, {
      'id': 6,
      'text': '水果',
      'children': [
        {
          'id': 7,
          'text': '进口水果',
          'link': 'gquestion'
        }, {
          'id': 8,
          'text': '国产水果'
        }
      ]
    }, {
      'id': 15,
      'text': '体育',
      'children': [
        {
          'id': 16,
          'text': '球类',
          'link': 'mmember'
        }, {
          'id': 17,
          'text': '运动服',
          'link': 'mcategory'
        }
      ]
    }
  ];
 
   constructor(public activeRoute : ActivatedRoute,
      private router : Router,
      private location : Location, private categoryService : ProCategoryService) {}
 
   ngOnInit() {
     this.category = new ProCategory();
     const id : String = this.activeRoute.snapshot.params['id'];
 
     this.id = id;
     if (this.id !== null && this.id !== undefined) {
       const category : ProCategory = new ProCategory();
       if (category !== null) {
         this.category = category;
       }
     }
     this.category.id = id;
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

