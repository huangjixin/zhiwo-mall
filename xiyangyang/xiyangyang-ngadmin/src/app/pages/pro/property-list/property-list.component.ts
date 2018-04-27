import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PropertyService} from '../property.service';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data: any = [];
  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private propertyService: PropertyService) { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    // console.log('初始化完毕');
    this.propertyService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', { outlets: { main: ['pproperty', {outlets: {list : ['new']}}] }}]);
  }

  /**
   * 跳转到编辑界面。
   * @param row
   */
  onEditRow(row) {
    this.router.navigate(['/index', { outlets: { main: ['pproperty', {outlets: {list : ['edit', row.id]}}] }}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row);
    this.data = this.propertyService.getData();
  }

}
