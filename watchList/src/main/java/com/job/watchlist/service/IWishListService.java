package com.job.watchlist.service;

import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.dto.RemoveRequest;
import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Validated
public interface IWishListService {
    JobDetail addToWishlist(@Valid AddRequest requestData) throws JobAlreadyExistException;

    void remove(@Valid RemoveRequest requestData) throws NoJobFoundException;

    List<JobDetail> listByUsername(@NotBlank @Length(min=4,max=20) String username) throws NoJobFoundException;



}
