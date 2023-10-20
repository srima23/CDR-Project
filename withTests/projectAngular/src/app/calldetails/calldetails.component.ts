import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/Status';
import { CallData } from '../call.interface';
import { CallService } from '../call.service';
import { Location } from '../location.interface';
import { MessagedialogComponent } from '../messagedialog/messagedialog.component';

@Component({
  selector: 'app-calldetails',
  templateUrl: './calldetails.component.html',
  styleUrls: ['./calldetails.component.css']
})
export class CalldetailsComponent implements OnInit {

  // filteredLocations: { [key: string]: Location[] } = {};  // Define filteredLocations object

  subscriberSearchInput: any;
  receiverSearchInput: any;

  subscriberNumberInput: any;
  receiverNumberInput: any;


  filteredLocations: { [key: string]: Location[] } = {
    subscriber: [],
    receiver: []
  };

  filteredNumbers: { [key: string]: string[] } = {
    subscriber: [],
    receiver: []
  };

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

  constructor(private callService: CallService, public dialog: MatDialog, private formBuilder: FormBuilder) { }


  // Inside your component class
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
    this.callService.loadLocationsFromCSV('subscriber');
    this.filteredLocations['subscriber'] = this.callService.getLocations('subscriber');

    this.callService.loadLocationsFromCSV('receiver');
    this.filteredLocations['receiver'] = this.callService.getLocations('receiver');

    this.callService.loadNumbersFromCSV('subscriber');
    this.filteredNumbers['subscriber'] = this.callService.getNumbers('subscriber');

    this.callService.loadNumbersFromCSV('receiver');
    this.filteredNumbers['receiver'] = this.callService.getNumbers('receiver');
  }


  // filteredLocations: Location[] = [];  // Define filteredLocations array
  // searchInput: any;

  // ngOnInit(): void {
  //   // Populate filteredLocations with data from the service
  //   this.callService.loadLocationsFromCSV();
  //   this.filteredLocations = this.callService.getLocations();
  //   console.log(this.filteredLocations);
  //   console.log(" location fetched");

  // }



  filterLocations(searchInput: string, type: string) {
    if (!searchInput) {
      this.filteredLocations[type] = [];
      return;
    }

    this.filteredLocations[type] = this.callService.getLocations(type).filter(location =>
      location.name.toLowerCase().includes(searchInput.toLowerCase())
    );
  }



  // filterLocations() {
  //   if (!this.searchInput) {
  //     this.filteredLocations = [];
  //     return;
  //   }

  //   this.filteredLocations = this.callService.getLocations().filter(location =>
  //     location.name.toLowerCase().includes(this.searchInput.toLowerCase())
  //   );
  // }

  // selectLocation(selectedLocation: Location, type: string) {
  //   this.searchInput = selectedLocation.name;
  //   this.filteredLocations[type] = [];
  //   // Clear the suggestions after selection
  // }

  selectLocation(selectedLocation: Location, type: string) {
    if (type === 'subscriber') {
      this.subscriberSearchInput = selectedLocation.name;
    } else if (type === 'receiver') {
      this.receiverSearchInput = selectedLocation.name;
    }
    this.filteredLocations[type] = [];
  }

  filterNumbers(searchInput: string, type: string) {
    if (searchInput) {
      this.filteredNumbers[type] = this.callService.getNumbers(type)
        .filter(number => number.includes(searchInput));
    } else {
      this.filteredNumbers[type] = [];
    }
  }

  // selectNumber(number: string, type: string) {
  //   if (type === 'subscriber') {
  //     this.subscriberSearchInput = number;
  //     this.filteredNumbers['subscriber'] = [];
  //   } else if (type === 'receiver') {
  //     this.receiverSearchInput = number;
  //     this.filteredNumbers['receiver'] = [];
  //   }
  // }

  onSubmit() {
    console.log('Form submitted!', this.newdata);
    if (this.isFormValid()) {
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
    this.newdata.receiverLoc = this.receiverSearchInput;
    this.newdata.subscriberLoc = this.subscriberSearchInput;
    if (this.newdata && this.newdata.callType && this.newdata.duration &&
      this.newdata.receiverLoc !== undefined && this.newdata.subscriberLoc !== undefined &&
      this.newdata.receiverNum && this.newdata.subscriberNum &&
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
