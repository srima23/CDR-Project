import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from 'src/Status';
import { Location } from './location.interface';
import { SmsData } from './sms.interface';

@Injectable({
    providedIn: 'root'
})
export class SmsService {
    private locations: { [key: string]: Location[] } = {
        subscriber: [],
        receiver: []
    };
    private filteredLocations: { [key: string]: Location[] } = {
        subscriber: [],
        receiver: []
    };
    subscriberSearchInput: any;
    receiverSearchInput: any;

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

    selectLocation(location: Location, type: string) {
        if (type === 'subscriber') {
            this.subscriberSearchInput = location.name;
            this.filterLocations(this.subscriberSearchInput, 'subscriber');
        } else if (type === 'receiver') {
            this.receiverSearchInput = location.name;
            this.filterLocations(this.receiverSearchInput, 'receiver');
        }
    }



    saveCDRSms(smsData: SmsData): Observable<Status> {
        console.log("I am in service");
        return this.http.post<Status>("http://localhost:8080/api/home/saveSmsCDR", smsData);
    }
}



