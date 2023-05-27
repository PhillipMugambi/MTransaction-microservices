package com.MTransaction.microservice.controllers;

import com.MTransaction.microservice.dto.LoginRequest;
import com.MTransaction.microservice.dto.UniversalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fraud/app")
@Validated
@Slf4j
public class Login {
    private final WebClient.Builder webClientBuilder;
    UniversalResponse response;
        @PostMapping("/login")
        public Mono<ResponseEntity<UniversalResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
            return webClientBuilder.build().post()
                    .uri("http://registration-service/fraud/app/authenticate")
                    .body(BodyInserters.fromValue(loginRequest))
                    .retrieve()
                    .bodyToMono(UniversalResponse.class)
                    .flatMap(response -> Mono.just(ResponseEntity.ok(response)));
        }}