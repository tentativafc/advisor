import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './modules/auth/auth.module';
import { AdviceModule } from './modules/advice/advice.module';
import { SharedModule } from './modules/shared/shared.module';
import { HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AuthModule,
    SharedModule,
    AdviceModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
