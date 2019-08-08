import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { UserSessionDetails } from './model/user-session-details';

@Injectable()
export class UserDetailsStorageService {

  USER_DETAILS_STORAGE_KEY: string = "advisor_user_session_details";

  constructor(private storageService: StorageService) {}

  public saveUserSession(userSessonDetails: UserSessionDetails) {
    this.storageService.saveObjectItem(this.USER_DETAILS_STORAGE_KEY, userSessonDetails);
  }

  public getUserSession(): UserSessionDetails | null {
    return this.storageService.getObjectItem(this.USER_DETAILS_STORAGE_KEY);
  }

  public deleteUserSession() {
    this.storageService.removeItem(this.USER_DETAILS_STORAGE_KEY);
  }

  public getAccessToken(): string {
    let userSessionDetails = this.getUserSession();
    if (userSessionDetails) {
      return userSessionDetails.access_token;
    }
    return "";
  }
}
