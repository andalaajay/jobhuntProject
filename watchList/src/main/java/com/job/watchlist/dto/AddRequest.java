package com.job.watchlist.dto;


import com.job.watchlist.entity.Location;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
public class AddRequest {
    @Min(1)
    private long externalServerId;
    @NotBlank
    @Length(max = 100)
    private String name;
    @NotBlank
    private String content;
    @NotBlank
    @Length(max = 20)
    private String publicationDate;
    @NotBlank
    @Length(max = 100)
    private String shortName;
    private List<Location> locations;
    @NotBlank
    @Length(max = 30)
    private String company;
    private String category;
    @NotBlank
    @Length(min = 4, max = 20)
    private String username;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
