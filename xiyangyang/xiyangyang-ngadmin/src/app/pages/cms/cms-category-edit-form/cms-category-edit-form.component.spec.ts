import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CmsCategoryEditFormComponent } from './cms-category-edit-form.component';

describe('CmsCategoryEditFormComponent', () => {
  let component: CmsCategoryEditFormComponent;
  let fixture: ComponentFixture<CmsCategoryEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CmsCategoryEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CmsCategoryEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
