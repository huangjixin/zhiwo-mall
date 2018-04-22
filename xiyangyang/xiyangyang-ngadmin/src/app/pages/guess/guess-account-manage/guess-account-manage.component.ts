import { GuessAccountService } from './../guess-account.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guess-account-manage',
  templateUrl: './guess-account-manage.component.html',
  styleUrls: ['./guess-account-manage.component.css']
})
export class GuessAccountManageComponent implements OnInit {

  constructor(private service: GuessAccountService) { }

  ngOnInit() {
  }

}
