import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from '../../../node_modules/rxjs';
import { Post } from '../post';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private tablerows = ["table-primary", "table-info", "table-success", "table-danger", "table-warning"]
  private baseAdminUrl = "http://localhost:8000/admin";
  private baseUserUrl = "http://localhost:8000/user";
  private baseUrl = "http://localhost:8000";
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers, withCredentials: true });

  constructor(private _http: Http) { }

  getUsers(): Observable<any> {
    return this._http.get(this.baseAdminUrl + '/viewusers', this.options);

  }

  getPosts(): Observable<any> {
    return this._http.get(this.baseAdminUrl + '/viewposts', this.options);
  }

  getUserPosts(emailId): Observable<any> {
    return this._http.get(this.baseUrl+ '/' + sessionStorage.getItem('myrole') + '/myposts/'+emailId, this.options);
  }

  addPost(post) {
    console.log("post is :");
    console.log(post);
    this._http.post(this.baseAdminUrl + '/addpost', post, this.options).subscribe(response => { console.log(response.json()) });
  }

  addUserPost(post) {
    console.log("post is :");
    console.log(post);
    this._http.post(this.baseUserUrl + '/savepost', post, this.options).subscribe(response => { console.log(response.json()) });
  }

  addUser(user) {
    console.log("user is :");
    console.log(user);
    this._http.post( this.baseUrl+ '/signup', user, this.options).subscribe(response => { console.log(response)});
  }

  deletePost(postId) {
    console.log("Post Id Is : ");
    console.log(postId);
    this._http.delete(this.baseAdminUrl + '/deletepost/' + postId, this.options).subscribe(response => console.log(response));

  }

  deleteUser(emailId) {
    console.log("Email Id Is : ");
    console.log(emailId);
    this._http.delete(this.baseAdminUrl + '/deleteuser/' + emailId, this.options).subscribe(response => console.log(response));

  }
  makeAdmin(emailId,user) {
    console.log("Email Is : ");
    console.log(emailId);
    console.log(user);
    this._http.put(this.baseAdminUrl + '/makeadmin/'+ emailId, user, this.options).subscribe(response => console.log(response));

  }


  getTableRows() {
    return this.tablerows;
  }
  
  getLoggedInUserName(){
    return sessionStorage.getItem('id');
  }


}
