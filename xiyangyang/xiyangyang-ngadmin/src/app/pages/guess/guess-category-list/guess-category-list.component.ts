import {GuessCategory} from './../guess-category.model';
import {Router, ActivatedRoute} from '@angular/router';
import {GuessCategoryService} from './../guess-category.service';
import {Component, OnInit} from '@angular/core';

@Component({selector: 'app-guess-category-list',
templateUrl: './guess-category-list.component.html',
 styleUrls: ['./guess-category-list.component.css']})
export class GuessCategoryListComponent implements OnInit {
  total : Number = 0;
  pageNumber : Number = 1;
  pageSize : Number = 15;
  data : any = [];

  guessCategory : GuessCategory;
  treeData = [
    {
      'id': 1,
      'name': '足球',
      'children': [
        {
          'id': 3,
          'name': '西甲',
          'link': 'pproduct'
        }, {
          'id': 4,
          'name': '德甲',
          'link': 'pcategory'
        }, {
          'id': 5,
          'name': '英超',
          'link': 'pproperty'
        }, {
          'id': 23,
          'name': '其它',
          'link': 'pshop'
        }
      ]
    }, {
      'id': 6,
      'name': '篮球',
      'children': [
        {
          'id': 7,
          'name': 'NBA',
          'link': 'gquestion'
        }, {
          'id': 8,
          'name': 'CBA'
        }
      ]
    }, {
      'id': 15,
      'name': '趣味奇偶数',
      'children': [
        {
          'id': 16,
          'name': '广东十选1',
          'link': 'mmember'
        }, {
          'id': 17,
          'name': '福建十选1',
          'link': 'mcategory'
        }
      ]
    }
  ];

  constructor(public activeRoute: ActivatedRoute, private router: Router, private categoryService: GuessCategoryService) {}

  ngOnInit() {}

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['gcategory', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   */
  onEditRow() {
    if ( this.guessCategory.id === null || this.guessCategory.id === undefined){
      return;
    }
    const id: String = this.guessCategory.id;
    this.router.navigate(['/index', {outlets: {main: ['gcategory', {outlets: {list: ['edit', id]}}]}}]);
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
    if (this.guessCategory == null || this.guessCategory == undefined) {
      this.guessCategory = new GuessCategory();
    }
    this.guessCategory.id = node.id;
  }
}
