package com.MTransaction.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
        (exclude = {DataSourceAutoConfiguration.class})
public class LoginServiceApplication {

    public static void main(String[] args) {
        System.out.println("APPLICATION STARTING");
        SpringApplication.run(LoginServiceApplication.class, args);
    }
}