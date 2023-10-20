import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../customers.service';

interface Location {
  name: string;
}

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css']
})
export class LocationComponent implements OnInit {
  private locations: Location[] = []; // Array to store locations from CSV
  private searchInput: string = '';

  constructor(private customerService: CustomersService) {

  }
  ngOnInit(): void {
    this.loadLocationsFromCSV();
  }

  private loadLocationsFromCSV() {
    // Load CSV file and parse data
    console.log("i ama thebmai  donkey here");
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {

        this.locations = this.parseCSVData(xhr.responseText);
        this.customerService.saveLocations(this.locations);
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
      this.customerService.setFilteredLocations(filteredLocations);
    } else {
      this.customerService.setFilteredLocations([]);
    }
  }

  private selectLocation(location: Location) {
    this.searchInput = location.name;
    this.filterLocations();
  }
}
