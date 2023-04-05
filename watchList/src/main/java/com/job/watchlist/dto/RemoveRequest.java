package com.job.watchlist.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RemoveRequest {
    @Min(1)
    private long externalServerId;
    @NotBlank
    @Length(min = 4, max = 20)
    private String username;

    public long getExternalServerId() {
        return externalServerId;
    }

    public void setExternalServerId(long externalServerId) {
        this.externalServerId = externalServerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
