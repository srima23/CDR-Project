import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalldetailsComponent } from './calldetails.component';

describe('CalldetailsComponent', () => {
  let component: CalldetailsComponent;
  let fixture: ComponentFixture<CalldetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CalldetailsComponent]
    });
    fixture = TestBed.createComponent(CalldetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
