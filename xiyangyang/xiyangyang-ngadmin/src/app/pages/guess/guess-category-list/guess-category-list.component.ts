import {Component, OnInit} from '@angular/core';

@Component({selector: 'app-guess-category-list',
 templateUrl: './guess-category-list.component.html',
 styleUrls: ['./guess-category-list.component.css']})
export class GuessCategoryListComponent implements OnInit {
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

  constructor() {}

  ngOnInit() {}

}
