package com.tcfj.webservice.dao.impl;

import com.tcfj.webservice.dao.ShiftDao;
import com.tcfj.webservice.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
@Repository
public class ShiftDaoImpl implements ShiftDao {

    //sql query used by DAO method to return all rows from the shift table.
    //The query could use "select *" rather than each individual row,
    //but it is best practice to explicitly return the rows you want.
    //For performance reasons and to be explicit with what you need
    private String getAllShifts = "select shift_id,donar_name,recipient_name,rescue_date,volunteer_1,volunteer_2," +
            "volunteer_3,pick_up_time,mode_of_transit,food_donated_weight,food_composted_weight,shift_length,food_type_summary," +
            "comments,supplies_stocked,submit_time from Shifts";

    private final static String insertShiftDetails = "INSERT INTO Shifts" + "( donar_name, recipient_name)" +
            "VALUES (?,?)";



    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Method to use jdbcTemplate to retrieve rows from sql query
     * @return
     */
    @Override
    public List<Shift> getAllShifts() {
        //Calls the query method of the jdbctemplate class.  We provide the
        //sql query, as well as a row mapper.  The sql query is executed on
        //the mysql database (via the mysql driver defined in the application.properties,
        //configured in the ApplicationConfig.java, and dependency is defined in the
        //build.gradle file
        List<Shift> shifts = jdbcTemplate.query(getAllShifts, new ShiftRowMapper());
        return shifts;
    }
    /**
     * Method to use jdbc template to insert shift
     */
    @Override
    public int insertShift(Shift shift) {
        Object[] param = {shift.getDonarName(), shift.getRecipientName()};
        int[] types = {Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(insertShiftDetails, param, types);

    }

    /**
     * Row mapper class to turn each row returned from MYSQL into a java object
     */
    public static final class ShiftRowMapper implements RowMapper {

        @Override
        public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
            Shift shift = new Shift();

            shift.setShiftId(rs.getInt("shift_id"));
            shift.setDonarName(rs.getString("donar_name"));
            shift.setRecipientName(rs.getString("recipient_name"));
            shift.setRescueDate(rs.getDate("rescue_date").toString());
            shift.setVolunteer1(rs.getString("volunteer_1"));
            shift.setVolunteer2(rs.getString("volunteer_2"));
            shift.setVolunteer3(rs.getString("volunteer_3"));
            shift.setPickUpTime(rs.getString("pick_up_time"));
            shift.setModeOfTransit(rs.getString("mode_of_transit"));
            shift.setFoodDonatedWeight(rs.getInt("food_donated_weight"));
            shift.setFoodCompostedWeight(rs.getInt("food_composted_weight"));
            shift.setShiftLength(rs.getInt("shift_length"));
            shift.setFoodTypeSummary("food_type_summary");
            shift.setComments(rs.getString("comments"));
            shift.setSuppliesStocked(rs.getBoolean("supplies_stocked"));
            shift.setSubmitTime(rs.getString("submit_time"));
            return shift;
        }
    }
}
