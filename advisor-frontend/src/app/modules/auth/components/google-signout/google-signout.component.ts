import { Component, OnInit, Output, EventEmitter, AfterViewInit, NgZone } from '@angular/core';
import { environment } from '../../../../../environments/environment';

declare const gapi: any;

@Component({
  selector: 'app-google-signout',
  templateUrl: './google-signout.component.html',
  styleUrls: ['./google-signout.component.scss']
})
export class GoogleSignoutComponent implements AfterViewInit {

  auth2: any;

  @Output()
  private signout: EventEmitter<null> = new EventEmitter();

  constructor(private ngZone: NgZone) { }

  ngAfterViewInit(): void {
    this.googleInit();
  }

  signOutHandler() {
    this.auth2.signOut().then(() => {
      this.ngZone.run(()=>{
        this.signout.emit(null);
      })
    });
  }

  googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: environment.google_client_id,
        cookiepolicy: 'single_host_origin',
        scope: environment.google_api_profiles
      });
    });
  }
}
