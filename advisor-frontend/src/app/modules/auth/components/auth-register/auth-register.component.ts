import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Register } from 'src/app/modules/shared/model/register';
import { AuthenticationService } from '../../authentication.service';
import { UserSessionDetails } from 'src/app/modules/shared/model/user-session-details';
import { UserDetails } from 'src/app/modules/shared/model/user-details';
import { UserDetailsStorageService } from 'src/app/modules/shared/user-details-storage.service';

@Component({
  selector: 'app-auth-register',
  templateUrl: './auth-register.component.html',
  styleUrls: ['./auth-register.component.scss']
})
export class AuthRegisterComponent implements OnInit {

  registerForm = this.fb.group({
    first_name: ['', Validators.required],
    last_name: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
    password_confirm: ['', Validators.required],
  });

  private register: Register;
  private userSessionDetails: UserSessionDetails;
  

  constructor(private fb: FormBuilder, private authenticationService: AuthenticationService, private userDetailsStorageService: UserDetailsStorageService) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log("Submit...")
    this.register = {... this.registerForm.getRawValue()};
    this.authenticationService.register(this.register).subscribe((registeredUser: UserSessionDetails) => {
      console.log(registeredUser.access_token);
      this.userSessionDetails = registeredUser;
      this.userDetailsStorageService.saveUserSession(this.userSessionDetails);
    });
  }

}
