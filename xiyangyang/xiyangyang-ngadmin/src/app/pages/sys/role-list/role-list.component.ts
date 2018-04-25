import { Component, OnInit } from '@angular/core';
import {RoleService} from '../role.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css'],
  providers: [RoleService]
})
export class RoleListComponent implements OnInit {


  total: Number = 0;
  pageNumber = 1;
  pageSize = 10;
  data = [];
  loading: boolean = false;
  pagePosition: String = 'bottom';

  isNewRow = false;
  editingRow = null;
  closed = true;

  constructor(private roleService: RoleService, private router: Router) { }

  ngOnInit() {
    this.loadPage(this.pageNumber, this.pageSize);
  }

  onEditRow(row) {
    this.isNewRow = false;
    this.editingRow = row;
    this.closed = false;
  }
  onAddRow() {

    this.isNewRow = true;
    this.closed = false;
  }

  onPageChange(event) {
    this.loadPage(event.pageNumber, event.pageSize);
  }

  loadPage(pageNumber: number, pageSize: number) {
    this.loading = true;
    this.roleService.getData(pageNumber, pageSize).subscribe((data) => {
      this.pageNumber = data.pageNumber;
      this.data = data.rows;
      this.loading = false;
      this.total = data.total;
    });
  }

}
