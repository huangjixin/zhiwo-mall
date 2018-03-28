import { BrowserModule, } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WeUiModule } from 'ngx-weui';

import { AppComponent } from './app.component';
import { GuessComponent } from './pages/guess/guess.component';
import { UserInfoComponent } from './pages/user-info/user-info.component';
import { GuessRecordComponent } from './pages/guess-record/guess-record.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MyCollectionComponent } from './pages/my-collection/my-collection.component';
import { AddressComponent } from './pages/address/address.component';
import { AddressDetailComponent } from './pages/address-detail/address-detail.component';
import { MainComponent } from './pages/main/main.component';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';

export const routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: MainComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'collection', component: MyCollectionComponent },
  { path: 'guess-record', component: GuessRecordComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    GuessComponent,
    UserInfoComponent,
    GuessRecordComponent,
    LoginComponent,
    RegisterComponent,
    MyCollectionComponent,
    AddressComponent,
    AddressDetailComponent,
    MainComponent
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule, RouterModule, WeUiModule.forRoot(), RouterModule.forRoot(routes,{ enableTracing: true })
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
