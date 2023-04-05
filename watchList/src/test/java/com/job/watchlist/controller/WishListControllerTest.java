package com.job.watchlist.controller;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.watchlist.dto.AddRequest;
import com.job.watchlist.dto.JobDetail;
import com.job.watchlist.dto.RemoveRequest;
import com.job.watchlist.entity.Location;
import com.job.watchlist.exception.JobAlreadyExistException;
import com.job.watchlist.exception.NoJobFoundException;
import com.job.watchlist.service.IWishListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    private JobDetail response;

    @MockBean
    IWishListService service;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    public void setUp() {
        response = new JobDetail();
        response.setExternalServerId(12310L);
        response.setName("Java Fullstack Developer");
        response.setContent("Tst");
        response.setPublicationDate("12/3/2022");
        response.setShortName("JSD");
        List<Location> locs1 = new ArrayList<>();
        Location loc1 = new Location();
        loc1.setName("pune");
        locs1.add(loc1);
        response.setLocations(locs1);
        response.setCompany("TCS");
        response.setCategory("Hello");
    }

    @AfterEach
    public void tearDown() {
        response = null;
    }

    /**
     * scenario: When Job is added successfully input : AddRequest expectation: Job
     * is added successfully. status 200/Ok
     */
    @Test
    public void testAddToWishList_1() throws Exception {

        AddRequest request = new AddRequest();
        request.setExternalServerId(12310L);
        request.setName("Java Fullstake Developer");
        request.setContent("Tst");
        request.setPublicationDate("12/3/2022");
        request.setShortName("JSD");
        List<Location> locs1 = new ArrayList<>();
        Location loc1 = new Location();
        loc1.setName("pune");
        locs1.add(loc1);
        request.setLocations(locs1);
        request.setCompany("TCS");
        request.setCategory("Hello");
        request.setUsername("ajay");
        when(service.addToWishlist(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String jsonResponse = objectMapper.writeValueAsString(response);
        String url = "/wishListJobs/addToWishList";
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk()).andExpect(content().json(jsonResponse));
        verify(service).addToWishlist(request);

    }

    /**
     * scenario: When Job is already exists
     * input : AddRequest
     * expectation:JobAlreadyExistException is thrown. status 400/BAD_REQUEST
     */
    @Test
    public void testAddToWishList_2() throws Exception {

        AddRequest request = new AddRequest();
        request.setExternalServerId(12310L);
        request.setName("Java Fullstake Developer");
        request.setContent("Tst");
        request.setPublicationDate("12/3/2022");
        request.setShortName("JSD");
        List<Location> locs1 = new ArrayList<>();
        Location loc1 = new Location();
        loc1.setName("pune");
        locs1.add(loc1);
        request.setLocations(locs1);
        request.setCompany("TCS");
        request.setCategory("Hello");
        request.setUsername("mohit");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String msg = "Job is already in the Wishlist";
        JobAlreadyExistException e = new JobAlreadyExistException(msg);
        when(service.addToWishlist(any(AddRequest.class))).thenThrow(e);
        String url = "/wishListJobs/addToWishList";
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isBadRequest()).andExpect(content().string(msg));

    }


    /**
     * scenario: When list is fetched successfully input : username=ajay
     * expectation: List is fetched successfully. status 200/OK
     */

    @Test
    public void fetchAll_1() throws Exception {
        String username = "mohit";
        List<JobDetail> tracks = new ArrayList<>();
        tracks.add(response);
        when(service.listByUsername(username)).thenReturn(tracks);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(tracks);
        System.out.println("**created json=" + json);
        String url = "/wishListJobs/listBy/" + username;
        mvc.perform(get(url)).andExpect(status().isOk()).andExpect(content().json(json));

    }


    /**
     * scenario: No job is found in the Wishlist input : username=ajay
     * expectation: NoJobFoundException is thrown. status 404/NOT_FOUND
     */

    @Test
    public void fetchAll_2() throws Exception {
        String username = "mohit";
        String msg = "No track found";
        NoJobFoundException e = new NoJobFoundException(msg);
        when(service.listByUsername(username)).thenThrow(e);
        String url = "/wishListJobs/listBy/" + username;
        mvc.perform(get(url)).andExpect(status().isNotFound()).andExpect(content().string(msg));

    }


    /**
     * scenario: When Job is removed successfully input : RemoveRequest expectation
     * Job is removed successfully. status 200/OK
     */
    @Test
    public void testremoveJob_1() throws Exception {
        RemoveRequest request = new RemoveRequest();
        request.setExternalServerId(12310L);
        request.setUsername("mohit");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/wishListJobs/delete";
        mvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk());
        verify(service).remove(request);
    }


    /**
     * scenario: When Job is not found input : RemoveRequest expectation:
     * NoJobFoundException. status 404/NOT_FOUND
     */
    @Test
    public void testremoveJob_2() throws Exception {
        RemoveRequest request = new RemoveRequest();
        request.setExternalServerId(12310L);
        request.setUsername("mohit");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);
        String url = "/wishListJobs/delete";
        String msg = "No Job found";
        NoJobFoundException e = new NoJobFoundException(msg);
        doThrow(e).when(service).remove(request);
        mvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isNotFound()).andExpect(content().string(msg));
        verify(service).remove(request);
    }
}
