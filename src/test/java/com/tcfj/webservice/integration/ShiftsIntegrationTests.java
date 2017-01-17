package com.tcfj.webservice.integration;

import com.tcfj.webservice.Util.ShiftTestObjectCreator;
import com.tcfj.webservice.model.Shift;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.tcfj.webservice.Util.ShiftTestObjectCreator.createShift;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by andrew.larsen on 1/1/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class ShiftsIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    EmbeddedDatabase database;

    private String url = "/tcfj/v1/shifts";
    private Shift shift;
    @Before
    public void setup(){
        shift = new Shift();
        shift = ShiftTestObjectCreator.createShift();
        database.shutdown();
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:create_donors_table.sql")
                .addScript("classpath:create_recipients_table.sql")
                .addScript("classpath:create_shifts_table.sql")
                .addScript("classpath:insert_donor_data.sql")
                .addScript("classpath:insert_recipients_data.sql")
                .addScript("classpath:insert_shift_data.sql")
                .build();

    }
    @After
    public void tearDown(){
        database.shutdown();
    }
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
        assertThat(shifts.get(0).getFoodTypeSummary(), CoreMatchers.is("good food"));
        assertThat(shifts.get(0).getShiftLength(), CoreMatchers.is(25));
    }
    @Test
    public void testPostShiftReturns201(){
        ResponseEntity<String> resp = this.testRestTemplate.postForEntity(url, shift, String.class);
        assertThat(resp.getStatusCode(), is (HttpStatus.CREATED));
    }
    @Test
    public void testValueIsInserted(){
        ResponseEntity<List<Shift>> resp = this.testRestTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference < List < Shift >> ()
        {
        });
        int currentCount = resp.getBody().size();
        ResponseEntity<String> postResp = this.testRestTemplate.postForEntity(url, shift, String.class);
        ResponseEntity<List<Shift>> newResp = this.testRestTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference < List < Shift >> ()
        {
        });
        int newCount = newResp.getBody().size();
        assertEquals(currentCount + 1, newCount);

    }
    @Test
    public void testBadValueReturns500(){
        shift.setRescueDate("THISISABADTIME");
        ResponseEntity<String> postResp = this.testRestTemplate.postForEntity(url, shift, String.class);

        assertEquals(postResp.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @Test
    public void testIsValueThere(){
        ResponseEntity<String> postResp = this.testRestTemplate.postForEntity(url, shift, String.class);
        ResponseEntity<List<Shift>> newResp = this.testRestTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference < List < Shift >> ()
        {
        });
        Shift insertedShift = null;
        List<Shift> shifts = newResp.getBody();
        for (int i = 0; i < shifts.size(); i++)
        {
             if (shifts.get(i).getShiftId() == 2 ) {
                 insertedShift = shifts.get(i);
             }
        }
        assertNotNull(insertedShift);
        assertEquals(insertedShift.getDonarName(), shift.getDonarName());
        assertEquals(insertedShift.getComments(), shift.getComments());
        assertEquals(insertedShift.getFoodCompostedWeight(), shift.getFoodCompostedWeight());
        assertEquals(insertedShift.getFoodDonatedWeight(), shift.getFoodDonatedWeight());
        assertEquals(insertedShift.getFoodTypeSummary(), shift.getFoodTypeSummary());
        assertEquals(insertedShift.getModeOfTransit(), shift.getModeOfTransit());
        assertEquals(insertedShift.getPickUpTime(), shift.getPickUpTime());
        assertEquals(insertedShift.getRecipientName(), shift.getRecipientName());
        assertEquals(insertedShift.getRescueDate(), shift.getRescueDate());
        assertEquals(insertedShift.getShiftLength(), shift.getShiftLength());
        assertNotNull(insertedShift.getSubmitTime());
        assertEquals(insertedShift.getVolunteer1(), shift.getVolunteer1());
        assertEquals(insertedShift.getVolunteer2(), shift.getVolunteer2());
        assertEquals(insertedShift.getVolunteer3(), shift.getVolunteer3());














    }

}
