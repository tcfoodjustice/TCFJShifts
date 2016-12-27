package com.tcfj.webservice.dao;

import com.tcfj.webservice.model.Shift;

import java.util.List;

/**
 * Created by andrew.larsen on 10/25/2016.
 *
 * Interface class to define all data access methods
 */
public interface ShiftDao {

    List<Shift> getAllShifts();
}
