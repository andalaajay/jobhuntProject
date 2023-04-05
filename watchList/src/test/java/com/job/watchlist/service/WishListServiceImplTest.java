package com.job.watchlist.service;

import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.dto.RemoveRequest;
import com.job.watchlist.entity.WishListJob;
import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import com.job.watchlist.repository.IWishListRepository;
import com.job.watchlist.util.JobUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class WishListServiceImplTest {

    @Mock
    JobUtil util;
    @Mock
    IWishListRepository repo;
    @InjectMocks
    @Spy
    WishListServiceImpl service;

    /**
     * scenario:jobs fetched successfully
     * input:username="ajay"
     * expectations:detailsList mock we created is same as the fetched result
     * verify IWishListRepository#findByUserName(*) is Called Only Once
     */
    @Test
    public void testListByUserName_1() throws Exception {
        String username = "ajay";
        List<WishListJob> jobs = mock(List.class);
        when(repo.findByUsername(username)).thenReturn(jobs);
        when(jobs.isEmpty()).thenReturn(false);
        List<JobDetail> detailsList = mock(List.class);
        when(util.toListJobDetails(jobs)).thenReturn(detailsList);
        List<JobDetail> result = service.listByUsername(username);
        assertSame(detailsList, result);
        verify(repo).findByUsername(username);
    }

    /**
     * scenario:jobs not fetched successfully,NoJobFoundException is thrown
     * input:username="ajay"
     * expectations:NoJobFoundException is thrown
     * verify IWishListRepository#findByUserName(*) is Called Only Once
     */

    @Test
    public void testListByUserName_2() throws Exception {
        String username = "ajay";
        List<WishListJob> jobs = mock(List.class);
        when(repo.findByUsername(username)).thenReturn(jobs);
        when(jobs.isEmpty()).thenReturn(true);
        Executable executable = () -> service.listByUsername(username);
        assertThrows(NoJobFoundException.class, executable);
        verify(repo).findByUsername(username);
    }

    /**
     * scenario:job is added successfully in the watchlist by a user
     * input:addRequest which contains username="ajay", and externalServerId=100;
     * expectation:WishListJob successfully saved, result is same as expected detail object
     * verify IWishListRepository#save(*) is Called Once
     */
    @Test
    public void testAddToWatchList_1() throws Exception {
        String username = "ajay";
        long externalServerId = 100;
        AddRequest request = new AddRequest();
        request.setUsername(username);
        request.setExternalServerId(externalServerId);
        WishListJob job = mock(WishListJob.class);
        WishListJob savedJob = mock(WishListJob.class);
        when(util.toJob(request)).thenReturn(job);
        JobDetail detail = mock(JobDetail.class);
        when(repo.save(job)).thenReturn(savedJob);
        when(util.toJobDetail(savedJob)).thenReturn(detail);
        Optional<WishListJob> optional = Optional.empty();
        when(repo.findByUsernameAndExternalServerId(username, externalServerId)).thenReturn(optional);
        JobDetail result = service.addToWishlist(request);
        assertSame(detail, result);
        verify(repo).save(job);
    }

    /**
     * scenario:job is not added successfully in the watchlist by a user,JobAlreadyExistException is thrown
     * input:addRequest which contains username="ajay", and externalServerId=100;
     * expectation:JobAlreadyExistException is thrown
     * verify IWishListRepository#save(*) is never called
     */
    @Test
    public void testAddToWatchList_2() throws Exception {
        String username = "ajay";
        long externalServerId = 100;
        AddRequest request = new AddRequest();
        request.setUsername(username);
        request.setExternalServerId(externalServerId);
        WishListJob job = new WishListJob();
        Optional<WishListJob> optional = Optional.of(job);
        when(repo.findByUsernameAndExternalServerId(username, externalServerId)).thenReturn(optional);
        Executable executable = () -> service.addToWishlist(request);
        assertThrows(JobAlreadyExistException.class, executable);
    }

    /**
     *
     */

    @Test
    public void testRemove_1()throws Exception
    {
        RemoveRequest request = mock(RemoveRequest.class);
        WishListJob job = mock(WishListJob.class);
        String username= request.getUsername();
        Optional<WishListJob> optional=Optional.of(job);
        long externalServerId=request.getExternalServerId();
        Mockito.doReturn(optional).when(repo).findByUsernameAndExternalServerId(username,externalServerId);
        Mockito.doNothing().when(repo).delete(job);
        service.remove(request);
    }
    /**
     * scenario:no watchListjobs found for the user, NoJobFoundException is thrown
     * input:removeRequest which contains username="ajay", and externalServerId=100;
     * expectation:NoJobFoundFoundException is thrown
     * verify IWishListRepository#remove(*) is never called
     */

    @Test
    public void testRemove_2() throws Exception {
        String username = "ajay";
        long externalServerId = 100;
        RemoveRequest request = new RemoveRequest();
        request.setUsername(username);
        request.setExternalServerId(externalServerId);
        WishListJob job = new WishListJob();
        Optional<WishListJob> optional = Optional.empty();
        when(repo.findByUsernameAndExternalServerId(username, externalServerId)).thenReturn(optional);
        Executable executable = () -> service.remove(request);
        assertThrows(NoJobFoundException.class, executable);
    }

}