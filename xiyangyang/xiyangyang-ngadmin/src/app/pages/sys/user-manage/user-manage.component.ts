import {UserService} from './../user.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../user.model';

@Component({
  selector: 'app-user-manage',
  templateUrl: './user-manage.component.html',
  styleUrls: ['./user-manage.component.css'],
  providers: [UserService]
})
export class UserManageComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 10;
  data = [];
  loading: boolean = false;
  pagePosition: String = 'bottom';
  isNewRow = false;
  editingRow = null;
  closed = true;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.loadPage(this.pageNumber, this.pageSize);
    this.initRow();
  }

  onPageChange(event) {
    this.loadPage(event.pageNumber, event.pageSize);
  }

  initRow() {
    this.editingRow = new User();
  }

  onEditRow(row) {
    this.isNewRow = false;
    this.editingRow = row;
    this.closed = false;
  }
  onAddRow() {
    this.initRow();
    this.isNewRow = true;
    this.closed = false;
  }

  loadPage(pageNumber: number, pageSize: number) {
    this.loading = true;
    this.userService.getData(pageNumber, pageSize).subscribe((data) => {
      this.pageNumber = data.pageNumber;
      this.data = data.rows;
      this.loading = false;
      this.total = data.total;
    });
  }
}

