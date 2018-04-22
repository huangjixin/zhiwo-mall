import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessAccountManageComponent } from './guess-account-manage.component';

describe('GuessAccountManageComponent', () => {
  let component: GuessAccountManageComponent;
  let fixture: ComponentFixture<GuessAccountManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessAccountManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessAccountManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
