package com.tcfj.webservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.naming.NamingException;

/**
 * Created by andrew.larsen on 1/1/2017.
 */
@Configuration
@Profile("test")
public class TestApplicationConfig {

    @Bean
    @Primary
    public EmbeddedDatabase dataSource(){
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:create_donors_table.sql")
                .addScript("classpath:create_recipients_table.sql")
                .addScript("classpath:create_shifts_table.sql")
                .addScript("classpath:insert_donor_data.sql")
                .addScript("classpath:insert_recipients_data.sql")
                .addScript("classpath:insert_shift_data.sql")
                .build();
        return db;
    }
    //define the JdbcTemplate bean, which is injected into the ShiftDaoImpl
    //via the autowired tag
    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() throws NamingException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setQueryTimeout(3000);
        return jdbcTemplate;
    }

}
