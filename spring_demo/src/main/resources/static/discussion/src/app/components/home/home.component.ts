import { Component, OnInit } from '@angular/core';
import { Router } from '../../../../node_modules/@angular/router';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = {}
  myposts:any;
  myRole = '';
  constructor(private userservice : UserService , private service : LoginService , private router : Router) { 
    this.service.authenticate(undefined,undefined,undefined);
  }

  ngOnInit() {
    this.getUserPosts(this.LoggedInUserName());
  }
  getUserPosts(emailId){
    this.userservice.getUserPosts(emailId).subscribe( ( posts:Response) => {
    console.log("Posts Are : ");
    console.log(posts.json());
    this.myposts = posts.json();
  },(error) => {
    console.log(error);
  });
}

signUp(){
  this.userservice.addUser(this.user);
}

isLoggedIn(){
  return LoginService.authenticated || LoginService.authenticatedUser;
}

LoggedInUserName(){
  return this.userservice.getLoggedInUserName();
}
isAdmin(){
  return LoginService.authenticated;
}

goToViewUsers(){
  this.router.navigateByUrl("viewusers");
}
goToViewPosts(){
  this.router.navigateByUrl("viewposts");
}
goToAddPost(){
  this.router.navigateByUrl("addpost");
}

hasPosts(){
  return this.myposts.length  > 0;
}


}
