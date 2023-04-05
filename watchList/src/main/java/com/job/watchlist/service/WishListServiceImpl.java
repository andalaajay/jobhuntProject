package com.job.watchlist.service;

import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.dto.RemoveRequest;
import com.job.watchlist.entity.WishListJob;
import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import com.job.watchlist.repository.IWishListRepository;
import com.job.watchlist.util.JobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 *this is service implementation class for wishlistservice
 */
@Service
public class WishListServiceImpl implements IWishListService{
    @Autowired
    private IWishListRepository repository;
    @Autowired
    private JobUtil util;

    // private long generatedId;
    public String generateUniqueId(long externalServerId, String username) {
        String id = externalServerId +"-u-"+username;
        return id;
    }


    /**
     * save wishlList job for a user  and returns JobDetails
     * If Job already found in the wishList then JobAlreadyExistException exception is thrown
     * * @param requestData of type AddRequest
     * @return JobDetail
     * @throws JobAlreadyExistException If alreadt exist for a user
     */
    @Override
    public JobDetail addToWishlist(AddRequest requestData) throws JobAlreadyExistException {

        Optional<WishListJob> optional = repository.findByUsernameAndExternalServerId(requestData.getUsername(),
                requestData.getExternalServerId());

        if (optional.isPresent()) {
            throw new JobAlreadyExistException("Job is already in the wishlist list");
        }

        WishListJob jobWish= util.toJob(requestData);
        String uniqueId = generateUniqueId(requestData.getExternalServerId(), requestData.getUsername());
        jobWish.setId(uniqueId);
        jobWish = repository.save(jobWish);
        JobDetail details = util.toJobDetail(jobWish);
        return details;
    }



    /**
     * Remove the  job from the wishList
     * @param requestData:RemoveRequest
     * @throws NoJobFoundException if user doesnot have any jobs in the wishlist
     */
    @Override
    public void remove(RemoveRequest requestData) throws NoJobFoundException {
        Optional<WishListJob> optional = repository.findByUsernameAndExternalServerId(requestData.getUsername(), requestData.getExternalServerId());
        if (!optional.isPresent()) {
            throw new NoJobFoundException("No Job Found");

        }
        WishListJob jobWish = optional.get();
        repository.delete(jobWish);
    }

    /**
     * List the wishList jobs for a user by taking input as username
     * @param username of type string
     * @return List of wishList jobs for a user
     * @throws NoJobFoundException if usernot have any jobs in the wishList
     */
    @Override
    public List<JobDetail> listByUsername(String username) throws NoJobFoundException {

        List<WishListJob> jobs = repository.findByUsername(username);
        if (jobs.isEmpty()) {
            throw new NoJobFoundException("your watch list is empty");
        }
        List<JobDetail> desired = util.toListJobDetails(jobs);
        return desired;
    }


}


