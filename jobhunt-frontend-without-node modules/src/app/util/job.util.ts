import { Job } from "../model/Job";



export class JobUtil {
    getUsername() {
      throw new Error('Method not implemented.');
    }
    toJob(jobData: any): Job {
       
        const job = new Job();
        job.externalServerId=jobData.id;
        job.shortName = jobData.short_name;
        job.content = jobData.contents;
        job.publicationDate = jobData.publication_date;
        job.locations = jobData.locations;
        job.name = jobData.name;
        job.company = jobData.company.name;
        console.log("categories",jobData.categories);
       if(jobData.categories && jobData.categories.length > 0){
           
        job.category = jobData.categories[0].name;
       }
        return job;
    }

    toJobs(data: any): Job[] {
       
        const jobs: Job[] = [];
        const jobsData = data.results;
        for (let jobData of jobsData) {
            const job: Job = this.toJob(jobData);
            jobs.push(job);
        }

        return jobs;
    }
    
  

}




