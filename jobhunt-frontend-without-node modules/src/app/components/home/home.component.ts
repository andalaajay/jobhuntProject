import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  {

  constructor(private authService:AuthenticationService,private router:Router) { }
  isLoggedIn():boolean{
    return this.authService.isLoggedIn();
}
login()
{
  if(this.isLoggedIn())
  {
    this.router.navigate(['/listjobs']);
  }
  else
  {
    this.router.navigate(['/loginUser']);
  }
}
register()
{
  this.router.navigate(['/registerUser']);
}
}
