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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by andrew.larsen on 1/2/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShiftController.class)
public class ShiftControllerTest {
    //this is used to send fake http calls to the controller method
    @Autowired
    private MockMvc mvc;
    //the @MockBean tag will auto mock the controller dependencies
    @MockBean
    private ShiftDao shiftDao;

    private String url = "/tcfj/v1/shifts";
    private List<Shift> shifts;

    @Before
    public void setup(){
        shifts = new ArrayList<>();
    }
    @Test
    public void testUrlNotFound() throws Exception {
        this.mvc.perform(get("/notfound")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testShiftsIsOk() throws Exception {
        shifts.add(new Shift());
        given(shiftDao.getAllShifts()).willReturn(shifts);
        this.mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void testShiftIsReturned() throws Exception {
        Shift shift = new Shift();
        shift.setComments("THIS IS A TEST");
        shifts.add(shift);
        given(shiftDao.getAllShifts()).willReturn(shifts);
        this.mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].comments", is(shift.getComments())));

    }
    @Test
    public void testPostShiftReturns201() throws Exception {
        Shift shift = new Shift();
        given(shiftDao.insertShift(Mockito.anyObject())).willReturn(1);
        this.mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(shift)))
                .andExpect(status().isCreated());
    }
    @Test
    public void testPostShiftReturns500() throws Exception {
     /*   Shift shift = new Shift();
        given(shiftDao.insertShift(Mockito.anyObject())).willReturn(0);
        this.mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(shift)))
                .andExpect(status().is5xxServerError());
                */
    }
}