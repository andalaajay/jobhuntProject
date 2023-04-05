import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent  {
  

 
  constructor(private authService:AuthenticationService)
  {

  }
  displayUserDetails()
  {
   const userName=this.authService.getUsername();
   return userName;
  }
}
