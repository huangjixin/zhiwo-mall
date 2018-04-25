import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessQuestionListComponent } from './guess-question-list.component';

describe('GuessQuestionListComponent', () => {
  let component: GuessQuestionListComponent;
  let fixture: ComponentFixture<GuessQuestionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessQuestionListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessQuestionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
