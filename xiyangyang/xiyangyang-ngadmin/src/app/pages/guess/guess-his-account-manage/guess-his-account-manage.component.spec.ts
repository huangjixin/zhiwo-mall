import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessHisAccountManageComponent } from './guess-his-account-manage.component';

describe('GuessHisAccountManageComponent', () => {
  let component: GuessHisAccountManageComponent;
  let fixture: ComponentFixture<GuessHisAccountManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessHisAccountManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessHisAccountManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
