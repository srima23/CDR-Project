import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/Status';
import { MessagedialogComponent } from '../messagedialog/messagedialog.component';
import { SmsService } from '../sms.Service';
import { SmsData } from '../sms.interface';

@Component({
  selector: 'app-smsdetails',
  templateUrl: './smsdetails.component.html',
  styleUrls: ['./smsdetails.component.css']
})
export class SmsdetailsComponent {


  constructor(private smsService: SmsService, public dialog: MatDialog) { }
  newdata: SmsData = {
    subscriberId: 0,
    receiverId: 0,
    date: "",
    time: "",
    subscriberLoc: "",
    receiverLoc: "",
    smsType: "",
    status: ""
  };

  status: Status = { "status": "" };

  onSubmit() {
    if (!this.isFormValid()) {
      // Handle form submission here
      console.log('Form submitted!', this.newdata);
      // Add logic to submit data or perform actions as needed
      this.smsService.saveCDRSms(this.newdata).subscribe(res => {
        if (res.status === 'added successfully') {
          this.openDialog('Call Details', 'Successfully recorded');
        } else {
          this.openDialog('Call Details', 'Error occurred while saving data');
        }
      });
    } else {
      this.openDialog('Call Details', 'Fill in all the fields');
    }
  }

  isFormValid(): boolean {
    // Check if all fields in this.newdata are filled
    if (this.newdata && this.newdata.smsType &&
      this.newdata.receiverLoc !== undefined && this.newdata.subscriberLoc !== undefined &&
      this.newdata.receiverId && this.newdata.subscriberId &&
      this.newdata.date && this.newdata.time !== undefined) {
      return false;


    }
    return true;

  }

  openDialog(title: string, message: string): void {
    const dialogRef = this.dialog.open(MessagedialogComponent, {
      width: '250px',
      data: { title, message }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }






}




