import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { throws } from "assert";
import { Http, Response } from "@angular/http";
import { Headers, RequestOptions } from "@angular/http";

@Injectable()
export class DomainExpertService {
  constructor(private http: Http) {}

  postPopulateData(populate: any, token: string): Observable<any> {
    console.log("adfasd" + token);
    let headers = new Headers({ token: token });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(
      "http://172.23.238.171:8999/api/v1/search",
      populate,
      options
    );
    // return this.http.post(
    //   "http://172.23.238.165:8000/search-service/",
    //   populate,
    //   options
    // );
  }

  getDomainConceptList(): Observable<any> {
    return this.http.get("http://172.23.238.178:8096/api/v1/neo4jConcept");
  }
}
