package com.tcfj.webservice.dao;

import com.tcfj.webservice.dao.impl.ShiftDaoImpl;
import com.tcfj.webservice.model.Shift;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by andrew.larsen on 1/1/2017.
 */
public class ShiftDaoTests {
    private EmbeddedDatabase db;
    private ShiftDao shiftDao;

    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:create_donors_table.sql")
                .addScript("classpath:create_recipients_table.sql")
                .addScript("classpath:create_shifts_table.sql")
                .addScript("classpath:insert_donor_data.sql")
                .addScript("classpath:insert_recipients_data.sql")
                .addScript("classpath:insert_shift_data.sql")
                .build();
        shiftDao = new ShiftDaoImpl(new JdbcTemplate(db));
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void testGetAllShifts(){
        List<Shift> shifts = shiftDao.getAllShifts();

        assertThat(shifts.size(), is(1));
        assertThat(shifts.get(0).getShiftId(), is(1));
        assertThat(shifts.get(0).getDonarName(), is("Seward Co-Op Friendship Store"));
        assertThat(shifts.get(0).getRecipientName(), is("St.Stephens Homeless Shelter"));
        assertThat(shifts.get(0).getVolunteer1(), is("Andrew Larsen"));
        assertThat(shifts.get(0).getVolunteer2(), is("Alec Larsen"));
        assertThat(shifts.get(0).getModeOfTransit(), is("car"));
        assertThat(shifts.get(0).getFoodDonatedWeight(), is(100));
        assertThat(shifts.get(0).getFoodCompostedWeight(), is(0));
        assertThat(shifts.get(0).getFoodTypeSummary(), is("food_type_summary"));
        assertThat(shifts.get(0).getShiftLength(), is(25));

    }
}
