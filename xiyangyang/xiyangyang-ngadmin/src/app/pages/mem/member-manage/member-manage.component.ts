import { MemberService } from './../member.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-member-manage',
  templateUrl: './member-manage.component.html',
  styleUrls: ['./member-manage.component.css']
})
export class MemberManageComponent implements OnInit {

  constructor(private service: MemberService) { }

  ngOnInit() {
  }

}