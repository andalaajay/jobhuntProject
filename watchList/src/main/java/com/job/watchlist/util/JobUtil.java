package com.job.watchlist.util;

import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.entity.WishListJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JobUtil {

    public JobDetail toJobDetail(WishListJob job) {
        JobDetail desired = new JobDetail();
        desired.setExternalServerId(job.getExternalServerId());
        desired.setName(job.getName());
        desired.setContent(job.getContent());
        desired.setPublicationDate(job.getPublicationDate());
        desired.setShortName(job.getShortName());
        desired.setLocations(job.getLocations());
        desired.setCompany(job.getCompany());
        desired.setCategory(job.getCategory());
        return desired;
    }

    public WishListJob toJob(AddRequest job) {
        WishListJob desired = new WishListJob();
        desired.setExternalServerId(job.getExternalServerId());
        desired.setName(job.getName());
        desired.setContent(job.getContent());
        desired.setPublicationDate(job.getPublicationDate());
        desired.setShortName(job.getShortName());
        desired.setLocations(job.getLocations());
        desired.setCompany(job.getCompany());
        desired.setCategory(job.getCategory());
        desired.setUsername(job.getUsername());
        return desired;
    }

    public List<JobDetail> toListJobDetails(Collection<WishListJob> jobs) {
        List<JobDetail> desired = new ArrayList<>();
        for (WishListJob iterator : jobs) {
            JobDetail details = toJobDetail(iterator);
            desired.add(details);
        }
        return desired;
    }
}

