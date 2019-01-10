import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {};
  loginErrorFlag = false;
  constructor(private service: LoginService, private router: Router) { 
    this.service.authenticate(undefined,undefined,undefined);
  }

  ngOnInit() {
  }  

  login(){
    this.service.authenticate(this.credentials, 
    () => {
      // console.log("Credentials : ");
      // console.log(this.credentials);
      this.router.navigateByUrl('');
    },
    () =>{
      this.loginErrorFlag = true;
    });
  }

  loginError(){
    return this.loginErrorFlag;
  }

  isLoggedIn(){
    return LoginService.authenticated || LoginService.authenticatedUser;
  }
  
}
