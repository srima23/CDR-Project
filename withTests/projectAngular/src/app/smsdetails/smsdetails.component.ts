import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/Status';
import { Location } from '../location.interface';
import { MessagedialogComponent } from '../messagedialog/messagedialog.component';
import { SmsService } from '../sms.Service';
import { SmsData } from '../sms.interface';

@Component({
  selector: 'app-smsdetails',
  templateUrl: './smsdetails.component.html',
  styleUrls: ['./smsdetails.component.css']
})
export class SmsdetailsComponent implements OnInit {
  subscriberSearchInput: any;
  receiverSearchInput: any;

  filteredLocations: { [key: string]: Location[] } = {
    subscriber: [],
    receiver: []
  };

  newdata: SmsData = {
    subscriberNum: 0,
    receiverNum: 0,
    date: "",
    time: "",
    subscriberLoc: "",
    receiverLoc: "",
    smsType: "",
    status: ""
  };

  status: Status = { "status": "" };
  constructor(private smsService: SmsService, public dialog: MatDialog) { }

  isSubscriberNumValid(): boolean {
    const subscriberNum = this.newdata.subscriberNum.toString(); // Convert to string
    const mobileNumberPattern = /^[0-9]{10}$/;
    return mobileNumberPattern.test(subscriberNum);
  }

  isReceiverrNumValid(): boolean {
    const subscriberNum = this.newdata.subscriberNum.toString(); // Convert to string
    const mobileNumberPattern = /^[0-9]{10}$/;
    return mobileNumberPattern.test(subscriberNum);
  }
  ngOnInit(): void {
    this.smsService.loadLocationsFromCSV('subscriber');
    this.filteredLocations['subscriber'] = this.smsService.getLocations('subscriber');

    this.smsService.loadLocationsFromCSV('receiver');
    this.filteredLocations['receiver'] = this.smsService.getLocations('receiver');
  }

  filterLocations(searchInput: string, type: string) {
    if (!searchInput) {
      this.filteredLocations[type] = [];
      return;
    }

    this.filteredLocations[type] = this.smsService.getLocations(type).filter(location =>
      location.name.toLowerCase().includes(searchInput.toLowerCase())
    );
  }

  selectLocation(selectedLocation: Location, type: string) {
    if (type === 'subscriber') {
      this.subscriberSearchInput = selectedLocation.name;
    } else if (type === 'receiver') {
      this.receiverSearchInput = selectedLocation.name;
    }
    this.filteredLocations[type] = [];
  }


  onSubmit() {
    this.newdata.receiverLoc = this.receiverSearchInput;
    this.newdata.subscriberLoc = this.subscriberSearchInput;
    if (this.isFormValid()) {
      // Handle form submission here
      console.log('Form submitted!', this.newdata);
      // Add logic to submit data or perform actions as needed
      this.smsService.saveCDRSms(this.newdata).subscribe(res => {
        console.log("Baby")
        if (res.status === 'added successfully') {
          this.openDialog('Message Details', 'Successfully recorded');
        } else {
          this.openDialog('Message Details', 'Error occurred while saving data');
        }
      });
    } else {
      this.openDialog('Message Details', 'Fill in all the fields');
    }
  }

  isFormValid(): boolean {
    // Check if all fields in this.newdata are filled
    if (this.newdata && this.newdata.smsType &&
      this.newdata.receiverNum && this.newdata.subscriberNum &&
      this.newdata.receiverLoc !== undefined && this.newdata.subscriberLoc !== undefined &&
      this.newdata.date && this.newdata.time !== undefined) {
      return true;
    }
    return false;

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




