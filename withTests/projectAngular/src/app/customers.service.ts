import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from 'src/Status';
import { CustomersData } from './customers.interface';
import { Location } from './location.interface';

// interface Location {
//   name: string;
// }


@Injectable({
  providedIn: 'root'
})


export class CustomersService {

  private locations: Location[] = [];  // Array to store all locations
  private filteredLocations: Location[] = [];
  searchInput: any;

  constructor(private http: HttpClient) { }

  saveLocations(locations: Location[]) {
    console.log("I AM UODHJDB");
    this.locations = locations;
  }

  getLocations(): Location[] {
    return this.locations;
  }

  setFilteredLocations(filteredLocations: Location[]) {
    this.filteredLocations = filteredLocations;
  }

  getFilteredLocations(): Location[] {
    return this.filteredLocations;
  }

  loadLocationsFromCSV() {
    // Load CSV file and parse data
    console.log("i ama thebmai  donkey here");
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {

        this.locations = this.parseCSVData(xhr.responseText);
        this.saveLocations(this.locations);
      }
    };
    xhr.open('GET', 'assets/locations.csv', true);
    xhr.send();
  }

  private parseCSVData(csvData: string): Location[] {
    const lines = csvData.split('\n');
    const locations: Location[] = [];
    for (let i = 1; i < lines.length; i++) {
      const line = lines[i].trim();
      if (line) {
        const parts = line.split(',');
        if (parts.length > 0) {
          locations.push({ name: parts[0] });
        }
      }
    }
    return locations;
  }

  private filterLocations() {
    if (this.searchInput) {
      const filteredLocations = this.locations.filter(location =>
        location.name.toLowerCase().includes(this.searchInput.toLowerCase())
      );
      this.setFilteredLocations(filteredLocations);
    } else {
      this.setFilteredLocations([]);
    }
  }

  private selectLocation(location: Location) {
    this.searchInput = location.name;
    this.filterLocations();
  }

  saveCustomersDetails(customersData: CustomersData): Observable<Status> {
    console.log("I am in service");
    return this.http.post<Status>("http://localhost:8080/api/home/saveCustomers", customersData);
  }



}
