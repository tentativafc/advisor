import { Component, AfterViewInit, ElementRef, Output } from '@angular/core';
import { UserSessionDetails } from 'src/app/modules/shared/model/user-session-details';
import { EventEmitter } from "@angular/core";

declare const gapi: any;

@Component({
  selector: 'app-google-signin',
  templateUrl: './google-signin.component.html',
  styleUrls: ['./google-signin.component.scss']
})
export class GoogleSigninComponent implements AfterViewInit {

  private clientId: string = '842559739559-44i02i0se3q9a1j06ns081731phou72t.apps.googleusercontent.com';

  private scope = [
    'profile',
    'email',
    'https://www.googleapis.com/auth/plus.me',
    'https://www.googleapis.com/auth/contacts.readonly',
    'https://www.googleapis.com/auth/admin.directory.user.readonly'
  ].join(' ');

  public auth2: any;

googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: this.clientId,
        cookiepolicy: 'single_host_origin',
        scope: this.scope
      });
      this.attachSignin(this.element.nativeElement.firstChild);
    });
  }

  attachSignin(element) {
    this.auth2.attachClickHandler(element, {}, (googleUser) => {
      let profile = googleUser.getBasicProfile();

      let sessionDetails = new UserSessionDetails();
      sessionDetails.google_access_token = googleUser.getAuthResponse().id_token
      sessionDetails.google_user_id = profile.getId()
      sessionDetails.name = profile.getName();
      sessionDetails.email = profile.getEmail();
      sessionDetails.image_url = profile.getImageUrl();

      console.log("Return from google" + sessionDetails);
      this.userSessionDetails.emit(sessionDetails);

    }, (err) => {
      console.log(JSON.stringify(err, undefined, 2));
    });
  }

  @Output()
  private userSessionDetails: EventEmitter<UserSessionDetails>;

  constructor(private element: ElementRef) {
    this.userSessionDetails = new EventEmitter()
  }

  ngAfterViewInit(): void {
    this.googleInit();
  }

}
