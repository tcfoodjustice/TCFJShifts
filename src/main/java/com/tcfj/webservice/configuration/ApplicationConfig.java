package com.tcfj.webservice.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by andrew.larsen on 10/25/2016.
 */
@Configuration
//Defined where to read in a property file
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    //use to access property file values
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() throws NamingException {
        BasicDataSource connectionPool = new BasicDataSource();
        //get the drive name from the property file. in this case
        //env.getProperty("db.driver") evaluates to com.mysql.jdbc.Driver
        //from the application.properties file under src/main/resources
        connectionPool.setDriverClassName(env.getProperty("db.driver"));
        connectionPool.setUrl(env.getProperty("db.url"));
        connectionPool.setUsername(env.getProperty("db.usrname"));
        connectionPool.setPassword(env.getProperty("db.password"));
        connectionPool.setInitialSize(Integer.parseInt(env.getProperty("db.initial.connection.pool.size")));

        return connectionPool;
    }

    //define the JdbcTemplate bean, which is injected into the ShiftDaoImpl
    //via the autowired tag
    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setQueryTimeout(3000);
        return jdbcTemplate;
    }
}
