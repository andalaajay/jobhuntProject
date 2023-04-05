import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from 'src/app/model/Job';
import { JobService } from 'src/app/services/service';
import { JobUtil } from 'src/app/util/job.util';




@Component({
  selector: 'app-list-jobs',
  templateUrl: './list-jobs.component.html',
  styleUrls: ['./list-jobs.component.css']
})
export class ListJobsComponent  {
  jobs: Job[] | undefined;
  jobUtil: JobUtil = new JobUtil();
  addedJob: Job | undefined;
  constructor(  private watchListService: JobService) {
    watchListService.fetchJobs().subscribe((data) => {
      console.log("inside lis jobs data", data);

      this.jobs = this.jobUtil.toJobs(data);

      console.warn("data", this.jobs);

    });
  }

  errorMsg: String | undefined;
  deletedJobItm: Job | undefined;

  addToWatchList(chosen: Job) {

    console.log(chosen);
    

    const observer = {
      next: (result: Job) => {
        this.addedJob = result;

      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Job> = this.watchListService.addToWatchList(chosen);
    observable.subscribe(observer);


  }



}


