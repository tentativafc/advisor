import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdviceListComponent } from './advice-list/advice-list.component';


const routes: Routes = [
  {path: '', component: AdviceListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdviceRoutingModule { }
