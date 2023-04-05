import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { baseServerUrl } from '../common/constants';
import { Job } from '../model/Job';

import { AuthenticationService } from './authentication.service';

@Injectable({ providedIn: 'root' })
export class JobService {

    constructor(private client: HttpClient,private authService:AuthenticationService) { }

    fetchJobs() {
      console.log("inside fetch jobs");
      return this.client.get("https://www.themuse.com/api/public/jobs?page=0");
    }
   
  getWatchList():Observable<Job[]>
  { 
    const username=this.authService.getUsername();
    const url=baseServerUrl+"/wishListJobs/listBy/"+username;
    const observable:Observable<Job[]>=this.client.get<Job[]>(url);
    return observable;
  }

  removeWatchList( externalServerId:number):Observable<void>
  {  
    const username=this.authService.getUsername();
    const requestData={
      username:username,
      externalServerId:externalServerId
    };
    const options={
      body:requestData
    }

    const url=baseServerUrl+"/wishListJobs/delete/";
    const observable:Observable<void>=this.client.delete<void>(url,options);
    return observable;
  }

 addToWatchList(job:Job): Observable<Job> {
  const username=this.authService.getUsername();
  const requestData={...job,username};  
  const url = baseServerUrl+"/wishListJobs/addToWishList";
    const observable: Observable<Job> = this.client.post<Job>(url, requestData);
    return observable;

  }  
}