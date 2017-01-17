package com.tcfj.webservice.Util;

import com.tcfj.webservice.model.Shift;

import java.time.LocalDateTime;

/**
 * Created by MK on 1/16/2017.
 */
public class ShiftTestObjectCreator {
    public static Shift createShift(){
        Shift shift = new Shift();
        shift.setComments("comments");
        shift.setDonarName("2chains");
        shift.setFoodCompostedWeight(100);
        shift.setFoodTypeSummary("Foul");
        shift.setModeOfTransit("Dragon");
        shift.setPickUpTime("2017-01-16 00:00:00.0");
        shift.setFoodDonatedWeight(500);
        shift.setRecipientName("2pac");
        shift.setRescueDate("2017-01-16");
        shift.setShiftLength(60);
        shift.setSuppliesStocked(true);
        shift.setVolunteer1("Ricky");
        shift.setVolunteer2("Julian");
        shift.setVolunteer3("Bubbles");
        return shift;
    }
}
