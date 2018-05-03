import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {Member} from './../member.model';
import {MemberService} from './../member.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-member-edit-form',
  templateUrl: './member-edit-form.component.html',
  styleUrls: ['./member-edit-form.component.css']
})
export class MemberEditFormComponent implements OnInit {
  // ID
  public id: String;
  public member: Member;
  public questionFormGroup: FormGroup;
  treeData = [
    {
      'id': '',
      'text': ''
    },
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

  files: File[] = [];

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private memberService: MemberService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.member = new Member();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const member: Member = this.memberService.getDataById(this.id);
      if (member !== null) {
        this.member = member;
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
