package com.tcfj.webservice.controller;

import com.tcfj.webservice.dao.ShiftDao;
import com.tcfj.webservice.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
@RestController
@RequestMapping(value = "tcfj/v1")
public class ShiftController {
    //The autowired tag injects a "bean" (a spring bean)
    //the bean is an instance of a ShiftDao object
    //shiftDao is an implementation of the ShiftDaoImpl
    @Autowired
    ShiftDao shiftDao;
    //The request mapping dictates where the method is exposed.
    //in this can, when deployed locally, you would hit this
    //method at: localhost:8080/shifts
    @RequestMapping("/shifts")
    public List<Shift> getAllShifts(){
        //this calls an returns the reponse fromt he getAllShifts method
        return shiftDao.getAllShifts();
    }
}
