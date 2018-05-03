import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {Address} from './../member-address.model';
import {AddressService} from './../address.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Location} from '@angular/common';

@Component({
  selector: 'app-address-edit-form',
  templateUrl: './address-edit-form.component.html',
  styleUrls: ['./address-edit-form.component.css']
})
export class AddressEditFormComponent implements OnInit {
  // ID
  public id: String;
  public address: Address;
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
              private router: Router, private location: Location, private addressService: AddressService) {
    // this.questionFormGroup = fb.group({
    //    'name': [null, Validators.required]
    //   });
  }

  // 初始化取ID值。
  ngOnInit() {
    this.address = new Address();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const address: Address = this.addressService.getDataById(this.id);
      if (address !== null) {
        this.address = address;
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
