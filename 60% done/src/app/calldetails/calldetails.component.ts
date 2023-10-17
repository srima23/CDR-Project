import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/Status'; // Replace with the correct path
import { CallData } from '../call.interface';
import { CallService } from '../call.service';
import { MessagedialogComponent } from '../messagedialog/messagedialog.component'; // Replace with the correct path

@Component({
  selector: 'app-calldetails',
  templateUrl: './calldetails.component.html',
  styleUrls: ['./calldetails.component.css']
})
export class CalldetailsComponent {
  newdata: CallData = {
    subscriberNum: 0,
    receiverNum: 0,
    date: "",
    time: "",
    duration: 0,
    subscriberLoc: "",
    receiverLoc: "",
    callType: "",
    callStatus: 'Connect',  // Set the default value to 'Connect'
    voicemail: false  // Assuming voicemail default is 'false'
  };

  status: Status = { status: "" };

  constructor(private callService: CallService, public dialog: MatDialog) { }


  onSubmit() {
    if (!this.isFormValid()) {
      // Handle form submission here
      console.log('Form submitted!', this.newdata);
      // Add logic to submit data or perform actions as needed
      this.callService.saveCDRCall(this.newdata).subscribe(res => {
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
    if (this.newdata && this.newdata.callType && this.newdata.duration &&
      this.newdata.receiverLoc !== undefined && this.newdata.subscriberLoc !== undefined &&
      this.newdata.receiverNum && this.newdata.subscriberNum &&
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
