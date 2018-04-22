import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcesManageComponent } from './resources-manage.component';

describe('ResourcesManageComponent', () => {
  let component: ResourcesManageComponent;
  let fixture: ComponentFixture<ResourcesManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourcesManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcesManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
