package com.example.mymiaosha4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Mymiaosha4Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Mymiaosha4Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Mymiaosha4Application.class);
    }
}
