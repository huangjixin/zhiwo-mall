import { CommentService } from './../comment.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comment-manage',
  templateUrl: './comment-manage.component.html',
  styleUrls: ['./comment-manage.component.css']
})
export class CommentManageComponent implements OnInit {

  constructor(private service: CommentService) { }

  ngOnInit() {
  }

}
