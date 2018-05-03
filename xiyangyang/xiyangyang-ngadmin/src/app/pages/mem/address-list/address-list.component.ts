import {Component, OnInit} from '@angular/core';
import {AddressService} from './../address.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  total: number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];
  isNewRow = false;
  editingRow = null;
  closed = true;
  pagePosition: string = 'bottom';
  pageOptions = [{value: 'bottom', text: 'Bottom'}, {value: 'top', text: 'Top'}, {value: 'both', text: 'Both'}];

  constructor(private addressService: AddressService, private router: Router) {
  }

  ngOnInit() {
    this.addressService.getData().subscribe((data) => {
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
    this.router.navigate(['/index', {outlets: {main: ['maddress', {outlets: {list: ['new']}}]}}]);
  }

  onEditRow(row) {
    this.isNewRow = false;
    this.editingRow = row;
    this.closed = false;
    this.router.navigate(['/index', { outlets: { main: ['maddress', {outlets: {list : ['edit', row.id]}}] }}]);
  }

  onSaveRow(row) {
    if (this.isNewRow) {
      this.addressService.add(row);
    } else {
      this.addressService.update(row);
    }
    this.data = this.addressService.getData();
    this.closed = true;
    this.isNewRow = false;
  }

  onDeleteRow(row) {
    this.addressService.delete(row);
    this.data = this.addressService.getData();
  }

}
