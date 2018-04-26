import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user.model';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {GuessQuestion} from '../../guess/guess-question.model';

@Component({
  selector: 'app-user-edit-form',
  templateUrl: './user-edit-form.component.html',
  styleUrls: ['./user-edit-form.component.css'],
  providers: [UserService]
})
export class UserEditFormComponent implements OnInit {
  user: User;

  constructor(public activeRoute: ActivatedRoute,
              private userService: UserService, private location: Location, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
    const id: String = this.activeRoute.snapshot.params['id'];
    if (id !== null && id !== undefined) {
      this.user = this.userService.findById(id);
    } else {
      this.user = new User();
    }
  }

  back(): void {
    this.router.navigate(['/index', {outlets: {main: ['suser', {outlets: {list: ['list']}}]}}]);
  }

  submitForm() {
    this.userService.saveOrUpdate(this.user);
    this.back();
  }

  onCancel() {
    this.back();
  }
}
