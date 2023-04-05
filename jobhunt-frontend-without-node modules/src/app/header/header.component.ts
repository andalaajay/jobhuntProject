import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public searchTerm !: string;
  CartService: any;
  public totalItem:number=0;
  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit(): void {
   
  }
  isLoggedIn():boolean{
    return this.authService.isLoggedIn();
}
  logout(){
    this.authService.logout();
    this.router.navigate(["/home"]);
  }


  
}
