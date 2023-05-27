package com.MTransaction.microservice.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegistrationResponse {
    private String phoneNumber;
}
