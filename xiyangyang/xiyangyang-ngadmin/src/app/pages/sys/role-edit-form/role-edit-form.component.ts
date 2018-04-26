import { ActivatedRouteSnapshot, Router, ParamMap, ActivatedRoute } from '@angular/router';
import { RoleService } from './../role.service';
import { Component, OnInit } from '@angular/core';
import { switchMap } from 'rxjs/operators';

import {Location} from '@angular/common';
import {Role} from '../role.model';

@Component({
  selector: 'app-role-edit-form',
  templateUrl: './role-edit-form.component.html',
  styleUrls: ['./role-edit-form.component.css'],
  providers:[RoleService]
})
export class RoleEditFormComponent implements OnInit {

  role: Role;

  constructor(public activeRoute: ActivatedRoute,
              private roleService: RoleService, private location: Location, private router: Router) { }

  ngOnInit() {
    const id: String = this.activeRoute.snapshot.params['id'];
    if (id !== null && id !== undefined) {
      this.role = this.roleService.findById(id);
    } else {
      this.role = new Role();
    }
  }

  back(): void {
    this.router.navigate(['/index', {outlets: {main: ['srole', {outlets: {list: ['list']}}]}}]);
  }

  submitForm() {
    this.roleService.saveOrUpdate(this.role);
    this.back();
  }

  onCancel() {
    this.back();
  }

}
