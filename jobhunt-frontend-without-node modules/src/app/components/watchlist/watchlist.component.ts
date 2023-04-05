import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from 'src/app/model/Job';
import { JobService } from 'src/app/services/service';





@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent {
  watchListArray: Job[] | undefined;
  errorMsg: string|undefined;
 

  constructor( private service: JobService) {
    this.fetchAllJobs();
    console.log(this.watchListArray?.length);
  }

  fetchAllJobs() {
    const observer = {
      next: (result: Job[]) => {
        this.watchListArray = result;
        console.log(this.watchListArray.length);
      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<Job[]> = this.service.getWatchList();
    observable.subscribe(observer);
  }

  remove(externalServerId: number) {
    const observer = {
      next: (result: void) => {
        this.removeUnfavourited(externalServerId);
        //console.log("removed From watchList");
        this.fetchAllJobs();

      },
      error: (error: Error) => {
        this.errorMsg = error.message;
      },
    };
    const observable: Observable<void> =
      this.service.removeWatchList(externalServerId);
    observable.subscribe(observer);
  }

  removeUnfavourited(externalServerId: number) {
    if (!this.watchListArray) {
      return;
    }
    let index = -1;
    for (let job of this.watchListArray) {
      index++;
      if (job.externalServerId == externalServerId) {
        this.watchListArray.slice(index);
      }

    }

  }
}
