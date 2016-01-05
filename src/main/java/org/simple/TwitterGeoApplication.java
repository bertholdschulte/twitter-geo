package org.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TwitterGeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterGeoApplication.class, args);
    }
    
 
}
