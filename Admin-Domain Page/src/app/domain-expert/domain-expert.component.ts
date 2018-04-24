import { Component, OnInit, Input } from "@angular/core";
import { ErrorStateMatcher } from "@angular/material";
import { startWith } from "rxjs/operators/startWith";
import { map } from "rxjs/operators/map";
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators
} from "@angular/forms";
import { DomainExpertService } from "./domain-expert.service";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs/Observable";

/** Error when invalid control is dirty, touched, or submitted. */
export class CustomErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }
}

@Component({
  selector: "app-domain-expert",
  templateUrl: "./domain-expert.component.html",
  styleUrls: ["./domain-expert.component.css"],
  inputs: ["response", "data"]
})
export class DomainExpertComponent implements OnInit {
  buttondisabled: boolean = false;
  data: any;
  @Input() domain: string;
  @Input() concepts: string;
  response: any;
  token: string;
  requestbody: any;
  result: any;
  role = "Domain Expert";
  domainList: any;
  conceptList: any;

  constructor(
    private _domainExpertService: DomainExpertService,
    private _router: Router,
    private route: ActivatedRoute
  ) {}

  newConceptList: Observable<string[]>;
  newDomainList: Observable<string[]>;

  ngOnInit() {
    // console.log(this._router);
    this.response = this.route.queryParams.subscribe(params => {
      this.response = params;
      this.token = this.response.token;
      // console.log(this.response.token);
    });
    this._domainExpertService.getDomainConceptList().subscribe(data => {
      this.result = data._body;
      console.log(JSON.parse(this.result));
      this.domainList = JSON.parse(this.result).domainList;
      this.conceptList = JSON.parse(this.result).conceptList;
      this.newDomainList = this.domainFormControl.valueChanges.pipe(
        startWith(""),
        map(val => this.filter(val, this.domainList))
      );
      this.newConceptList = this.conceptFormControl.valueChanges.pipe(
        startWith(""),
        map(val => this.filter(val, this.conceptList))
      );
    });
  }

  filter(val: string, option: any): string[] {
    return option.filter(
      option => option.toLowerCase().indexOf(val.toLowerCase()) === 0
    );
  }

  domainFormControl = new FormControl("", [Validators.required]);
  conceptFormControl = new FormControl("", [Validators.required]);
  matcher = new CustomErrorStateMatcher();

  checkResponse() {
    // console.log(this.response);
  }
  checking() {
    this.buttondisabled =
      this.domainFormControl.hasError("required") ||
      this.conceptFormControl.hasError("required");
  }

  populateAllConcepts() {
    for (let domain of this.domainList) {
      for (let concept of this.conceptList) {
        this.domain = domain;
        this.concepts = concept;
        this.populatebutton();
      }
    }
  }

  populatebutton() {
    console.log(this.response);
    this.requestbody = {
      domain: this.domain,
      concepts: this.concepts.split(",")
    };
    // console.log(this.response, "  token ", this.response.token, this.token);
    this._domainExpertService
      .postPopulateData(this.requestbody, this.token)
      .subscribe(data => {
        this.result = data;
        // console.log(this.result._body);
      });
  }
}
