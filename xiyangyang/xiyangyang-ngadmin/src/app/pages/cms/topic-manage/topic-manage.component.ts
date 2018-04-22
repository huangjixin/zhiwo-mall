import { TopicService } from './../topic.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-topic-manage',
  templateUrl: './topic-manage.component.html',
  styleUrls: ['./topic-manage.component.css']
})
export class TopicManageComponent implements OnInit {

  constructor(private service: TopicService) { }

  ngOnInit() {
  }

}
