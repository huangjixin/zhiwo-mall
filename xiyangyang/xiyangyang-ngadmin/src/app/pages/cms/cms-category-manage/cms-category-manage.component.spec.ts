import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CmsCategoryManageComponent } from './cms-category-manage.component';

describe('CmsCategoryManageComponent', () => {
  let component: CmsCategoryManageComponent;
  let fixture: ComponentFixture<CmsCategoryManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CmsCategoryManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CmsCategoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
