import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessCategoryListComponent } from './guess-category-list.component';

describe('GuessCategoryListComponent', () => {
  let component: GuessCategoryListComponent;
  let fixture: ComponentFixture<GuessCategoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessCategoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
