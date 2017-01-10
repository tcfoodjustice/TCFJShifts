package com.tcfj.webservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
@Configuration
//Defined where to read in a property file
//@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    //use to access property file values
    @Autowired
    Environment env;

    @Autowired
    DataSource dataSource;
    //define the JdbcTemplate bean, which is injected into the ShiftDaoImpl
    //via the autowired tag
    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(3000);
        return jdbcTemplate;
    }

}
