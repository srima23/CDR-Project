import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from 'src/Status';
import { SmsData } from './sms.interface';

@Injectable({
    providedIn: 'root'
})
export class SmsService {

    constructor(private http: HttpClient) { }

    saveCDRSms(smsData: SmsData): Observable<Status> {
        console.log("I am in service");
        return this.http.post<Status>("http://localhost:8080/api/home/saveSmsCDR", smsData);
    }
}


