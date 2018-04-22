import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemCategoryManageComponent } from './mem-category-manage.component';

describe('MemCategoryManageComponent', () => {
  let component: MemCategoryManageComponent;
  let fixture: ComponentFixture<MemCategoryManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemCategoryManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemCategoryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
