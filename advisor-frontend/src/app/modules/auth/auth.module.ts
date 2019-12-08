import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';

import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { AuthPasswordRecoveryComponent } from './components/auth-password-recovery/auth-password-recovery.component';
import { GoogleSigninComponent } from './components/google-signin/google-signin.component';
import { GoogleSignoutComponent } from './components/google-signout/google-signout.component';
import { AuthenticationService } from './authentication.service';
import { AuthRegisterComponent } from './components/auth-register/auth-register.component';
import { SocialLoginComponent } from './components/social-login/social-login.component';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [GoogleSigninComponent, GoogleSignoutComponent, AuthLoginComponent, AuthPasswordRecoveryComponent, AuthRegisterComponent, SocialLoginComponent],
  exports: [GoogleSigninComponent, GoogleSignoutComponent, AuthLoginComponent, AuthPasswordRecoveryComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [
    AuthenticationService
  ]
})
export class AuthModule { }
