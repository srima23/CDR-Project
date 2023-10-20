import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/Status';
import { CustomersData } from '../customers.interface';
import { CustomersService } from '../customers.service';
import { Location } from '../location.interface';
import { MessagedialogComponent } from '../messagedialog/messagedialog.component';


@Component({
  selector: 'app-accountdetails',
  templateUrl: './accountdetails.component.html',
  styleUrls: ['./accountdetails.component.css']
})

export class AccountdetailsComponent implements OnInit {


  filteredLocations: Location[] = [];  // Define filteredLocations array
  searchInput: any;

  details: CustomersData = {
    id: 0,
    name: "",
    number: "",
    location: "",
    gender: ""
  };
  
  ngOnInit(): void {
  
    // Populate filteredLocations with data from the service
    this.customersService.loadLocationsFromCSV();
    console.log("heyyyyyyyy")
    this.filteredLocations = this.customersService.getLocations();
    console.log(this.filteredLocations);
    console.log(" I am din something wndeful ");

  }

  status: Status = { "status": "" };
  constructor(private customersService: CustomersService,public dialog: MatDialog) { }

  isNumberValid: boolean = true;

validateNumber() {
  const inputNumber = this.details.number;
  this.isNumberValid = /^[1-9][0-9]{9}$/.test(inputNumber);
}

isAccountNameValid: boolean = true;

validateAccountName() {
  const accountName: string = this.details.name;
  this.isAccountNameValid = !!accountName.trim(); // Check if it's not empty or only whitespace
}



  filterLocations() {
    if (!this.searchInput) {
      this.filteredLocations = [];
      return;
    }
  
    this.filteredLocations = this.customersService.getLocations().filter(location =>
      location.name.toLowerCase().includes(this.searchInput.toLowerCase())
    );
  }

  selectLocation(selectedLocation: Location) {
    this.searchInput = selectedLocation.name;
    this.filteredLocations = []; // Clear the suggestions after selection
  }

  
  onSubmit() {

    if(!this.isFormValid()){

    console.log('Form submitted!', this.details);
    this.details.location = this.searchInput;
    this.customersService.saveCustomersDetails(this.details).subscribe(res => {
      if (res.status === 'added successfully') {
        console.log('hello')
        this.openDialog('Customer Details', 'Successfully recorded');
      } else {
        this.openDialog('Customer Details', 'Error occurred while saving data');
      }
    });
    
  }else {
    this.openDialog('Call Details', 'Fill in all the fields');
  }

    }

  isFormValid(): Boolean {
    if (this.details && this.details.name !== undefined && this.details.number &&
      this.details.location !== undefined && this.details.gender !== undefined) 
   {
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

  
  
  

  // detailsForm: FormGroup;
  // filteredLocations: Location[] = [];
  // searchInput: string = '';
  // details: any;

  // constructor(private customersService: CustomersService) {
  // this.detailsForm = this.formBuilder.group({
  //   accountName: ['', Validators.required],
  //   number: ['', [Validators.required, Validators.pattern('^[1-9][0-9]{9}$')]],
  //   location: [''],
  //   gender: ['']
  // });










// onSubmit() {
//   if (this.detailsForm.invalid) {
//     console.log('Form is invalid. Please check the fields.');
//     return;
//   }

  

  // const details: CustomersData = {
  //   id: 0,
  //   name: this.detailsForm.value.accountName,
  //   number: this.detailsForm.value.number,
  //   location: this.detailsForm.value.location,
  //   gender: this.detailsForm.value.gender
  // };

  // console.log('Form submitted!', details);
  // this.customersService.saveCustomersDetails(details).subscribe(
  //   (res: Status) => {
  //     this.status = res;
  //   },
  //   (error) => {
  //     console.error('Error saving customer details:', error);
  //   }
  // );



// filterLocations() {
//   if (!this.searchInput) {
//     this.filteredLocations = [];
//     return;
//   }

//   this.filteredLocations = this.customersService.getLocations().filter(location =>
//     location.name.toLowerCase().includes(this.searchInput.toLowerCase())
//   );
// }

//   selectLocation(selectedLocation: Location) {
//     this.searchInput = selectedLocation.name;
//     this.filteredLocations = [];
//   }

//   status: Status = { status: '' };
// }






