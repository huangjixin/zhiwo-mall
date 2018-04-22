import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuessCateogryManageComponent } from './guess-cateogry-manage.component';

describe('GuessCateogryManageComponent', () => {
  let component: GuessCateogryManageComponent;
  let fixture: ComponentFixture<GuessCateogryManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuessCateogryManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuessCateogryManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
