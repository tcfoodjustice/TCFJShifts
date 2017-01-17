package com.tcfj.webservice.controller;

import com.tcfj.webservice.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by MK on 10/23/2016.
 */

@RestController
public class GreetingController {

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    GreetingService greetingService;


    @RequestMapping("/greetings/{greeting}")
    public String greeting(@PathVariable String greeting) {
        String result =  greetingService.getGreeting(greeting);
        return result;
    }

}
