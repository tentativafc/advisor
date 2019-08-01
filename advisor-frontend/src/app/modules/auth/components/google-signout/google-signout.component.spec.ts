import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleSignoutComponent } from './google-signout.component';

describe('GoogleSignoutComponent', () => {
  let component: GoogleSignoutComponent;
  let fixture: ComponentFixture<GoogleSignoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoogleSignoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleSignoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
