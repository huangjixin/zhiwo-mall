import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddredssComponentComponent } from './addredss-component.component';

describe('AddredssComponentComponent', () => {
  let component: AddredssComponentComponent;
  let fixture: ComponentFixture<AddredssComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddredssComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddredssComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
