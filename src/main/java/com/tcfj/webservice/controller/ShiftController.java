package com.tcfj.webservice.controller;

import com.tcfj.webservice.dao.ShiftDao;
import com.tcfj.webservice.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //this calls an returns the response from the getAllShifts method
        return shiftDao.getAllShifts();
    }

    @PostMapping("/shifts")
    //This defines that a successful response will return a Http status of created (201)
    @ResponseStatus(HttpStatus.CREATED)
    //The @RequestBody is what contains the HTTP request body (in this case the shift to be inserted
    public ResponseEntity insertShift(@RequestBody Shift shift){
        //an insert method will need to be created in the ShiftDao and called here.  It's common practice
        //to return the created shift after
        int result = shiftDao.insertShift(shift);
        if(result < 1) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
