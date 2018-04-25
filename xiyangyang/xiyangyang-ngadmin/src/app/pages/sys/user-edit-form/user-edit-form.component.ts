import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user.model';
import {Location} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-edit-form',
  templateUrl: './user-edit-form.component.html',
  styleUrls: ['./user-edit-form.component.css'],
  providers: [UserService]
})
export class UserEditFormComponent implements OnInit {
  user: User;

  constructor(private userService: UserService, private location: Location, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  back(): void {
    this.location.back();
  }

  submitForm() {
    this.userService.saveOrUpdate(this.user);
    this.back();
  }

  onCancel() {
    this.back();
  }
}
