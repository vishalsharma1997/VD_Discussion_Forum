import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  authUrl = 'http://localhost:8000/admin';
  logoutUrl = 'http://localhost:8000/logout';
  authUserUrl = 'http://localhost:8000/user';
  static authenticated = false;
  static authenticatedUser = false;
  loggedIn = false;
  headers = {};

  constructor(private http: HttpClient) { }

  authenticate(credentials, successCallback, errorCallback){

    this.headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {} );

    this.http.get(this.authUrl, { headers: this.headers, withCredentials:true }).subscribe( (resposnse) => {
        if (resposnse.hasOwnProperty('name')){
          // console.log("In If : "+resposnse);
          
          LoginService.authenticated = true;
          sessionStorage.setItem('id', resposnse["principal"]['username']);
          sessionStorage.setItem('myrole', 'admin');
        }else{
          // console.log( resposnse);
          LoginService.authenticated = false;
        }
        // console.log("successCallback = " +successCallback)
        return successCallback && successCallback();
      },
      (error) => {
        // console.log("errorCallback = "+errorCallback);
        return errorCallback && errorCallback();
      }
    );

    this.http.get(this.authUserUrl, { headers: this.headers, withCredentials:true }).subscribe( (resposnse) => {
      if (resposnse.hasOwnProperty('name')){
        // console.log("In If : "+resposnse);
        
        LoginService.authenticatedUser = true;
        sessionStorage.setItem('id', resposnse["principal"]['username']);
        sessionStorage.setItem('myrole', 'user');
      }else{
        // console.log( resposnse);
        LoginService.authenticatedUser = false;
      }
      // console.log("successCallback = " +successCallback)
      return successCallback && successCallback();
    },
    (error) => {
      // console.log("errorCallback = "+errorCallback);
      return errorCallback && errorCallback();
    }
  );
  }

  logout(successCallback, errorCallback){
    this.http.post(this.logoutUrl, {}, {withCredentials:true}).subscribe(
      (succsess) => {
        return successCallback && successCallback();
      },
      (error) => {
        console.log(error);
        return errorCallback && errorCallback();
      });
  }
}
