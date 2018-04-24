import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessAccountEditFormComponent } from './guess-account-edit-form.component';

describe('GuessAccountEditFormComponent', () => {
  let component: GuessAccountEditFormComponent;
  let fixture: ComponentFixture<GuessAccountEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessAccountEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessAccountEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
