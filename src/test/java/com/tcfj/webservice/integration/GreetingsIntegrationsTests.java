package com.tcfj.webservice.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by MK on 1/16/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingsIntegrationsTests {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void testGreeting() throws Exception {
        ResponseEntity<String> resp = this.testRestTemplate.getForEntity("/greetings/test",String.class);

        assertThat(resp.getStatusCode(), is(HttpStatus.OK));

    }
    @Test
    public void testGreetingString() throws Exception {
        ResponseEntity<String> resp = this.testRestTemplate.getForEntity("/greetings/test",String.class);

        assertThat(resp.getBody(), is("testhello"));

    }
}
