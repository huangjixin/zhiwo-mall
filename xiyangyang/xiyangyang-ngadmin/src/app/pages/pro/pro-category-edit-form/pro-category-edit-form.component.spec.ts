import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProCategoryEditFormComponent } from './pro-category-edit-form.component';

describe('ProCategoryEditFormComponent', () => {
  let component: ProCategoryEditFormComponent;
  let fixture: ComponentFixture<ProCategoryEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProCategoryEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProCategoryEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
