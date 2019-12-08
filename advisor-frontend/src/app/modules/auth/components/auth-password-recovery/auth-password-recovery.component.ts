import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-auth-password-recovery',
  templateUrl: './auth-password-recovery.component.html',
  styleUrls: ['./auth-password-recovery.component.scss']
})
export class AuthPasswordRecoveryComponent implements OnInit {


  recoverForm = this.fb.group({
    email: ['', Validators.required]
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
  }

  onClick() {
  }

}
