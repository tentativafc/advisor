import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { AuthModule } from '../auth/auth.module';
import { ApiService } from './api.service';
import { StorageService } from './storage.service';



@NgModule({
  declarations: [HeaderComponent, FooterComponent],
  exports: [HeaderComponent, FooterComponent],
  
  imports: [
    CommonModule,
    AuthModule
  ],
  providers: [
    ApiService,
    StorageService
  ]
})
export class SharedModule { }
