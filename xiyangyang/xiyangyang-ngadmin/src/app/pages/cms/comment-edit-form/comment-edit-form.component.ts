import {CmsComment} from './../cms-comment.model';
import { ActivatedRoute, Router } from '@angular/router';
import {CommentService} from './../comment.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-comment-edit-form',
  templateUrl: './comment-edit-form.component.html',
  styleUrls: ['./comment-edit-form.component.css']
})
export class CommentEditFormComponent implements OnInit {
  // ID
  public id: String;
  public cmsComment: CmsComment;
  public questionFormGroup: FormGroup;

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private commentService: CommentService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.cmsComment = new CmsComment();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const cmsComment: CmsComment =  this.commentService.getDataById(this.id);
      if ( cmsComment !== null ) {
        this.cmsComment = cmsComment;
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
