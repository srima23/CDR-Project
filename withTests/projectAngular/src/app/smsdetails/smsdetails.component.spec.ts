import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmsdetailsComponent } from './smsdetails.component';

describe('SmsdetailsComponent', () => {
  let component: SmsdetailsComponent;
  let fixture: ComponentFixture<SmsdetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmsdetailsComponent]
    });
    fixture = TestBed.createComponent(SmsdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
