package com.job.watchlist.dto;

import com.job.watchlist.entity.Location;

import java.util.List;


/**
 * request dto for  job details
 * @author:ajay,arvinder,saketh krishna,jitendra
 */
public class JobDetail {
    private long externalServerId;
    private String name;
    private String content;
    private String publicationDate;
    private String shortName;
    private List<Location> locations;
    private String company;
    private String category;

    public long getExternalServerId() {
        return externalServerId;
    }

    public void setExternalServerId(long externalServerId) {
        this.externalServerId = externalServerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}