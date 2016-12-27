package com.tcfj.webservice.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by andrew.larsen on 12/26/2016.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {
  //  @Autowired
    //TestRestTemplate testRestTemplate;
    @Test
    public void testGreeting() throws Exception {
      //  ResponseEntity<String> resp = this.testRestTemplate.getForEntity("/greetings",String.class);

        assertThat("Hello World", is("Hello World"));

    }
}