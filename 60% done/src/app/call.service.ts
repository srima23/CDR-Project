import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from 'src/Status';
import { CallData } from './call.interface';

@Injectable({
  providedIn: 'root'
})
export class CallService {

  constructor(private http: HttpClient) { }

  saveCDRCall(callData: CallData): Observable<Status> {
    console.log("I am in service");
    return this.http.post<Status>("http://localhost:8080/api/home/saveCallCDR", callData);
  }
}
