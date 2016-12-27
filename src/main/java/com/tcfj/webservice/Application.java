package com.tcfj.webservice; /**
 * Created by MK on 10/23/2016.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * This is the main method that controls the application
     * The SpringApplication.run spins up an internal Web Server container
     * which props up the web service, exposing it over http
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
