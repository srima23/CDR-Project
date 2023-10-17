import { Component, OnInit } from '@angular/core';
import { Status } from 'src/Status';
import { CustomersData } from '../customers.interface';
import { CustomersService } from '../customers.service';

interface Location {
  name: string;
}

@Component({
  selector: 'app-accountdetails',
  templateUrl: './accountdetails.component.html',
  styleUrls: ['./accountdetails.component.css']
})
export class AccountdetailsComponent implements OnInit {
  

  filteredLocations: Location[] = [];  // Define filteredLocations array
  searchInput: any;

  ngOnInit(): void {
    // Populate filteredLocations with data from the service
    this.customersService.loadLocationsFromCSV();
    this.filteredLocations = this.customersService.getLocations();
    console.log(this.filteredLocations);
    console.log(" I am din something wndeful");

  }
  constructor(private customersService: CustomersService) { }
  details: CustomersData = {
    id: 0,
    name: "",
    number: "",
    location: "",
    gender: ""
  };
  status: Status = { "status": "" };

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
    console.log('Form submitted!', this.details);
    this.details.location = this.searchInput;
    this.customersService.saveCustomersDetails(this.details).subscribe(res => this.status = res);
    // Add logic to submit data or perform actions as needed
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






