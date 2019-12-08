import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { ApiService } from './api.service';
import { StorageService } from './storage.service';
import { UserDetailsStorageService } from './user-details-storage.service';
import { ErrorValidationComponent } from './components/error-validation/error-validation.component';



@NgModule({
  declarations: [HeaderComponent, FooterComponent, ErrorValidationComponent],
  exports: [HeaderComponent, FooterComponent, ErrorValidationComponent],
  
  imports: [
    CommonModule,
  ],
  providers: [
    ApiService,
    StorageService,
    UserDetailsStorageService
  ]
})
export class SharedModule { }
