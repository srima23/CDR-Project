import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagedialogComponent } from './messagedialog.component';

describe('MessagedialogComponent', () => {
  let component: MessagedialogComponent;
  let fixture: ComponentFixture<MessagedialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MessagedialogComponent]
    });
    fixture = TestBed.createComponent(MessagedialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
