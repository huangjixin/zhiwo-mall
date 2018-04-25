import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemCategoryListComponent } from './mem-category-list.component';

describe('MemCategoryListComponent', () => {
  let component: MemCategoryListComponent;
  let fixture: ComponentFixture<MemCategoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemCategoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
