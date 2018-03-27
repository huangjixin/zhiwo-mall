import { BrowserModule, } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { WeUiModule } from 'ngx-weui';

import { AppComponent } from './app.component';
import { GuessComponent } from './pages/guess/guess.component';
import { UserInfoComponent } from './pages/user-info/user-info.component';
import { GuessRecordComponent } from './pages/guess/guess-record/guess-record.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MyCollectionComponent } from './pages/my-collection/my-collection.component';


@NgModule({
  declarations: [
    AppComponent,
    GuessComponent,
    UserInfoComponent,
    GuessRecordComponent,
    LoginComponent,
    RegisterComponent,
    MyCollectionComponent
  ],
  imports: [
    BrowserModule,WeUiModule.forRoot(),BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
