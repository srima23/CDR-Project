import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-messagedialog',
  templateUrl: './messagedialog.component.html',
  styleUrls: ['./messagedialog.component.css']
})
export class MessagedialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }

  get title(): string {
    return this.data.title;
  }

  get message(): string {
    return this.data.message;
  }


}
