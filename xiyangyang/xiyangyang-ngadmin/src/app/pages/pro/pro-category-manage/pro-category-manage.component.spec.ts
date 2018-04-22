import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProCategoryManageComponent } from './pro-category-manage.component';

describe('ProCategoryManageComponent', () => {
  let component: ProCategoryManageComponent;
  let fixture: ComponentFixture<ProCategoryManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProCategoryManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProCategoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
