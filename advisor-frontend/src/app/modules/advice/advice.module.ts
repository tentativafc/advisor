import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdviceListComponent } from './advice-list/advice-list.component';
import { AdviceRoutingModule } from './advice-routing.module';



@NgModule({
  declarations: [AdviceListComponent],
  exports: [AdviceListComponent],
  imports: [
    CommonModule,
    AdviceRoutingModule
  ]
})
export class AdviceModule { }
