import { BrowserModule, } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { WeUiModule } from 'ngx-weui';

import { AppComponent } from './app.component';
import { GuessComponent } from './pages/guess/guess.component';


@NgModule({
  declarations: [
    AppComponent,
    GuessComponent
  ],
  imports: [
    BrowserModule,WeUiModule.forRoot(),BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
