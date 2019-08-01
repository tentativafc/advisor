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

  @Input() public title: string;

  public authenticated: boolean;

  public userSessionDetails: UserSessionDetails;

  // constructor(private authenticationService: AuthenticationService, private storageService: StorageService) { }

  userSessionDetailsHandler(event: UserSessionDetails) {
    this.authenticated = true;
    this.userSessionDetails = event;
  }

  ngOnInit() {
    this.authenticated = false;
  }

}
