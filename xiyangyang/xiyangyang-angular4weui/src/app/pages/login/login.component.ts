import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../service/auth.service';
import {RouterStateSnapshot} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public url: String = '';
  constructor(private authService: AuthService) {
    // this.url = state.url;, state: RouterStateSnapshot
  }

  ngOnInit() {
  }

  login(){
    this.authService.isLoggedIn = true;
  }

}
