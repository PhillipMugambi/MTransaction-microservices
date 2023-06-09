package com.MTransaction.microservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UniversalResponse {
    private String status;
    private String message;
    private Object data;

    //TODO: implement how to display data to the client
}
