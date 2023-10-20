import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from 'src/Status';
import { CallData } from './call.interface';
import { Location } from './location.interface';

@Injectable({
  providedIn: 'root'
})
export class CallService {

  private locations: { [key: string]: Location[] } = {
    subscriber: [],
    receiver: []
  };
  private filteredLocations: { [key: string]: Location[] } = {
    subscriber: [],
    receiver: []
  };
  private numbers: { [key: string]: string[] } = {
    subscriber: [],
    receiver: []
  };
  subscriberSearchInput: any;
  receiverSearchInput: any;

  // subscriberNumbers: string[] = [];
  // receiverNumbers: string[] = [];



  constructor(private http: HttpClient) { }


  saveLocations(locations: Location[], type: string) {
    console.log("I AM call service for type:", type);
    this.locations[type] = locations;
  }

  getLocations(type: string): Location[] {
    return this.locations[type];
  }

  setFilteredLocations(filteredLocations: Location[], type: string) {
    this.filteredLocations[type] = filteredLocations;
  }

  getFilteredLocations(type: string): Location[] {
    return this.filteredLocations[type];
  }

  loadNumbersFromCSV(type: string) {
    const csvFile = type === 'subscriber' ? 'assets/cdr_customers.csv' : 'assets/cdr_customers.csv';

    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        const parsedNumbers = this.parseNUmbersCSVData(xhr.responseText);  // Fix: call the updated method
        this.saveNumbers(parsedNumbers, type);
      }
    };
    xhr.open('GET', `assets/cdr_customers.csv`, true);
    xhr.send();
  }


  private saveNumbers(numbers: string[], type: string) {
    this.numbers[type] = numbers;
  }


  getNumbers(type: string): string[] {
    return this.numbers[type];
  }

  private parseNUmbersCSVData(csvData: string): string[] {
    const lines = csvData.split('\n');
    const numbers: string[] = [];
    for (let i = 1; i < lines.length; i++) {
      const line = lines[i].trim();
      if (line) {
        numbers.push(line);
      }
    }
    return numbers;
  }



  loadLocationsFromCSV(type: string) {
    // Load CSV file and parse data
    console.log("I am call service happening for type:", type);
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
      if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        const parsedLocations = this.parseCSVData(xhr.responseText);
        this.saveLocations(parsedLocations, type);
      }
    };
    xhr.open('GET', `assets/locations.csv`, true);
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

  filterLocations(searchInput: string, type: string) {
    if (searchInput) {
      const filteredLocations = this.locations[type].filter(location =>
        location.name.toLowerCase().includes(searchInput.toLowerCase())
      );
      this.setFilteredLocations(filteredLocations, type);
    } else {
      this.setFilteredLocations([], type);
    }
  }
  filterNumbers(searchInput: string, type: string): string[] {
    if (!searchInput) {
      return [];
    }

    // Filter numbers based on search input
    return this.numbers[type].filter(num => num.toString().includes(searchInput));
  }





  selectLocation(location: Location, type: string) {
    if (type === 'subscriber') {
      this.subscriberSearchInput = location.name;
      this.filterLocations(this.subscriberSearchInput, 'subscriber');
    } else if (type === 'receiver') {
      this.receiverSearchInput = location.name;
      this.filterLocations(this.receiverSearchInput, 'receiver');
    }

    // private filterLocations() {
    //   if (this.searchInput) {
    //     const filteredLocations = this.locations.filter(location =>
    //       location.name.toLowerCase().includes(this.searchInput.toLowerCase())
    //     );
    //     this.setFilteredLocations(filteredLocations);
    //   } else {
    //     this.setFilteredLocations([]);
    //   }
  }
  selectNumber(selectedNumber: string, type: string) {
    if (type === 'subscriber') {
      this.subscriberSearchInput = selectedNumber;
    } else if (type === 'receiver') {
      this.receiverSearchInput = selectedNumber;
    }
  }


  // private selectLocation(location: Location) {
  //   this.searchInput = location.name;
  //   this.filterLocations();
  // }



  saveCDRCall(callData: CallData): Observable<Status> {
    console.log("I am in service");
    return this.http.post<Status>("http://localhost:8080/api/home/saveCallCDR", callData);
  }
}
