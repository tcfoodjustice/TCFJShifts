package com.tcfj.webservice.service;

import org.springframework.stereotype.Component;

/**
 * Created by MK on 1/16/2017.
 */
@Component
public class GreetingService {
    public String getGreeting(String greeting){
        return greeting + "hello";
    }


}
