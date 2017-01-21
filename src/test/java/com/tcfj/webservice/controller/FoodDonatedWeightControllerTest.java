package com.tcfj.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcfj.webservice.dao.ShiftDao;
import com.tcfj.webservice.model.Shift;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Mon 1/18/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FoodDonatedWeightController.class)
public class FoodDonatedWeightControllerTest {
    //this is used to send fake http calls to the controller method
    @Autowired
    private MockMvc mvc;
    //the @MockBean tag will auto mock the controller dependencies
    @MockBean
    private ShiftDao shiftDao;

    private Double total;
    private String url = "/tcfj/v1/shifts/foodDonatedWeight";
    private List<Shift> shifts;

    @Before
    public void setup(){
        shifts = new ArrayList<>();
        total = 1000D;
    }
    @Test
    public void testUrlNotFound() throws Exception {
        this.mvc.perform(get("/notfound")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testGetTotalFoodDonatedWeight() throws Exception {
        given(shiftDao.getTotalFoodDonatedWeight()).willReturn(total);
        this.mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.total", is(total)));

    }


}
