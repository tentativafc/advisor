import { Injectable } from '@angular/core';
import { ApiService } from '../shared/api.service';
import { Observable } from 'rxjs';
import { UserSessionDetails } from '../shared/model/user-session-details';
import { UserDetailsStorageService } from '../shared/user-details-storage.service';
import { Login } from '../shared/model/login';
import { Register } from '../shared/model/register';
import { Recover } from '../shared/model/recover';

@Injectable()
export class AuthenticationService {

  constructor(private userDetailsStorageService: UserDetailsStorageService, private apiService: ApiService) { }

  login(login: Login): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/api/login', login);
  }

  register(register: Register): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/api/register', register);
  }

  recoverPassword(recover: Recover) {
    return this.apiService.post('/auth/api/recover_password', recover);
  }

  isLoggedIn(): boolean {
    let access_token = this.userDetailsStorageService.getAccessToken();
    return (access_token.length > 0);
  }

  validateToken(userSessionDetails: UserSessionDetails): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/validate', userSessionDetails);
  }

  invalidateToken(userSessionDetails: UserSessionDetails): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/invalidate', userSessionDetails);
  }

}
