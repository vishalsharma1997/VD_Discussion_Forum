import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListusersComponent } from './components/listusers/listusers.component';
import { UserService } from './services/user.service';
import { LoginComponent } from './components/login/login.component';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ListpostsComponent } from './components/listposts/listposts.component';
import { LoginService } from './services/login.service';
import { AddpostComponent } from './components/addpost/addpost.component';
import { HomeComponent } from './components/home/home.component';
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';


const appRoutes : Routes = [
  {path : 'viewusers',component:ListusersComponent},
  {path : 'login' , component:LoginComponent},
  {path : 'viewposts' , component:ListpostsComponent},
  {path : 'addpost' , component:AddpostComponent},
  {path : '' , component:HomeComponent},
  {path : '**' , component:PagenotfoundComponent}
];

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    ListusersComponent,
    AppComponent,
    LoginComponent,
    ListpostsComponent,
    AddpostComponent,
    HomeComponent,
    PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    HttpModule
  ],
  providers: [UserService,LoginService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
