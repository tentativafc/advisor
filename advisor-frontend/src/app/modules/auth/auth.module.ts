import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GoogleSigninComponent } from './components/google-signin/google-signin.component';
import { AuthenticationService } from './authentication.service';


@NgModule({
  declarations: [GoogleSigninComponent],
  exports: [GoogleSigninComponent],
  imports: [
    CommonModule,
  ],
  providers: [
    AuthenticationService
  ]
})
export class AuthModule { }
