import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment'
import {Observable, throwError} from 'rxjs'
import {HttpClient, HttpParams} from '@angular/common/http'
import {catchError} from 'rxjs/operators'

@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  private formatErrors(error: any) {
    return  throwError(error.error);
  }

  get(path: string ='/api', params: HttpParams): Observable<any> {
    return this.httpClient.get(`${environment.api_url}${path}`, {params}).pipe(catchError(this.formatErrors));
  }

  post(path: string ='/api', body: Object = {}): Observable<any> {
    return this.httpClient.post(`${environment.api_url}${path}`, JSON.stringify(body)).pipe(catchError(this.formatErrors));
  }

  put(path: string ='/api', body: Object = {}): Observable<any> {
    return this.httpClient.put(`${environment.api_url}${path}`, JSON.stringify(body)).pipe(catchError(this.formatErrors));
  }

  delete(path: string='/api'): Observable<any> {
    return this.httpClient.delete(`${environment.api_url}${path}`).pipe(catchError(this.formatErrors));
  }

}
