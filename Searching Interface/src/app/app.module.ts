import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { AppComponent } from './app.component';

import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
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
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
} from '@angular/material';

import { CardFancyComponent } from "./card-fancy/card-fancy.component";
import { CardFancyExample } from "./card-fancy/card-fancy-example";
import { ChatComponent } from "./chat/chat.component";

@NgModule({
  declarations: [
    AppComponent,
    CardFancyComponent,
    CardFancyExample,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    MatCardModule,
    MatGridListModule,
    MatButtonModule,
    MatDividerModule,
    MatAutocompleteModule,
    MatInputModule,
    // FormsModule,
    BrowserAnimationsModule,
    // ReactiveFormsModule,
    MatSelectModule,
    // HttpClientModule,
    MatDialogModule,
    MatNativeDateModule,
    // HttpModule,
    MatTabsModule,
    MatChipsModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
