import { ActivatedRouteSnapshot, Router, ParamMap, ActivatedRoute } from '@angular/router';
import { RoleService } from './../role.service';
import { Component, OnInit } from '@angular/core';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-role-edit-form',
  templateUrl: './role-edit-form.component.html',
  styleUrls: ['./role-edit-form.component.css']
})
export class RoleEditFormComponent implements OnInit {

  id: String;

  constructor(public activeRoute: ActivatedRoute,
    private router: Router, private roleService: RoleService) { }

  ngOnInit() {
    const ids: String = this.activeRoute.snapshot.params['id'];
    console.log(ids);
    // this.activeRoute.queryParams.subscribe(params => {
    //   this.id = params['id'];
    //   console.log(this.id);
    // });
    // const id: String = this.route.paramMap.get('id');
    // console.log(id);
  }

}
