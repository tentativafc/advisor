import { Injectable } from '@angular/core';
import { ApiService } from '../shared/api.service';
import { Observable } from 'rxjs';
import { UserSessionDetails } from '../shared/model/user-session-details';
import { UserDetailsStorageService } from '../shared/user-details-storage.service';

@Injectable()
export class AuthenticationService {

  constructor(private userDetailsStorageService: UserDetailsStorageService, private apiService: ApiService) { }

  isLoggedIn(): boolean {
    let access_token = this.userDetailsStorageService.getAccessToken();
    return (access_token.length > 0);
  }

  validateToken(userSessionDetails: UserSessionDetails): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/api/validate', userSessionDetails);
  }

  invalidateToken(userSessionDetails: UserSessionDetails): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/api/invalidate', userSessionDetails);
  }

}
