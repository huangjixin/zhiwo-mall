import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessHisAccountListComponent } from './guess-his-account-list.component';

describe('GuessHisAccountListComponent', () => {
  let component: GuessHisAccountListComponent;
  let fixture: ComponentFixture<GuessHisAccountListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessHisAccountListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessHisAccountListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
