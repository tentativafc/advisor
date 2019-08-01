import { Injectable } from '@angular/core';
import { ApiService } from '../shared/api.service';
import { Observable } from 'rxjs';
import { UserSessionDetails } from '../shared/model/user-session-details';

@Injectable()
export class AuthenticationService {

  constructor(private apiService: ApiService) { }

  validateToken(userSessionDetails: UserSessionDetails): Observable<UserSessionDetails> {
    return this.apiService.post('/auth/api', userSessionDetails);
  }

}
