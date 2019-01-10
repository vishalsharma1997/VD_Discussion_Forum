import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../user';
import { LoginService } from '../../services/login.service';
import { Router } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-listusers',
  templateUrl: './listusers.component.html',
  styleUrls: ['./listusers.component.css']
})
export class ListusersComponent implements OnInit {

  private users:any;
  private tablerows:any;
  // error:any;
  // iserror = false;
  constructor(private _userService:UserService , private router : Router) {
    this.tablerows = this._userService.getTableRows();
   }

  ngOnInit() {
    this._userService.getUsers().subscribe( ( users:Response) => {
      // console.log("Users Are : ");
      // console.log(users.json());
      this.users = users.json();
    },(error) => {
      console.log(error);
      // this.iserror = true;
      // this.error =error;
    });
  }

  deleteUser(emailId){
    console.log(emailId);
    this._userService.deleteUser(emailId);
    console.log("User Deleted !!!");
      //window.location.reload();
  }

  isLoggedInUser(emailId){
    // console.log("Emails are :");
    // console.log(emailId);
    // console.log(sessionStorage.getItem('id'));
    if(sessionStorage.getItem('id') == emailId){
      return true;
    }
    return false;
  }

  // haserror(){
  //   console.log(this.error);
  //   return this.iserror;
  // }
  // pagenotfound(){
  //   this.router.navigateByUrl("pagenotfound");
  // }

  

}
