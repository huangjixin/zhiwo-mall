import {CmsDocument} from './../cms-document.model';
import { ActivatedRoute, Router } from '@angular/router';
import {DocumentService} from './../document.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-document-edit-form',
  templateUrl: './document-edit-form.component.html',
  styleUrls: ['./document-edit-form.component.css']
})
export class DocumentEditFormComponent implements OnInit {
  // ID
  public id: String;
  public cmsDocument: CmsDocument;
  public questionFormGroup: FormGroup;

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private documentService: DocumentService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.cmsDocument = new CmsDocument();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const cmsDocument: CmsDocument =  this.documentService.getDataById(this.id);
      if ( cmsDocument !== null ) {
        this.cmsDocument = cmsDocument;
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
