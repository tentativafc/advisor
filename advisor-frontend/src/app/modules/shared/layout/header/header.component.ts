const USER_DETAILS_STORAGE_KEY = "advisor_user_session_details";

import { Component, OnInit, Input } from '@angular/core';
import { StorageService } from '../../storage.service';
import { UserSessionDetails } from '../../model/user-session-details';
import { AuthenticationService } from 'src/app/modules/auth/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input()
  title: string;

  authenticated: boolean = false;

  userSessionDetails: UserSessionDetails;

  constructor(private storageService: StorageService, private authenticationService: AuthenticationService) { }

  userSigninHandler(event: UserSessionDetails) {
    this.validateToken(event);
  }

  userSignoutHandler(event: null) {
    this.storageService.removeItem(USER_DETAILS_STORAGE_KEY);
  }

  ngOnInit() {
    this.authenticated = false;
    let userSessionDetail: UserSessionDetails = this.storageService.getObjectItem(USER_DETAILS_STORAGE_KEY);
    if (userSessionDetail) {
      this.validateToken(userSessionDetail)
    }
  }

  onClick() {
    this.authenticated = !this.authenticated;
  }

  validateToken(userSessionDetails: UserSessionDetails) {
    console.log(userSessionDetails.google_access_token);
    //this.authenticationService.validateToken(userSessionDetails).subscribe((validatedUserSessionDetails: UserSessionDetails) => {
    // this.userSessionDetails = validatedUserSessionDetails;
    this.authenticated = true;
    //  this.storageService.saveObjectItem(USER_DETAILS_STORAGE_KEY, this.userSessionDetails);
    // });
  }

}
