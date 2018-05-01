import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { Http, Response } from "@angular/http";
import { Headers, RequestOptions } from "@angular/http";

@Injectable()
export class TermBankService {
  constructor(private http: Http) {}

  getIndicators(): Observable<any> {
    return this.http.get("http://172.23.238.178:8096/api/v1/neo4jIntent");
  }

  getWordApi(term: any): Observable<any> {
    return this.http.get(
      "http://words.bighugelabs.com/api/1/b9fb9122bf7e43979c005372b3eb65cf/" +
        term +
        "/json"
    );
  }
  postTermSynonym(neo4jList:any):Observable<any>{
    return this.http.post(
      "http://172.23.238.178:8096/api/v1/neo4jSynonym",neo4jList
    );
  }
}
