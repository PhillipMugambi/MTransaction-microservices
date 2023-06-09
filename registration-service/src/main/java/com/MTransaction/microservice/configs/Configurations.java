package com.MTransaction.microservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class Configurations {
    @Bean
    public PasswordEncoder passwordEncoderD() {
        return new BCryptPasswordEncoder();
    }
}
