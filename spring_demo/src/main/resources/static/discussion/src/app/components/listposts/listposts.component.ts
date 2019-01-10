import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import { Router } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-listposts',
  templateUrl: './listposts.component.html',
  styleUrls: ['./listposts.component.css']
})
export class ListpostsComponent implements OnInit {

  private posts:any;
  private tablerows:any;
  // error:any;
  // iserror = false;
  constructor(private _userService:UserService ,private router : Router) {
    this.tablerows = this._userService.getTableRows();
   }

  ngOnInit() {
    this.getAllPosts();
  }

  getAllPosts(){
    this._userService.getPosts().subscribe( ( posts:Response) => {
      console.log("Posts Are : ");
      console.log(posts.json());
      this.posts = posts.json();
    },(error) => {
      console.log(error);
       // this.iserror = true;
      // this.error =error;
    });
  }

  deletePost(postId){
    console.log(postId);
      this._userService.deletePost(postId);
      console.log("Post Deleted !!!");
      window.location.reload();
  }

  goToAddPost(){
    this.router.navigateByUrl("addpost");
  }
  
  // haserror(){
  //   console.log(this.error);
  //   return this.iserror;
  // }
  // pagenotfound(){
  //   this.router.navigateByUrl("pagenotfound");
  // }


}
