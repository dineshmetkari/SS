import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs/Observable";
import { throws } from "assert";
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { IDomainExpert } from './IDomainExpert';

@Injectable()
export class AdminService {

  constructor(private http: Http, private httpClient : HttpClient) {

  }

  postAddDomainExpertCredentials(credentials : any) : Observable<any> {

      let headers = new Headers({'Content-Type':'application/json'})
      let options = new RequestOptions({headers : headers})

      return this.http.post('http://172.23.238.148:8090/register', credentials, options);
  }

  getAllDomainExperts() : Observable<IDomainExpert[]> {
    return this.httpClient.get<IDomainExpert[]>('http://172.23.238.148:8090/showAll');
  }

  deleteDomainExpert(emailId : String) : Observable<any> {

    return this.http.get('http://172.23.238.148:8090/delete/'+ emailId);
  }



}
