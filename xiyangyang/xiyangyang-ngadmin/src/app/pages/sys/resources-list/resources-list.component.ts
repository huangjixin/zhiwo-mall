import {Component, OnInit} from '@angular/core';
import {ResourcesService} from '../resources.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Resource} from '../resource.model';
import {RoleService} from '../role.service';


@Component({
  selector: 'app-resources-list',
  templateUrl: './resources-list.component.html',
  styleUrls: ['./resources-list.component.css'],
  providers: [ResourcesService]
})
export class ResourcesListComponent implements OnInit {

  resource: Resource;

  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];
  loading: boolean = false;
  pagePosition: String = 'bottom';

  constructor(private resourcesService: ResourcesService, private router: Router) { }

  ngOnInit() {
    this.loadPage(this.pageNumber, this.pageSize);
  }

  onEditRow(row) {
    const id = row.id;
    this.router.navigate(['/index', {outlets: {main: ['sresources', {outlets: {list: ['edit', id]}}]}}]);
  }
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['sresources', {outlets: {list: ['new']}}]}}]);
  }

  onDeleteRow(row) {

  }

  onPageChange(event) {
    this.loadPage(event.pageNumber, event.pageSize);
  }

  loadPage(pageNumber: number, pageSize: number) {
    this.loading = true;
    this.resourcesService.getData(pageNumber, pageSize).subscribe((data) => {
      this.pageNumber = data.pageNumber;
      this.data = data.rows;
      this.loading = false;
      this.total = data.total;
    });
  }

}
