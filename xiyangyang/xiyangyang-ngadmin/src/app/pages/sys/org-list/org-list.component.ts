import {Component, OnInit} from '@angular/core';
import {Org} from '../org.model';
import {OrgService} from '../org.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-org-list',
  templateUrl: './org-list.component.html',
  styleUrls: ['./org-list.component.css'],
  providers: [OrgService]
})
export class OrgListComponent implements OnInit {

  org: Org;
  treeData: any;

  constructor(private router: Router, private orgService: OrgService) {
  }

  ngOnInit() {
    this.org = new Org();
    this.treeData = this.orgService.getData();
  }

  selectNodeHandler(node: any): void {
    this.org.id = node.id;
  }

  /**
   * 新增
   */
  onAddRow() {
    this.router.navigate(['/index', {outlets: {main: ['sorg', {outlets: {list: ['new']}}]}}]);
  }

  /**
   * 跳转到编辑界面。
   */
  onEditRow() {
    if ( this.org.id === null || this.org.id === undefined){
      return;
    }
    const id: String = this.org.id;
    this.router.navigate(['/index', {outlets: {main: ['sorg', {outlets: {list: ['edit', id]}}]}}]);
  }

  /**
   * 删除一行
   * @param row
   */
  onDeleteRow(row) {
    // this.questionService.delete(row); this.data = this.categoryService.getData();
  }

}
