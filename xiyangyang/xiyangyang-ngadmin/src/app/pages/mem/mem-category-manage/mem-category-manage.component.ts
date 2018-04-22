import { MemCategoryService } from './../mem-category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mem-category-manage',
  templateUrl: './mem-category-manage.component.html',
  styleUrls: ['./mem-category-manage.component.css']
})
export class MemCategoryManageComponent implements OnInit {

  constructor(private service: MemCategoryService) { }

  ngOnInit() {
  }

}
