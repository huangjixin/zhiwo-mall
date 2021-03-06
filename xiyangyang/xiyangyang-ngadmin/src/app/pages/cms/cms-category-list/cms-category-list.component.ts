import {ActivatedRoute, Router} from '@angular/router';
import {CmsCategoryService} from './../cms-category.service';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';

@Component({
  selector: 'app-cms-category-list',
  templateUrl: './cms-category-list.component.html',
  styleUrls: ['./cms-category-list.component.css']
})
export class CmsCategoryListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];
  treeData = [
    {
      'id': 1,
      'name': '娱乐',
      'children': [
        {
          'id': 3,
          'name': '赵丽颖',
          'link': 'pproduct'
        }, {
          'id': 4,
          'name': '李易峰',
          'link': 'pcategory'
        }, {
          'id': 5,
          'name': '李沁',
          'link': 'pproperty'
        }, {
          'id': 23,
          'name': '林允儿',
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
      'name': '老司机专场',
      'children': [
        {
          'id': 16,
          'name': '中国老司机',
          'link': 'mmember'
        }, {
          'id': 17,
          'name': '小司机',
          'link': 'mcategory'
        }
      ]
    }
  ];

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private cmsCategoryService: CmsCategoryService) {
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    // console.log('初始化完毕');
    this.cmsCategoryService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['ccategory', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   * @param row
   */
  onEditRow(row) {
    this.router.navigate(['/index', {outlets: {main: ['ccategory', {outlets: {list: ['edit', row.id]}}]}}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row);
    this.data = this.cmsCategoryService.getData();
  }

}
