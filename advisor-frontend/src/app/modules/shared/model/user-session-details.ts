import { UserDetails } from './user-details';

export class UserSessionDetails extends UserDetails {
    google_access_token: string;
    access_token: string;
}
