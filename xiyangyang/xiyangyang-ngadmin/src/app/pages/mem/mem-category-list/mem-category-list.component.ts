import {ActivatedRoute, Router} from '@angular/router';
import {MemCategoryService} from './../mem-category.service';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';

@Component({
  selector: 'app-mem-category-list',
  templateUrl: './mem-category-list.component.html',
  styleUrls: ['./mem-category-list.component.css']
})
export class MemCategoryListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];
  treeData = [
    {
      'id': 1,
      'name': 'VIP',
      'children': [
        {
          'id': 3,
          'name': '黄金VIP',
          'link': 'pproduct'
        }, {
          'id': 4,
          'name': '砖石VIP',
          'link': 'pcategory'
        }, {
          'id': 5,
          'name': '王者VIP',
          'link': 'pproperty'
        }, {
          'id': 23,
          'name': '青铜VIP',
          'link': 'pshop'
        }
      ]
    }, {
      'id': 6,
      'name': '科技',
      'children': [
        {
          'id': 7,
          'name': '中国十大黑科技',
          'link': 'gquestion'
        }, {
          'id': 8,
          'name': '公鸡中的战斗机'
        }
      ]
    }, {
      'id': 15,
      'name': '会员专场',
      'children': [
        {
          'id': 16,
          'name': '中国老会员',
          'link': 'mmember'
        }, {
          'id': 17,
          'name': '小会员',
          'link': 'mcategory'
        }
      ]
    }
  ];

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private memCategoryService: MemCategoryService) {
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    // console.log('初始化完毕');
    this.memCategoryService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['mcategory', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   * @param row
   */
  onEditRow(row) {
    this.router.navigate(['/index', {outlets: {main: ['mcategory', {outlets: {list: ['edit', row.id]}}]}}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row);
    this.data = this.memCategoryService.getData();
  }

}
