import {Component, OnInit} from '@angular/core';
import {MemberService} from './../member.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {
  total: number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];
  isNewRow = false;
  editingRow = null;
  closed = true;
  pagePosition: string = 'bottom';
  pageOptions = [{value: 'bottom', text: 'Bottom'}, {value: 'top', text: 'Top'}, {value: 'both', text: 'Both'}];

  constructor(private dataService: MemberService, private router: Router) {
  }

  ngOnInit() {
    this.dataService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
    this.initRow();
  }

  initRow() {
    this.editingRow = {
      itemid: null,
      name: null,
      listprice: null,
      unitcost: null,
      addr: null,
    };
  }

  onAddRow() {
    this.initRow();
    this.isNewRow = true;
    this.closed = false;
    const editLink: String = 'new';
    this.router.navigate(['/index', {outlets: {main: ['mmember', {outlets: {list: ['new']}}]}}]);
  }

  onEditRow(row) {
    this.isNewRow = false;
    this.editingRow = row;
    this.closed = false;
    this.router.navigate(['/index', { outlets: { main: ['mmember', {outlets: {list : ['edit', row.id]}}] }}]);
  }

  onSaveRow(row) {
    if (this.isNewRow) {
      this.dataService.add(row);
    } else {
      this.dataService.update(row);
    }
    this.data = this.dataService.getData();
    this.closed = true;
    this.isNewRow = false;
  }

  onDeleteRow(row) {
    this.dataService.delete(row);
    this.data = this.dataService.getData();
  }
}
