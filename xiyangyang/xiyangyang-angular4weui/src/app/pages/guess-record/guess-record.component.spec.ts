import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessRecordComponent } from './guess-record.component';

describe('GuessRecordComponent', () => {
  let component: GuessRecordComponent;
  let fixture: ComponentFixture<GuessRecordComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessRecordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
