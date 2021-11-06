import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class ExchangeService {

  constructor(private http:HttpClient) { }

  public exchange(amount: number, currencyOrigin: string, currencyDestiny: string, date: string): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MzYwOTQzNzEsInN1YiI6ImFkbWluIn0.5anfK_fvmQ6Ot5oEtW6sBVNJEpKZt75RIGF4edX0vQ4eq0Phu8_CZqoQzkqmxBmkMlO_70chL_r_5EIv6T-t_A');

    return this.http.get(`http://localhost:8080/v1/exchanges/${amount}/${currencyOrigin}/${currencyDestiny}/${date}`, {'headers': headers});
  }

}
