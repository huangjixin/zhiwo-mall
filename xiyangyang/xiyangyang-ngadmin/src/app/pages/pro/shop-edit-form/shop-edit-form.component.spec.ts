import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopEditFormComponent } from './shop-edit-form.component';

describe('ShopEditFormComponent', () => {
  let component: ShopEditFormComponent;
  let fixture: ComponentFixture<ShopEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShopEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
