import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  treeData = [{
    'id': 1,
    'name': '商品模块管理',
    'children': [{
      'id': 3,
      'name': '商品管理',
      'link': 'pproduct'
  }, {
    'id': 4,
    'name': '商品种类管理',
    'link': 'pcategory'
  }, {
    'id': 5,
    'name': '商品属性管理',
    'link': 'pproperty'
    }, {
      'id': 23,
      'name': '店铺管理',
      'link': 'pshop'
      }]
  }, {
    'id': 6,
    'name': '竞猜管理',
    'children': [{
      'id': 7,
      'name': '问题管理',
      'link': 'gquestion'
  }, {
    'id': 8,
    'name': '竞猜统计管理',
  }, {
    'id': 20,
    'name': '竞猜分类管理',
    'link': 'gcateogry'
  }, {
    'id': 21,
    'name': '竞猜账户管理',
    'link': 'gaccount'
    }, {
      'id': 22,
      'name': '竞猜账户历史记录管理',
      'link': 'ghaccount'
      }]
  }, {
    'id': 15,
    'name': '会员模块管理',
    'children': [{
      'id': 16,
      'name': '会员管理',
      'link': 'mmember'
      }, {
        'id': 17,
        'name': '会员分类管理',
        'link': 'mcategory'
      }, {
        'id': 18,
        'name': '会员地址管理',
        'link': 'maddress'
    }]
  }, {
    'id': 30,
    'name': '新闻管理',
    'children': [{
      'id': 31,
      'name': '文档管理',
      'link': 'cdocument'
      }, {
        'id': 32,
        'name': '新闻分类管理',
        'link': 'ccategory'
      }, {
        'id': 33,
        'name': '新闻评论管理',
        'link': 'ccomment'
    }, {
      'id': 34,
      'name': '新闻专题管理',
      'link': 'ctopic'
  }]
  }, {
    'id': 19,
    'name': '系统管理',
    'children': [{
      'id': 11,
      'name': '用户管理',
      'link': 'suser'
    }, {
      'id': 12,
      'name': '角色管理',
      'link': 'srole'
    }, {
      'id': 13,
      'name': '资源管理',
      'link': 'sresources'
    }, {
      'id': 13,
      'name': '组织管理',
      'link': 'sorg'
    }, {
      'id': 14,
      'name': '登录日志管理',
      'link': 'slog'
    }]
}];

  data = [
    {'code': 'FI-SW-01','name': 'Koi','unitcost': 10.00,'status': 'P','listprice': 36.50,'attr': 'Large','itemid': 'EST-1'},
    {'code': 'K9-DL-01','name': 'Dalmation','unitcost': 12.00,'status': 'P','listprice': 18.50,'attr': 'Spotted Adult Female','itemid': 'EST-10'},
    {'code': 'RP-SN-01','name': 'Rattlesnake','unitcost': 12.00,'status': 'P','listprice': 38.50,'attr': 'Venomless','itemid': 'EST-11'},
    {'code': 'RP-SN-01','name': 'Rattlesnake','unitcost': 12.00,'status': 'P','listprice': 26.50,'attr': 'Rattleless','itemid': 'EST-12'},
    {'code': 'RP-LI-02','name': 'Iguana','unitcost': 12.00,'status': 'P','listprice': 35.50,'attr': 'Green Adult','itemid': 'EST-13'},
    {'code': 'FL-DSH-01','name': 'Manx','unitcost': 12.00,'status': 'P','listprice': 158.50,'attr': 'Tailless','itemid': 'EST-14'},
    {'code': 'FL-DSH-01','name': 'Manx','unitcost': 12.00,'status': 'P','listprice': 83.50,'attr': 'With tail','itemid': 'EST-15'},
    {'code': 'FL-DLH-02','name': 'Persian','unitcost': 12.00,'status': 'P','listprice': 23.50,'attr': 'Adult Female','itemid': 'EST-16'},
    {'code': 'FL-DLH-02','name': 'Persian','unitcost': 12.00,'status': 'P','listprice': 89.50,'attr': 'Adult Male','itemid': 'EST-17'},
    {'code': 'AV-CB-01','name': 'Amazon Parrot','unitcost': 92.00,'status': 'P','listprice': 63.50,'attr': 'Adult Male','itemid': 'EST-18'}
];


  selection: any = null;

  constructor(private router: Router) { }

  ngOnInit() {
    console.log('123');
  }


  // 点击选中菜单处理函数。
  selectNodeHandler(node: any): void {
    if (node.link != null || node.link !== undefined ) {
      // [{ outlets: { userInfo: ['guess-record'] } }]
      const link: String =  node.link;
      // this.router.navigate(['/index', { outlets: { main: [node.link] }}]);
      this.router.navigate(['/index', { outlets: { main: [link, {outlets: {list : ['list']}}] }}]);
      // this.router.navigate(['/index', { outlets: { main: [link, {outlets: {list : ['edit', '123456']}}] }}]);
      // {queryParams: {'id': '123456'}}
    }
    console.log('123456');
  }
}
