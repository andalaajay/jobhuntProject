import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Register } from 'src/app/model/Register';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
 userRegister:Register|undefined;

 usernameCtrl:FormControl;
 passwordCtrl:FormControl;
 errMsg:string|undefined;
 myform:FormGroup;



 constructor(builder:FormBuilder,private authService:AuthenticationService,private router:Router){
   this.usernameCtrl=builder.control('',[Validators.required, Validators.minLength(4),Validators.maxLength(10)]);
   this.passwordCtrl=builder.control('',[Validators.required,Validators.minLength(4),Validators.maxLength(10)]);
   const mappingObj={
    username:this.usernameCtrl,
    password:this.passwordCtrl
  };
   this.myform=builder.group(mappingObj);
 }

  registerUser() {
    if(!this.myform.valid){
      this.myform.markAllAsTouched();
      return;
    }
   
    const username:string=this.usernameCtrl.value;
   
    const password:string=this.passwordCtrl.value;
    this.userRegister=new Register(username,password); 
    
    const observable:Observable<void>=this.authService.register(username,password);
      observable.subscribe({
        next:(result:void)=>{  
          this.router.navigate(['/loginUser']);
        },
        error:(err:Error)=>{
        this.errMsg=err.message;
        alert("username already taken");
        }
      });
      console.log("inside onFormSubmit in registre username="+username+" ,password="+password);  
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

  isNameCtrlRequireValid(){
    const valid=this.isControlRequireValid(this.usernameCtrl);
     return valid;  
  }
  
  isPasswordCtrlRequireValid(){
    const valid=this.isControlRequireValid(this.passwordCtrl);
     return valid;  
  }

  isNameCtrlMinLengthValid(){
    const touchedOrDirty=this.isControlTouchedOrDirty(this.usernameCtrl);
    if(!touchedOrDirty){
      return true;
    }
    return  !this.usernameCtrl.errors?.['minlength']
  }
  isPasswordCtrlMinLengthValid(){
    const touchedOrDirty=this.isControlTouchedOrDirty(this.passwordCtrl);
    if(!touchedOrDirty){
      return true;
    }
    return  !this.passwordCtrl.errors?.['minlength']
  }
 
}
