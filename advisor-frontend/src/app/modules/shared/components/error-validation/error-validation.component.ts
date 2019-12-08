import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-error-validation',
  templateUrl: './error-validation.component.html',
  styleUrls: ['./error-validation.component.scss']
})
export class ErrorValidationComponent implements OnInit {

  @Input() formGroupValidate: FormGroup;
  @Input() controlName: string;
  @Input() controlTitle: string;
  
  controlsName: string[] = [];
  // control: AbstractControl;

  constructor() { }

  ngOnInit() {
    // this.control = this.formGroupValidate.controls[this.controlName];
  }
}
