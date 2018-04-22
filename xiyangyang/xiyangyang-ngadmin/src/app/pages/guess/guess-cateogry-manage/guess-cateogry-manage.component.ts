import { GuessCategoryService } from './../guess-category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guess-cateogry-manage',
  templateUrl: './guess-cateogry-manage.component.html',
  styleUrls: ['./guess-cateogry-manage.component.css']
})
export class GuessCateogryManageComponent implements OnInit {

  constructor(private service: GuessCategoryService) { }

  ngOnInit() {
  }

}
