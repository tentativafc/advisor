import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthPasswordRecoveryComponent } from './auth-password-recovery.component';

describe('AuthPasswordRecoveryComponent', () => {
  let component: AuthPasswordRecoveryComponent;
  let fixture: ComponentFixture<AuthPasswordRecoveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthPasswordRecoveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthPasswordRecoveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
