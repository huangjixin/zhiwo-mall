import { Property } from './../property.model';
import { PropertyValue } from './../property-value.model';
import { Product } from './../pro-product.model';
import { ProductService } from './../product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {Location} from '@angular/common';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({selector: 'app-product-edit-form',
 templateUrl: './product-edit-form.component.html',
  styleUrls: ['./product-edit-form.component.css']})
export class ProductEditFormComponent implements OnInit {

  // ID
  public id : String;
  public product : Product;
  public productFormGroup : FormGroup;

  // 是否显示属性值表单。
  public showPValueForm: Boolean = false;

  // 选中的属性值；
  public propertyValue: PropertyValue;

  public mode: String = 'new';

  public properties:Array<Property>;

  public propertyId;
  public property: any;

  public propertiesData = [{value: 11, text: 'Mr. Nice'},{value: 12, text: 'Narco'},{value: 13, text: 'Bombasto'}];

  constructor(public activeRoute : ActivatedRoute,
     private router : Router,
     private location : Location, private productService : ProductService) {

    this.properties = [];
    let property: Property = new Property();
    property.id = '1';
    property.value = '1';
    property.name = '尺码';
    property.text = '尺码';
    this.properties.push(property);
    property = new Property();
    property.id = '2';
    property.value = '2';
    property.name = '规格';
    property.text = '规格';
    this.properties.push(property);
    property = new Property();
    property.id = '3';
    property.value = '3';
    property.name = '风格';
    property.text = '风格';
    this.properties.push(property);
  }

  ngOnInit() {
    this.product = new Product();
    const id : String = this.activeRoute.snapshot.params['id'];
    this.product.id = id;
    this.id = id;
    if (this.id !== null && this.id !== undefined) {
      const product : Product = new Product();
      if (product !== null) {
        this.product = product;
      }
    }
    this.product.id = id;
  }

  /**
   * 返回
   */
  back() : void {
    this
      .location
      .back();
  }

  onSubmit() : void {
    alert(JSON.stringify(this.product));
  }

  onPValueEditRow(row) : void {
    this.mode = 'edit';
    this.showPValueForm = true;
    this.propertyValue = row;
  }

  onPValueDeleteRow(row) : void {
    const index: number = this
      .product
      .propertyValues
      .indexOf(row);
    this
      .product
      .propertyValues
      .splice(index, 1);
      const temp = this.product.propertyValues.concat();
      this.product.propertyValues = temp;
  }

  addPropertyValue(): void {
    this.mode = 'new';
    this.showPValueForm = true;
    this.propertyValue = new PropertyValue();
  }

  savePValue(): void {
    if ( this.mode === 'new') {
      if (this.propertyValue.name === '') {
        return;
      }
      this.propertyValue.propertyId = this.propertyId;

      this.properties.forEach(prop => {
        if(prop.id === this.propertyId){
          this.property = prop;
        }
      });
      const property: Property = new Property();
      property.name = this.property.name;
      property.id = this.property.id;
      this.propertyValue.property = property;

      this
        .product.propertyValues
        .push(this.propertyValue);
        const temp = this.product.propertyValues.concat();
        this.product.propertyValues = temp;
        this.propertyValue = new PropertyValue();
    } else if (this.mode === 'edit') {

    }
  }

}
