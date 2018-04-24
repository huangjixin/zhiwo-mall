import {MemberService} from './../member.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-member-manage',
  templateUrl: './member-manage.component.html',
  styleUrls: ['./member-manage.component.css']
})
export class MemberManageComponent implements OnInit {
  total: number = 0;
  pageNumber = 1;
  pageSize = 20;
  data = [];
  isNewRow = false;
  editingRow = null;
  closed = true;
  pagePosition: string = 'bottom';
  pageOptions = [{value: 'bottom', text: 'Bottom'}, {value: 'top', text: 'Top'}, {value: 'both', text: 'Both'}];

  constructor(private dataService: MemberService) {
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
  }

  onEditRow(row) {
    this.isNewRow = false;
    this.editingRow = row;
    this.closed = false;
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
