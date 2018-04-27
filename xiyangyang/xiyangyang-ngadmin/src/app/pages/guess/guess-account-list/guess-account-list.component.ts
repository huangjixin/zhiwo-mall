import { GuessAccountService } from './../guess-account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';


@Component({
  selector: 'app-guess-account-list',
  templateUrl: './guess-account-list.component.html',
  styleUrls: ['./guess-account-list.component.css']
})
export class GuessAccountListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data: any = [];

  constructor(public activeRoute: ActivatedRoute,
    private router: Router, private accountService: GuessAccountService) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    // console.log('初始化完毕');
     this.accountService.getData().subscribe((data) => {
     this.total = data.total;
     this.data = data.rows;
      });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', { outlets: { main: ['gaccount', {outlets: {list : ['new']}}] }}]);
  }
  
  /**
   * 跳转到编辑界面。
   * @param row 
   */
  onEditRow(row) {
    this.router.navigate(['/index', { outlets: { main: ['gaccount', {outlets: {list : ['edit', row.id]}}] }}]);
  }

  /**
   * 删除一行
   * @param row 
   */
  onDeleteRow(row) {
    // this.accountService.delete(row);
    this.data = this.accountService.getData();
  }
}
