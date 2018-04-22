import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyManageComponent } from './property-manage.component';

describe('PropertyManageComponent', () => {
  let component: PropertyManageComponent;
  let fixture: ComponentFixture<PropertyManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
