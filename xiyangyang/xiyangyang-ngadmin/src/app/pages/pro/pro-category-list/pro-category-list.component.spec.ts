import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProCategoryListComponent } from './pro-category-list.component';

describe('ProCategoryListComponent', () => {
  let component: ProCategoryListComponent;
  let fixture: ComponentFixture<ProCategoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProCategoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
