import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../user';
import { LoginService } from '../../services/login.service';

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
  constructor(private _userService:UserService) {
    this.tablerows = this._userService.getTableRows();
   }

  ngOnInit() {
    this._userService.getUsers().subscribe( ( users:Response) => {
      console.log("Users Are : ");
      console.log(users.json());
      // console.log("Aurthorities : ");
      // console.log((users.json())[0]['authorities'][0]['authority']);
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
      window.location.reload();
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
  
   isAdmin(user){
    //  console.log(user);
    //  user.authorities.forEach(element => {
    //    console.log(element);
       
    //  });
    for(var i = 0 ; i < user.authorities.length;i=i+1){
      if(user.authorities[i]['authority'] == 'ROLE_ADMIN'){
        //console.log("Email : " + user.emailId);
        return true;
      }
    }
    return false;
  }
  
  makeAdmin(emailId,user){
    // console.log("User ->  is : ");
    // console.log(user);
    this._userService.makeAdmin(emailId,user);
     //window.location.reload();
  }

  // haserror(){
  //   console.log(this.error);
  //   return this.iserror;
  // }
  // pagenotfound(){
  //   this.router.navigateByUrl("pagenotfound");
  // }

}
