import { HttpClient } from "@angular/common/http";
import { Component, Injectable } from '@angular/core';
import { Router } from "@angular/router";



@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']

})

export class HomepageComponent {

  constructor(private router: Router, private _http: HttpClient) { }

  navigateToAccountDetails() {
    this.router.navigate(['/account-details']);
  }

  navigateToCallDetails() {
    this.router.navigate(['/call-details']);
  }

  navigateToSmsDetails() {
    this.router.navigate(['/sms-details']);
  }


  //getting list of cycle data from this endpoint
  // getallcdrfiles() {
  //   // const headers = new HttpHeaders({

  //   //   'Authorization': 'Bearer ' + localStorage.getItem('token')

  //   // });
  //   return this._http.get('http://localhost:8080/api/auth/list-data');
  // }

  // //show data immidiatly on load
  // ngOnInit() {
  //   this.getallcdrfiles().subscribe({
  //     next: (res) => {
  //       this.newdata = res;
  //       console.log('Success: Response from API:', this.newdata);
  //     },
  //     error: (error) => {
  //       console.error('Error: Failed to fetch data from API:', error);
  //     }
  //   });


}

