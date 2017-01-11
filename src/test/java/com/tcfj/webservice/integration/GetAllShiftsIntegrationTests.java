package com.tcfj.webservice.integration;

import com.tcfj.webservice.model.Shift;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by andrew.larsen on 1/1/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class GetAllShiftsIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate;
    private String url = "/tcfj/v1/shifts";
    @Test
    public void test200Ok(){
        ResponseEntity<String> resp = this.testRestTemplate.getForEntity(url,String.class);
        assertThat(resp.getStatusCode(), is (HttpStatus.OK));
    }
    @Test
    public void testShiftsReturnsCorrentNumberOfShifts(){
        ResponseEntity<List<Shift>> resp = this.testRestTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference < List < Shift >> ()
        {
        });
        assertThat(resp.getBody().size(), is (1));
    }
    @Test
    public void testShiftsReturnsCorrentShift(){
        ResponseEntity<List<Shift>> resp = this.testRestTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference < List < Shift >> ()
        {
        });
        List<Shift> shifts = resp.getBody();
        assertThat(shifts.size(), CoreMatchers.is(1));
        assertThat(shifts.get(0).getShiftId(), CoreMatchers.is(1));
        assertThat(shifts.get(0).getDonarName(), CoreMatchers.is("Seward Co-Op Friendship Store"));
        assertThat(shifts.get(0).getRecipientName(), CoreMatchers.is("St.Stephens Homeless Shelter"));
        assertThat(shifts.get(0).getVolunteer1(), CoreMatchers.is("Andrew Larsen"));
        assertThat(shifts.get(0).getVolunteer2(), CoreMatchers.is("Alec Larsen"));
        assertThat(shifts.get(0).getModeOfTransit(), CoreMatchers.is("car"));
        assertThat(shifts.get(0).getFoodDonatedWeight(), CoreMatchers.is(100));
        assertThat(shifts.get(0).getFoodCompostedWeight(), CoreMatchers.is(0));
        assertThat(shifts.get(0).getFoodTypeSummary(), CoreMatchers.is("food_type_summary"));
        assertThat(shifts.get(0).getShiftLength(), CoreMatchers.is(25));
    }
}
