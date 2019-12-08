import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { AuthPasswordRecoveryComponent } from './components/auth-password-recovery/auth-password-recovery.component';
import { AuthRegisterComponent } from './components/auth-register/auth-register.component';

const routes: Routes = [
  { path: 'auth-login', component: AuthLoginComponent },
  { path: 'auth-register', component: AuthRegisterComponent },
  { path: 'auth-password-recovery', component: AuthPasswordRecoveryComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
