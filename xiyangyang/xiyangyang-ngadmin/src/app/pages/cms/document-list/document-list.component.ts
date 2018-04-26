import {ActivatedRoute, Router} from '@angular/router';
import {DocumentService} from './../document.service';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.css']
})
export class DocumentListComponent implements OnInit {
  total: Number = 0;
  pageNumber = 1;
  pageSize = 15;
  data = [];

  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private documentService: DocumentService) {
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    // console.log('初始化完毕');
    this.documentService.getData().subscribe((data) => {
      this.total = data.total;
      this.data = data.rows;
    });
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['cdocument', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   * @param row
   */
  onEditRow(row) {
    this.router.navigate(['/index', {outlets: {main: ['cdocument', {outlets: {list: ['edit', row.id]}}]}}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row);
    this.data = this.documentService.getData();
  }

}
