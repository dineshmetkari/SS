import { Component, OnInit, Input, OnChanges } from "@angular/core";
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
import {MatChipInputEvent} from '@angular/material';
import {ENTER, COMMA} from '@angular/cdk/keycodes';

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
export class DomainExpertComponent implements OnInit, OnChanges {
  conceptAutoComplete: Array<string> = [];
  buttondisabled: boolean = false;
  populatebuttondisabled: boolean = true;
  populateAllButtonDisabled: boolean = true;
  domainInput: boolean = false;
  data: any;
  @Input() domain: string;
  @Input() domain1: string;
  @Input() concepts: string;
  concepts1 : Array<string> = [];
  concepts2 : Array<string> = [];

  visible: boolean = true;
  selectable: boolean = true;
  removable: boolean = true;
  addOnBlur: boolean = true;

  // Enter, comma
  separatorKeysCodes = [ENTER, COMMA];

  response: any;
  token: string;
  requestbody: any;
  result: any;
  role = "Domain Expert";
  domainList: any;
  conceptList: any;
  keys: Array<string> = [];

  constructor(
    private _domainExpertService: DomainExpertService,
    private _router: Router,
    private route: ActivatedRoute
  ) {}

  newConceptList: Observable<string[]>;
  newDomainList: Observable<string[]>;
  newDomainList1: Observable<string[]>;

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
      this.conceptList = JSON.parse(this.result).conceptList;
      for(var key in this.conceptList){
        // this.keys.push(key);
        // console.log(key.replace('""',''));
        // console.log(key);
        this.keys.push(key);
      }
      // this.keys = Object.keys(this.conceptList);

      this.newDomainList = this.domainFormControl.valueChanges.pipe(
        startWith(""),
        map(val => this.filter(val, this.keys))
      );
      this.newDomainList1 = this.domainFormControl1.valueChanges.pipe(
        startWith(""),
        map(val => this.filter(val, this.keys))
      );
    });
  }

  filter(val: string, option: any): string[] {
    return option.filter(
      option => option.toLowerCase().indexOf(val.toLowerCase()) === 0
    );
  }

  domainFormControl = new FormControl("", [Validators.required]);
  domainFormControl1 = new FormControl("", [Validators.required]);
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

  checking1() {

    this.populateAllButtonDisabled =
        this.domainFormControl1.hasError("required");
  }

  fetchConcepts(){
    console.log("called")
    for(let key of this.keys){
      console.log("//key" +key);
      console.log(this.domain);
      if(key == this.domain){
        console.log("inside");
        console.log(this.conceptList.Java);
        this.conceptAutoComplete = [];
        // console.log(typeof(this.conceptList.Java));
        this.conceptAutoComplete = this.conceptList[key];
      }
    }
    console.log(this.conceptAutoComplete);
    this.newConceptList = this.conceptFormControl.valueChanges.pipe(
      startWith(""),
      map(val => this.filter(val, this.conceptAutoComplete))
    );

    this.checking();

  }

  fetchAllConcepts(){

    for(let key of this.keys){
      if(key == this.domain1){
        this.concepts2 = this.conceptList[key];
      }
    }

    this.checking1();
    if(this.domain1=="Java" || this.domain1=="Investment")
      this.populateAllButtonDisabled = false;
    else
      this.populateAllButtonDisabled = true;
  }


  ngOnChanges(){
    this.fetchConcepts();
  }

  populatebutton() {
    console.log(this.response);
    this.requestbody = {
      domain: this.domain,
      concepts: this.concepts1
    };
    // console.log(this.response, "  token ", this.response.token, this.token);
    this._domainExpertService
      .postPopulateData(this.requestbody, this.token)
      .subscribe(data => {
        this.result = data;
        // console.log(this.result._body);
      });
  }

  populateAllButton() {
    console.log(this.response);
    this.requestbody = {
      domain: this.domain1,
      concepts: this.concepts2
    };
    // console.log(this.response, "  token ", this.response.token, this.token);
    this._domainExpertService
      .postPopulateData(this.requestbody, this.token)
      .subscribe(data => {
        this.result = data;
        // console.log(this.result._body);
      });
  }

  flag : boolean = true;

  addConcepts(concept : string) {
    this.flag= true;
    this.populatebuttondisabled = false;
    this.domainInput = true;
    console.log("to be added "+concept);
    for(var str of this.concepts1){
      if(str==concept)
        this.flag = false;
    }
    if(this.flag==true){
      this.concepts1.push(concept);
      console.log(this.concepts1);
      this.concepts= "";
      console.log("added succesfully");
    }
    this.concepts = "";

  }

  add(event: MatChipInputEvent): void {
    let input = event.input;
    let value = event.value;

    // Add our fruit
    // if ((value || '').trim()) {
    //   this.concepts1.push({ value.trim() });
    // }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(concept: any): void {
    let index = this.concepts1.indexOf(concept);

    if (index >= 0) {
      this.concepts1.splice(index, 1);
    }

    if(this.concepts1.length == 0) {
      this.domainInput = false;
      this.populatebuttondisabled = true;
    }
  }
}
