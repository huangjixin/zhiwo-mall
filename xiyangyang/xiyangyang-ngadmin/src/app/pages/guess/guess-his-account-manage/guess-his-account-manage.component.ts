import { GuessHisAccountService } from './../guess-his-account.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guess-his-account-manage',
  templateUrl: './guess-his-account-manage.component.html',
  styleUrls: ['./guess-his-account-manage.component.css']
})
export class GuessHisAccountManageComponent implements OnInit {

  constructor(private service: GuessHisAccountService) { }

  ngOnInit() {
  }

}
