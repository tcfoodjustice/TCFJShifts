package com.tcfj.webservice.service;

import org.assertj.core.util.Compatibility;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MK on 1/16/2017.
 */
public class GreetingServiceTest {
    private String greeting;
    private GreetingService greetingService;
    @Before
    public void setUp() throws Exception {
        greeting = "Testing";
        greetingService = new GreetingService();

    }

    @Test
    public void getGreeting() throws Exception {
        String result = greetingService.getGreeting(greeting);
        assertEquals(result, "Testinghello");
    }

}