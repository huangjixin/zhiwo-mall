import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessQuestionEditFormComponent } from './guess-question-edit-form.component';

describe('GuessQuestionEditFormComponent', () => {
  let component: GuessQuestionEditFormComponent;
  let fixture: ComponentFixture<GuessQuestionEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessQuestionEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessQuestionEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
