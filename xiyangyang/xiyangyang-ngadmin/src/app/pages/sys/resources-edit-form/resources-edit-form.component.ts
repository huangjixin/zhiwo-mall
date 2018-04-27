import {Component, OnInit} from '@angular/core';
import {Resource} from '../resource.model';
import {ResourcesService} from '../resources.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {OrgService} from '../org.service';

@Component({
  selector: 'app-resources-edit-form',
  templateUrl: './resources-edit-form.component.html',
  styleUrls: ['./resources-edit-form.component.css'],
  providers: [ResourcesService, OrgService]
})
export class ResourcesEditFormComponent implements OnInit {
  resource: Resource;
  orgTreeData: any;

  constructor(public activeRoute: ActivatedRoute,
              private resourcesService: ResourcesService,
              private orgService: OrgService,
              private location: Location, private router: Router) {
  }

  ngOnInit() {
    const id: String = this.activeRoute.snapshot.params['id'];
    if (id !== null && id !== undefined) {
      this.resource = this.resourcesService.findById(id);
    } else {
      this.resource = new Resource();
    }

    this.orgTreeData = this.orgService.getComboData();
  }

  back(): void {
    this.router.navigate(['/index', {outlets: {main: ['sresources', {outlets: {list: ['list']}}]}}]);
  }

  submitForm() {
    this.resourcesService.saveOrUpdate(this.resource);
    this.back();
  }

  onCancel() {
    this.back();
  }

}
