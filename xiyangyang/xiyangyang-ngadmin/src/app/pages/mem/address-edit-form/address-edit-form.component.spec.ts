import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressEditFormComponent } from './address-edit-form.component';

describe('AddressEditFormComponent', () => {
  let component: AddressEditFormComponent;
  let fixture: ComponentFixture<AddressEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddressEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
