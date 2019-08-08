
import { Component, OnInit, Input } from '@angular/core';
import { UserSessionDetails } from '../../model/user-session-details';
import { AuthenticationService } from 'src/app/modules/auth/authentication.service';
import { UserDetailsStorageService } from '../../user-details-storage.service';

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

  constructor(private userDetailsStorageService: UserDetailsStorageService, private authenticationService: AuthenticationService) { }

  userSigninHandler(event: UserSessionDetails) {
    this.validateToken(event);
  }

  userSignoutHandler(event: null) {
    let userSessionDetail: UserSessionDetails = this.userDetailsStorageService.getUserSession();
    this.invalidateToken(userSessionDetail);
  }

  ngOnInit() {
    this.authenticated = false;
    let userSessionDetail: UserSessionDetails = this.userDetailsStorageService.getUserSession();
    if (userSessionDetail) {
      this.validateToken(userSessionDetail)
    }
  }

  onClick() {
    this.authenticated = !this.authenticated;
  }

  validateToken(userSessionDetails: UserSessionDetails) {
    this.authenticationService.validateToken(userSessionDetails).subscribe((validatedUserSessionDetails: UserSessionDetails) => {
      console.log(validatedUserSessionDetails.access_token);
      this.userSessionDetails = validatedUserSessionDetails;
      this.authenticated = true;
      this.userDetailsStorageService.saveUserSession(this.userSessionDetails);
    });
  }

  invalidateToken(userSessionDetails: UserSessionDetails) {
    console.log(userSessionDetails.google_access_token);
    this.authenticationService.invalidateToken(userSessionDetails).subscribe(() => {
      this.authenticated = false;
      this.userDetailsStorageService.deleteUserSession();
    });
  }

}
