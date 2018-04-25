import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleEditFormComponent } from './role-edit-form.component';

describe('RoleEditFormComponent', () => {
  let component: RoleEditFormComponent;
  let fixture: ComponentFixture<RoleEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoleEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoleEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
