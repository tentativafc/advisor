import { Component, AfterViewInit, ElementRef, Output, NgZone } from '@angular/core';
import { UserSessionDetails } from 'src/app/modules/shared/model/user-session-details';
import { EventEmitter } from "@angular/core";
import { environment } from '../../../../../environments/environment';

declare const gapi: any;

@Component({
  selector: 'app-google-signin',
  templateUrl: './google-signin.component.html',
  styleUrls: ['./google-signin.component.scss']
})
export class GoogleSigninComponent implements AfterViewInit {

  public auth2: any;

  googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: environment.google_client_id,
        cookiepolicy: 'single_host_origin',
        scope: environment.google_api_profiles
      });
      this.attachSignin(this.element.nativeElement.firstChild);
    });
  }

  attachSignin(element) {
    this.auth2.attachClickHandler(element, {}, (googleUser) => {
      this.ngZone.run(() => {
        let profile = googleUser.getBasicProfile();

        let sessionDetails = new UserSessionDetails();
        sessionDetails.google_access_token = googleUser.getAuthResponse().id_token
        sessionDetails.google_user_id = profile.getId()
        sessionDetails.name = profile.getName();
        sessionDetails.email = profile.getEmail();
        sessionDetails.image_url = profile.getImageUrl();

        this.signin.emit(sessionDetails);

      });
    }, (err) => {
      console.log(JSON.stringify(err, undefined, 2));
    });
  }

  @Output()
  private signin: EventEmitter<UserSessionDetails> = new EventEmitter();

  constructor(private ngZone: NgZone, private element: ElementRef) { }

  ngAfterViewInit(): void {
    this.googleInit();
  }

}
