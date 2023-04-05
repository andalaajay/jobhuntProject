import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 

  usernameCtrl:FormControl;
  passwordCtrl:FormControl;
  myForm:FormGroup;
  errMsg:string|undefined;
  
    constructor(builder:FormBuilder, private authService:AuthenticationService,
      private router:Router) { 
      this.usernameCtrl=builder.control('',[Validators.required]);
      this.passwordCtrl=builder.control('',[Validators.required]);
      this.myForm=builder.group({
        username: this.usernameCtrl,
        password: this.passwordCtrl
      });
    }
  
    ngOnInit(): void {
    }
  
  
    onFormSubmit(){
      const username=this.usernameCtrl.value;
      const password=this.passwordCtrl.value;
      
      const observable:Observable<string>=this.authService.login(username,password);
      observable.subscribe({
        next:(token:string)=>{
          console.log("received token",token);
          this.errMsg=undefined;    
          this.authService.saveToken(username,token);
          this.router.navigate(['/listjobs']);
        },
        error:(err:Error)=>{
        this.errMsg=err.message;
        alert("incorrect credentials or user doesnot exist");
        }
      });
      console.log("inside onFormSubmit username="+username+" ,password="+password);
        
    }
    isControlTouchedOrDirty(control:FormControl){
      return (control.touched || control.dirty);
      }
      
      isControlRequireValid(control:FormControl){
        const touchedOrDirty=this.isControlTouchedOrDirty(control);
        if(!touchedOrDirty){
          return true;
        }
        return !control.errors?.['required'];
      }
      
      isUserNameCtrlRequireValid(){
        const valid=this.isControlRequireValid(this.usernameCtrl);
         return valid;  
      }
      isPasswordCtrlRequireValid(){
        const valid=this.isControlRequireValid(this.passwordCtrl);
         return valid;  
      }
  
}
