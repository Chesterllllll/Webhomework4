package com.chester.webhomework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Webhomework4Application {

//    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Webhomework4Application.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Webhomework4Application.class, args);
    }

}
