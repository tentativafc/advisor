import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  yearNow = 0; 

  constructor() { 
  }

  ngOnInit() {
    this.yearNow = (new Date()).getFullYear(); 
  }

}
