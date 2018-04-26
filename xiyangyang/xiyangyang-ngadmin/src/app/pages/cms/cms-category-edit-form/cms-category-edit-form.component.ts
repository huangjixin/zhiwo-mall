import {CmsCategory} from './../cms-category.model';
import { ActivatedRoute, Router } from '@angular/router';
import {CmsCategoryService} from './../cms-category.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-cms-category-edit-form',
  templateUrl: './cms-category-edit-form.component.html',
  styleUrls: ['./cms-category-edit-form.component.css']
})
export class CmsCategoryEditFormComponent implements OnInit {
  // ID
  public id: String;
  public cmsCategory: CmsCategory;
  public questionFormGroup: FormGroup;

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private cmsCategoryService: CmsCategoryService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.cmsCategory = new CmsCategory();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const cmsCategory: CmsCategory =  this.cmsCategoryService.getDataById(this.id);
      if ( cmsCategory !== null ) {
        this.cmsCategory = cmsCategory;
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
