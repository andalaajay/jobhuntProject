package com.job.watchlist.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * request dto for removing  job
 * @author:ajay,arvinder,saketh krishna,jitendra
 */
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveRequest request = (RemoveRequest) o;
        return externalServerId == request.externalServerId && Objects.equals(username, request.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalServerId, username);
    }
}
