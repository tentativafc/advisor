import { Component, AfterViewInit, ElementRef } from '@angular/core';

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

  public googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: this.clientId,
        cookiepolicy: 'single_host_origin',
        scope: this.scope
      });
      this.attachSignin(this.element.nativeElement.firstChild);
    });
  }

  public attachSignin(element) {
    let me = this;
    this.auth2.attachClickHandler(element, {}, (googleUser) => {
      let profile = googleUser.getBasicProfile();
      console.log('Token || ' + googleUser.getAuthResponse().id_token);
      console.log('ID: ' + profile.getId());
      console.log('Name: ' + profile.getName());
      console.log('Image URL: ' + profile.getImageUrl());
      console.log('Email: ' + profile.getEmail());
      //YOUR CODE HERE
    }, (err) => {
      console.log(JSON.stringify(err, undefined, 2));
    });
  }

  constructor(private element: ElementRef) {
  }

  ngAfterViewInit(): void {
    this.googleInit();
  }

}
