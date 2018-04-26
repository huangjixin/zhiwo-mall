import {CmsTopic} from './../cms-topic.model';
import { ActivatedRoute, Router } from '@angular/router';
import {TopicService} from './../topic.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-topic-edit-form',
  templateUrl: './topic-edit-form.component.html',
  styleUrls: ['./topic-edit-form.component.css']
})
export class TopicEditFormComponent implements OnInit {
  // ID
  public id: String;
  public cmsTopic: CmsTopic;
  public questionFormGroup: FormGroup;

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private topicService: TopicService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.cmsTopic = new CmsTopic();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const cmsTopic: CmsTopic =  this.topicService.getDataById(this.id);
      if ( cmsTopic !== null ) {
        this.cmsTopic = cmsTopic;
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
