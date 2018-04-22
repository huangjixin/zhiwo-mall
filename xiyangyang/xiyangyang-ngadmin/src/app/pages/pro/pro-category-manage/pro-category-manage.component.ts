import { ProCategoryService } from './../pro-category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pro-category-manage',
  templateUrl: './pro-category-manage.component.html',
  styleUrls: ['./pro-category-manage.component.css']
})
export class ProCategoryManageComponent implements OnInit {

  constructor(private service: ProCategoryService) { }

  ngOnInit() {
  }

}
