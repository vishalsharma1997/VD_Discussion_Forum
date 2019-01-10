import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '../../../../node_modules/@angular/router';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-addpost',
  templateUrl: './addpost.component.html',
  styleUrls: ['./addpost.component.css']
})
export class AddpostComponent implements OnInit {

  post ={}
  postAdded = false;

  constructor(private userservice : UserService, private router : Router) { }

  ngOnInit() {
  }

  addPost(){
    if(LoginService.authenticatedUser){
      this.userservice.addUserPost(this.post);
    }
    if(LoginService.authenticated){
      this.userservice.addPost(this.post);
    }
    console.log("Post Added !!!");
    this.postAdded = true;
    this.router.navigateByUrl("");
    // window.location.reload();
  }

  isLoggedIn(){
    return LoginService.authenticated || LoginService.authenticatedUser;
  }

}
