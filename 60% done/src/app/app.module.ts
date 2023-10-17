import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AccountdetailsComponent } from './accountdetails/accountdetails.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthInterceptorService } from './auth-interceptor.service';
import { CalldetailsComponent } from './calldetails/calldetails.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { MessagedialogComponent } from './messagedialog/messagedialog.component';


import { LocationComponent } from './location/location.component';
import { SmsdetailsComponent } from './smsdetails/smsdetails.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountdetailsComponent,
    LoginpageComponent,
    HomepageComponent,
    CalldetailsComponent,
    SmsdetailsComponent,
    MessagedialogComponent,
    LocationComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    ReactiveFormsModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
