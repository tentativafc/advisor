import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GoogleSigninComponent } from './components/google-signin/google-signin.component';
import { AuthenticationService } from './authentication.service';
import { GoogleSignoutComponent } from './components/google-signout/google-signout.component';


@NgModule({
  declarations: [GoogleSigninComponent, GoogleSignoutComponent],
  exports: [GoogleSigninComponent, GoogleSignoutComponent],
  imports: [
    CommonModule,
  ],
  providers: [
    AuthenticationService
  ]
})
export class AuthModule { }
