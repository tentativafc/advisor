import { Component, OnInit, Input } from '@angular/core';
import { StorageService } from '../../storage.service';
import { UserSessionDetails } from '../../model/user-session-details';
import { AuthenticationService } from 'src/app/modules/auth/authentication.service';
import { ChangeDetectorRef } from '@angular/core'

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

  constructor(private authenticationService: AuthenticationService, private storageService: StorageService, private ref: ChangeDetectorRef) { }

  userSigninHandler(event: UserSessionDetails) {
    this.authenticated = true;
    this.userSessionDetails = event;
    this.storageService.saveObjectItem("advisor_user_session_details", this.userSessionDetails);
    this.ref.detectChanges();
  }

  userSignoutHandler(event: null) {
    this.authenticated = false;
    this.storageService.removeItem("advisor_user_session_details");
    this.ref.detectChanges();
  }

  ngOnInit() {
    this.authenticated = false;
    this.userSessionDetails = this.storageService.getObjectItem('advisor_user_session_details');
    if (this.userSessionDetails) {
      this.authenticated = true;
    }
    // this.authenticationService.validateToken(this.userSessionDetails).subscribe((data) => console.log(data));
  }

  onClick() {
    this.authenticated = !this.authenticated;
  }

}
