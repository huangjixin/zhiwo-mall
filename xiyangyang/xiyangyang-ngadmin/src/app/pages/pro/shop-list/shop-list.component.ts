import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ShopService} from '../shop.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data: any = [];
  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private shopService: ShopService) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    // console.log('初始化完毕');
    this.shopService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', { outlets: { main: ['pshop', {outlets: {list : ['new']}}] }}]);
  }

  /**
   * 跳转到编辑界面。
   * @param row
   */
  onEditRow(row) {
    this.router.navigate(['/index', { outlets: { main: ['pshop', {outlets: {list : ['edit', row.id]}}] }}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row);
    this.data = this.shopService.getData();
  }
}
