import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesEditFormComponent } from './resources-edit-form.component';

describe('ResourcesEditFormComponent', () => {
  let component: ResourcesEditFormComponent;
  let fixture: ComponentFixture<ResourcesEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourcesEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcesEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
