import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopicEditFormComponent } from './topic-edit-form.component';

describe('TopicEditFormComponent', () => {
  let component: TopicEditFormComponent;
  let fixture: ComponentFixture<TopicEditFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopicEditFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopicEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
