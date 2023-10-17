import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountdetailsComponent } from './accountdetails/accountdetails.component';
import { CalldetailsComponent } from './calldetails/calldetails.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { MessagedialogComponent } from './messagedialog/messagedialog.component';
import { SmsdetailsComponent } from './smsdetails/smsdetails.component';

const routes: Routes = [
  { path: '', component: LoginpageComponent }, // Default 
  { path: 'home', component: HomepageComponent },
  { path: 'accountdetails', component: AccountdetailsComponent },
  { path: 'calldetails', component: CalldetailsComponent },
  { path: 'smsdetails', component: SmsdetailsComponent },
  { path: 'messagedialog', component: MessagedialogComponent }

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
