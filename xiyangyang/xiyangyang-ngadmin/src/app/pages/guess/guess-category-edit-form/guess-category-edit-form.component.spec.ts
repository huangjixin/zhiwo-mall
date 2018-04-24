import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessCategoryEditFormComponent } from './guess-category-edit-form.component';

describe('GuessCategoryEditFormComponent', () => {
  let component: GuessCategoryEditFormComponent;
  let fixture: ComponentFixture<GuessCategoryEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessCategoryEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessCategoryEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
