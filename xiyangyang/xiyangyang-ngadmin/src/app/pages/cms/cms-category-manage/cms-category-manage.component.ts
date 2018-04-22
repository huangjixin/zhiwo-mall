import { CommentService } from './../comment.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cms-category-manage',
  templateUrl: './cms-category-manage.component.html',
  styleUrls: ['./cms-category-manage.component.css']
})
export class CmsCategoryManageComponent implements OnInit {

  constructor(private service: CommentService) { }

  ngOnInit() {
  }

}
