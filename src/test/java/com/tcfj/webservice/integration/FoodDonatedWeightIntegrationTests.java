package com.tcfj.webservice.integration;

import com.tcfj.webservice.Util.ShiftTestObjectCreator;
import com.tcfj.webservice.dao.ShiftDao;
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
 * Created by Matt Sloan on 1/19/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class FoodDonatedWeightIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    EmbeddedDatabase database;

    private String url = "/tcfj/v1/shifts/foodDonatedWeight";

    private Shift shift;
    private ShiftDao shiftDao;
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

    //test getTotalFoodDonatedWeight returns correct sum from correct number of shifts
    @Test
    public void testGetTotalFoodDonatedWeightReturnsCorrectSum() {

        ResponseEntity<Double> resp = this.testRestTemplate.getForEntity(url,Double.class);
        assertThat(resp.getStatusCode(), is (HttpStatus.OK));

        assertThat(resp.getBody(), is (100D));

    }
}
