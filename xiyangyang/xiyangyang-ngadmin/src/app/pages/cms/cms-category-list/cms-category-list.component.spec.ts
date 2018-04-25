import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CmsCategoryListComponent } from './cms-category-list.component';

describe('CmsCategoryListComponent', () => {
  let component: CmsCategoryListComponent;
  let fixture: ComponentFixture<CmsCategoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CmsCategoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CmsCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
