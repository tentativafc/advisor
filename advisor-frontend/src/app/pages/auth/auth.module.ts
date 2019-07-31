import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthLoginComponent } from './auth-login/auth-login.component';
import { GoogleSigninComponent } from './google-signin/google-signin.component';
import { TesteComponent } from './teste/teste.component';


@NgModule({
  declarations: [AuthLoginComponent, GoogleSigninComponent],
  imports: [
    CommonModule,
    AuthRoutingModule
  ], 
  exports: [GoogleSigninComponent]
})
export class AuthModule { }
