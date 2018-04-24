import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessOptionsEditFormComponent } from './guess-options-edit-form.component';

describe('GuessOptionsEditFormComponent', () => {
  let component: GuessOptionsEditFormComponent;
  let fixture: ComponentFixture<GuessOptionsEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessOptionsEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessOptionsEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
