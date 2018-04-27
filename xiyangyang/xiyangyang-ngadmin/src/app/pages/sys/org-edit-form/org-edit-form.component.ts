import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Org} from '../org.model';
import {Location} from '@angular/common';
import {OrgService} from '../org.service';

@Component({
  selector: 'app-org-edit-form',
  templateUrl: './org-edit-form.component.html',
  styleUrls: ['./org-edit-form.component.css'],
  providers: [OrgService]
})
export class OrgEditFormComponent implements OnInit {
  org: Org;
  treeData: any;

  constructor(public activeRoute: ActivatedRoute,
              private orgService: OrgService, private location: Location, private router: Router) {
  }

  ngOnInit() {
    this.treeData = this.orgService.getComboData();
    const id: String = this.activeRoute.snapshot.params['id'];
    if (id !== null && id !== undefined) {
      this.org = this.orgService.findById(id);
    } else {
      this.org = new Org();
    }
  }

  back(): void {
    this.router.navigate(['/index', {outlets: {main: ['sorg', {outlets: {list: ['list']}}]}}]);
  }

  submitForm() {
    this.orgService.saveOrUpdate(this.org);
    this.back();
  }

  onCancel() {
    this.back();
  }

}
