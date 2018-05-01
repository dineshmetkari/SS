import { BrowserModule } from "@angular/platform-browser";
import { NgModule, Input } from "@angular/core";
import { MatAutocompleteModule } from "@angular/material";
import { FormControl } from "@angular/forms";

import { AppComponent } from "./app.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {
  HttpClientModule,
  HttpClient,
  HttpHeaders,
  HttpParams
} from "@angular/common/http";

import { MatFormFieldModule } from "@angular/material/form-field";
import { RouterModule, Routes } from "@angular/router";
import {
  MatButtonModule,
  MatCardModule,
  MatDividerModule,
  MatGridListModule,
  MatInputModule,
  MatSelectModule,
  MatFormFieldControl,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatIconModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule
} from "@angular/material";

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NavbarComponent } from "./navbar/navbar.component";
import { AppService } from "./app.service";
import { RequestOptions, HttpModule, Http } from "@angular/http";
import { CdkTableModule } from "@angular/cdk/table";
import { DomainExpertComponent } from "./domain-expert/domain-expert.component";
import { DomainExpertService } from "./domain-expert/domain-expert.service";
import { AdminComponent } from "./admin/admin.component";
import { AdminService } from "./admin/admin.service";
import { TermBankComponent } from "./term-bank/term-bank.component";
import { Observable } from "rxjs/Observable";
import { TermBankService } from "./term-bank/term-bank.service";
import { TermBankModel } from "./term-bank/term-bank.model";

const appRoutes: Routes = [
  { path: "home", component: AppComponent },
  { path: "admin", component: AdminComponent },
  { path: "domain-expert", component: DomainExpertComponent },
  { path: "", redirectTo: "/home", pathMatch: "full" },
  { path: "**", redirectTo: "/home", pathMatch: "full" }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DomainExpertComponent,
    AdminComponent,
    TermBankComponent
  ],
  exports: [
    CdkTableModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatDialogModule,
    BrowserModule,
    MatChipsModule,
    MatCheckboxModule,
    MatCardModule,
    MatGridListModule,
    MatButtonModule,
    MatDividerModule,
    MatAutocompleteModule,
    MatInputModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatSelectModule,
    HttpClientModule,
    MatDialogModule,
    MatNativeDateModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    HttpModule,
    RouterModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AppService,
    DomainExpertService,
    AdminService,
    TermBankService,
    TermBankModel
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
