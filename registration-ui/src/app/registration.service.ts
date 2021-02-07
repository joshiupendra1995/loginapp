import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  baseUrl="http://localhost:8090"

  constructor(private _http: HttpClient) { }

  public loginUserFormRemote(user: User): Observable<any> {
    return this._http.put<any>(`${this.baseUrl}/login`, user)
  }
}
