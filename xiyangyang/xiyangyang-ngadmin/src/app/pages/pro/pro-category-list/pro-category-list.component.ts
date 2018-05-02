import { ProCategoryService } from './../pro-category.service';
import { ProCategory } from './../pro-category.model';
import {Router, ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-pro-category-list',
  templateUrl: './pro-category-list.component.html',
  styleUrls: ['./pro-category-list.component.css']
})
export class ProCategoryListComponent implements OnInit {
  total : Number = 0;
  pageNumber : Number = 1;
  pageSize : Number = 15;
  data : any = [];

  proCategory : ProCategory;
  treeData = [
    {
      'id': 1,
      'name': '服装',
      'children': [
        {
          'id': 3,
          'name': '男装',
          'link': 'pproduct'
        }, {
          'id': 4,
          'name': '女装',
          'link': 'pcategory'
        }
      ]
    }, {
      'id': 6,
      'name': '水果',
      'children': [
        {
          'id': 7,
          'name': '进口水果',
          'link': 'gquestion'
        }, {
          'id': 8,
          'name': '国产水果'
        }
      ]
    }, {
      'id': 15,
      'name': '体育',
      'children': [
        {
          'id': 16,
          'name': '球类',
          'link': 'mmember'
        }, {
          'id': 17,
          'name': '运动服',
          'link': 'mcategory'
        }
      ]
    }
  ];

  constructor(public activeRoute: ActivatedRoute, private router: Router, private categoryService: ProCategoryService) {}

  ngOnInit() {}

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['pcategory', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   */
  onEditRow() {
    if ( this.proCategory.id === null || this.proCategory.id === undefined){
      return;
    }
    const id: String = this.proCategory.id;
    this.router.navigate(['/index', {outlets: {main: ['pcategory', {outlets: {list: ['edit', id]}}]}}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row); this.data = this.categoryService.getData();
  }

  // 点击选中菜单处理函数。
  selectNodeHandler(node : any) : void {
    if (this.proCategory === null || this.proCategory === undefined) {
      this.proCategory = new ProCategory();
    }
    this.proCategory.id = node.id;
  }

}
