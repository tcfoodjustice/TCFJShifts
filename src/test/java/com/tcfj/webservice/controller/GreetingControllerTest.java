package com.tcfj.webservice.controller;

import com.tcfj.webservice.service.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by andrew.larsen on 12/26/2016.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {
    //this is used to send fake http calls to the controller method
    @Autowired
    private MockMvc mvc;
    @MockBean
    GreetingService greetingService;
    private String greeting;
    private String rsp;
    @Before
    public void setup(){
        greeting = "greeting";
        rsp = "goodjob";

    }


    @Test
    public void testGreeting() throws Exception {
        this.mvc.perform(get("/greetings/greeting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGreetingString() throws Exception {
        given(greetingService.getGreeting(greeting)).willReturn(rsp);
        this.mvc.perform(get("/greetings/" + greeting)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(rsp)));

    }
}