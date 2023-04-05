package com.job.watchlist.controller;

import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.dto.RemoveRequest;
import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import com.job.watchlist.service.IWishListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RequestMapping("/wishListJobs")
@RestController
public class WishListController {
        @Autowired
        private IWishListService service;

        @PostMapping("/addToWishList")
        public JobDetail addToWishList(@RequestBody AddRequest requestData) throws JobAlreadyExistException {
                JobDetail response = service.addToWishlist(requestData);
                return response;
        }
        @GetMapping("/listBy/{username}")
        public List<JobDetail> fetchAll(@PathVariable String username) throws NoJobFoundException {
                List<JobDetail>response=service.listByUsername(username);
                return response;
        }
        @DeleteMapping("/delete")
        public void removeJob(@RequestBody RemoveRequest requestData) throws NoJobFoundException {
                service.remove(requestData);
        }
}
