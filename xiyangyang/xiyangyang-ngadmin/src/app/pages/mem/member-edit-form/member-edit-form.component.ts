import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {Member} from './../member.model';
import {MemberService} from './../member.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-edit-form',
  templateUrl: './member-edit-form.component.html',
  styleUrls: ['./member-edit-form.component.css']
})
export class MemberEditFormComponent implements OnInit {
  public id: String;
  public member: Member;

  files: File[] = [];

  constructor(public activeRoute: ActivatedRoute, private sanitizer: DomSanitizer, private memberService: MemberService) {
  }

  ngOnInit() {
    this.member = new Member();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      debugger;
      const member: Member = this.memberService.getDataById(this.id);
      if (member !== null) {
        this.member = member;
      }
    }
  }

  onFileSelect(event) {
    event.forEach((file) => {
      file.url = this.sanitizer.bypassSecurityTrustUrl((window.URL.createObjectURL(file)));
    });
    // event.file().url = this.sanitizer.bypassSecurityTrustUrl((window.URL.createObjectURL(event.file())));
    this.files = this.files.concat(event);
  }
}
