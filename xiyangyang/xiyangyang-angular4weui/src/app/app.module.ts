import { BrowserModule, } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, RouteReuseStrategy } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { WeUIModule } from 'angular4-weui';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
// import { WeUiModule } from 'ngx-weui';

import { AppComponent } from './app.component';

import {SimpleReuseStrategy} from './SimpleReuseStrategy';
import { MainComponent } from './pages/main/main.component';
import { UserInfoComponent } from './pages/user-info/user-info.component';
import { GuessRecordComponent } from './pages/guess-record/guess-record.component';
import { AddredssComponentComponent } from './pages/addredss-component/addredss-component.component';
import { AddressComponent } from './pages/address/address.component';
import { AddressDetailComponent } from './pages/address-detail/address-detail.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PersonalComponent } from './pages/personal/personal.component';

import { SwiperModule, SwiperConfigInterface, SWIPER_CONFIG } from 'ngx-swiper-wrapper';
import { GuessComponent } from './pages/guess/guess.component';

const DEFAULT_SWIPER_CONFIG: SwiperConfigInterface = {
  observer: true,
  direction: 'horizontal',
  threshold: 50,
  spaceBetween: 5,
  slidesPerView: 1,
  centeredSlides: true
};

export const routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: MainComponent, children: [
      {path: '', outlet: 'userInfo', component: UserInfoComponent},
      {path: 'guess-record', outlet: 'userInfo', component: GuessRecordComponent },
      {path: 'address', outlet: 'userInfo', component: AddredssComponentComponent, children: [
          {path: '', outlet: 'addressComponent', component: AddressComponent },
          {path: 'address-detail', outlet: 'addressComponent', component: AddressDetailComponent }
        ]} ,
    ] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'personal', component: PersonalComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    UserInfoComponent,
    GuessRecordComponent,
    AddredssComponentComponent,
    AddressComponent,
    AddressDetailComponent,
    LoginComponent,
    RegisterComponent,
    PersonalComponent,
    GuessComponent
  ],
  imports: [
    SwiperModule, FormsModule, BrowserModule, InfiniteScrollModule,
    BrowserAnimationsModule, RouterModule, WeUIModule,  RouterModule.forRoot(routes,{ enableTracing: true })
  ],
  providers: [{provide : RouteReuseStrategy, useClass: SimpleReuseStrategy}, { provide: SWIPER_CONFIG, useValue: DEFAULT_SWIPER_CONFIG
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
