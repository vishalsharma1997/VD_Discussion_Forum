import { Component, OnInit } from '@angular/core';
import {LoginService} from './services/login.service';
import {Router} from '@angular/router';
 
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'VD-DISCUSSION';
  constructor(private service : LoginService,  private router : Router){

  }

  ngOnInit(){
    this.service.authenticate(undefined, undefined, undefined);
  }
  
  isLogged(){
    // console.log("value is : ");
    // console.log(this.service.authenticated);
    return LoginService.authenticated || LoginService.authenticatedUser;
  }
  
  logout(){
    this.service.logout(
      () => {
        LoginService.authenticated = false;
        LoginService.authenticatedUser = false;
          this.router.navigateByUrl("login");
      },
      () => {

      }
    )
  }

 

  checkLoginButton(){
    if(LoginService.authenticated){
      return "LOG OUT";
    }
    else{
      return "LOG IN";
    }
  }

}
