import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessAccountListComponent } from './guess-account-list.component';

describe('GuessAccountListComponent', () => {
  let component: GuessAccountListComponent;
  let fixture: ComponentFixture<GuessAccountListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessAccountListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessAccountListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
