import { Component, OnInit, Output, EventEmitter, AfterViewInit } from '@angular/core';

declare const gapi: any;

@Component({
  selector: 'app-google-signout',
  templateUrl: './google-signout.component.html',
  styleUrls: ['./google-signout.component.scss']
})
export class GoogleSignoutComponent implements AfterViewInit {

  private clientId: string = '842559739559-44i02i0se3q9a1j06ns081731phou72t.apps.googleusercontent.com';

  private scope = [
    'profile',
  ].join(' ');

  auth2: any;

  @Output()
  private signout: EventEmitter<null> = new EventEmitter();

  constructor() { }

  ngAfterViewInit(): void {
    this.googleInit();
  }

  signOutHandler() {
    this.auth2.signOut().then(() => {
      this.signout.emit(null);
    });
  }

  googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: this.clientId,
        cookiepolicy: 'single_host_origin',
        scope: this.scope
      });
    });
  }
}
