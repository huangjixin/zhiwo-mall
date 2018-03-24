import { BrowserModule } from '@angular/platform-browser';
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
    BrowserModule,WeUiModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
