import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment'
import { Observable, throwError } from 'rxjs'
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http'
import { catchError } from 'rxjs/operators'
import { UserDetailsStorageService } from './user-details-storage.service';

@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient, private userDetailsStorageService: UserDetailsStorageService) { }

  private formatErrors(error: any) {
    return throwError(error.error);
  }

  get(path: string = '', params: HttpParams): Observable<any> {
    return this.httpClient.get(`${environment.api_url}${path}`, { ...this.getHeaderOptionsAjax(), params }).pipe(catchError(this.formatErrors));
  }

  post(path: string = '', body: Object = {}): Observable<any> {
    return this.httpClient.post(`${environment.api_url}${path}`, JSON.stringify(body), this.getHeaderOptionsAjax()).pipe(catchError(this.formatErrors));
  }

  put(path: string = '', body: Object = {}): Observable<any> {
    return this.httpClient.put(`${environment.api_url}${path}`, JSON.stringify(body), this.getHeaderOptionsAjax()).pipe(catchError(this.formatErrors));
  }

  delete(path: string = '', params: HttpParams): Observable<any> {
    return this.httpClient.delete(`${environment.api_url}${path}`, { ...this.getHeaderOptionsAjax(), params }).pipe(catchError(this.formatErrors));
  }

  private getHeaderOptionsAjax() {

    const bearerToken = this.userDetailsStorageService.getAccessToken();

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${bearerToken}`
      })
    };

    return httpOptions;
  }

}
