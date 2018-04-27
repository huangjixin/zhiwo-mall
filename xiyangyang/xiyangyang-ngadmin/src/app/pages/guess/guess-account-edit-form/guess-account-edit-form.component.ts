import { GuessAccount } from './../guess-account.model';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import { GuessAccountService } from '../guess-account.service';

@Component({selector: 'app-guess-account-edit-form',
 templateUrl: './guess-account-edit-form.component.html',
  styleUrls: ['./guess-account-edit-form.component.css']})
export class GuessAccountEditFormComponent implements OnInit {
  // ID
  public id : String;
  public account : GuessAccount;
  public accountFormGroup : FormGroup;

  constructor(public activeRoute : ActivatedRoute,
    private router : Router,
    private location : Location,
    private guessAccountService : GuessAccountService) {}

  // 初始化取ID值。
  ngOnInit() {
    this.account = new GuessAccount();
    const id : String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      // const account : GuessAccount = this
      //   .guessAccountService
      //   .getDataById(this.id);
      // 临时模拟数据。
      const account : GuessAccount = new GuessAccount();
      account.id = this.id;
      account.balance = 1000;
      if (account !== null) {
        this.account = account;
      }
    } else {
      // 模拟数据。
      // for (let index = 0; index < 3; index++) {
      //   const option : GuessOptions = new GuessOptions();
      //   option.name = 'test' + index;
      //   option.betRate = 1.8;
      //   this
      //     .account
      //     .guessOptions
      //     .push(option);
      // }
    }
  }

  // 返回上一级目录。
  back() : void {
    this.location.back();
  }

  onSubmit(): void {
    alert(JSON.stringify(this.account));
  }

}
