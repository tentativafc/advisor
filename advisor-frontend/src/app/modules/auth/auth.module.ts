import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GoogleSigninComponent } from './components/google-signin/google-signin.component';


@NgModule({
  declarations: [GoogleSigninComponent],
  exports: [GoogleSigninComponent],
  imports: [
    CommonModule,
  ]
})
export class AuthModule { }
