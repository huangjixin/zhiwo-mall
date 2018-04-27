import { Component, OnInit } from '@angular/core';
import {Property} from '../property.model';
import {FormGroup} from '@angular/forms';
import {PropertyService} from '../property.service';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-property-edit-form',
  templateUrl: './property-edit-form.component.html',
  styleUrls: ['./property-edit-form.component.css']
})
export class PropertyEditFormComponent implements OnInit {

  // ID
  public id: String;
  public property: Property;
  public shopFormGroup: FormGroup;
  constructor(public activeRoute: ActivatedRoute,
              private router: Router, private location: Location, private propertyService: PropertyService) { }

  ngOnInit() {
    this.property = new Property();
    const id: String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const property: Property =  this.propertyService.getDataById(this.id);
      if ( property !== null ) {
        this.property = property;
      }
    }
  }
// 返回上一级目录。
  back(): void {
    this.location.back();
  }

  submitForm() {
    alert(JSON.stringify(this.property));
  }
}
