import {PropertyService} from './../property.service';
import {Property} from './../property.model';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({selector: 'app-property-edit-form',
 templateUrl: './property-edit-form.component.html',
  styleUrls: ['./property-edit-form.component.css']})
export class PropertyEditFormComponent implements OnInit {
  // ID
  public id : String;
  public property : Property;
  public propertyFormGroup : FormGroup;

  constructor(public activeRoute : ActivatedRoute,
     private router : Router, private location : Location,
     private propertyService : PropertyService) {}

  ngOnInit() {
    this.property = new Property();
    const id : String = this.activeRoute.snapshot.params['id'];

    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const property : Property = new Property();
      if (property !== null) {
        this.property = property;
      }
    }
  }

  /**
 * 返回
 */
  back() : void {
    this
      .location
      .back();
  }


  onSubmit(): void {
    alert(JSON.stringify(this.property));
  }
}
