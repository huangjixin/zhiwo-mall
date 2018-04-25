import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemCategoryEditFormComponent } from './mem-category-edit-form.component';

describe('MemCategoryEditFormComponent', () => {
  let component: MemCategoryEditFormComponent;
  let fixture: ComponentFixture<MemCategoryEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemCategoryEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemCategoryEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
