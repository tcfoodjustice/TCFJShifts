package com.tcfj.webservice.controller;

import com.tcfj.webservice.dao.ShiftDao;
import com.tcfj.webservice.dto.FoodTotalDTO;
import com.tcfj.webservice.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MK on 10/23/2016.
 */

@RestController
//adds this string from value to request url
@RequestMapping(value = "tcfj/v1")
public class FoodDonatedWeightController {
    //The autowired tag injects a "bean" (a spring bean)
    //the bean is an instance of a ShiftDao object
    //shiftDao is an implementation of the ShiftDaoImpl
    @Autowired
    ShiftDao shiftDao;
    @RequestMapping("/shifts/foodDonatedWeight")
    public FoodTotalDTO getTotalFoodDonatedWeight() {
        //use shiftDao to return response form a sql query of total weight
        FoodTotalDTO foodTotal = new FoodTotalDTO();

        double sum = shiftDao.getTotalFoodDonatedWeight();
        foodTotal.setTotal(sum);
        return foodTotal;

    }

}
