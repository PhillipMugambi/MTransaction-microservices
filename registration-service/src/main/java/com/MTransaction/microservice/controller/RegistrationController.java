package com.MTransaction.microservice.controller;
import com.MTransaction.microservice.dto.LoginRequest;
import com.MTransaction.microservice.dto.RegisterRequest;
import com.MTransaction.microservice.dto.UniversalResponse;
import com.MTransaction.microservice.models.User;
import com.MTransaction.microservice.repositories.UserRepository;
import com.MTransaction.microservice.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.MTransaction.microservice.utils.HelperUtility.checkPhoneNumber;

@RestController
@RequestMapping("/fraud/app/")
@Validated
@Slf4j

public class RegistrationController {

    private  final RegistrationService registrationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UniversalResponse universalResponse;
    public RegistrationController(RegistrationService registrationService, UserRepository userRepository, PasswordEncoder passwordEncoder, UniversalResponse universalResponse) {
        this.registrationService = registrationService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.universalResponse = universalResponse;
    }


    @PostMapping("/register")
    public UniversalResponse register(@RequestBody @Valid RegisterRequest registerRequest)
    {
        try{
            System.out.println(registerRequest);
            return registrationService.register(registerRequest);


        }
        catch (Exception ex){
            System.out.println(ex);
            return universalResponse.builder()
                    .message("BAD Request")
                    .status("0")
                    .data(registerRequest)
                    .build();
        }
    }
    @PostMapping("/authenticate")
    public UniversalResponse authenticate(@RequestBody LoginRequest loginRequest)
    {
        try{
            String checkedNumber = checkPhoneNumber(loginRequest.getMobileNumber());
            if(checkedNumber.equals("Invalid phone number")){
                return universalResponse.builder()
                        .message("Invalid phone number")
                        .status("0")
                        .data(loginRequest)
                        .build();
            }
            else
            if (userRepository.findUserByMobileNumber(checkedNumber)!=null){
            User user=userRepository.findUserByMobileNumber(checkedNumber);
            String userPin= user.getPin();
                System.out.println(loginRequest.getPin());
                String Encoded= passwordEncoder.encode(loginRequest.getPin());
            if (Encoded==userPin){
                return universalResponse.builder()
                        .message("Login successful")
                        .status("1")
                        .data(loginRequest)
                        .build();
            }
            else {

                return universalResponse.builder()
                        .message("You entered the wrong pin")
                        .status("0")
                        .data(loginRequest)
                        .build();
            }
        }

        }catch (Exception exception){
            log.info(exception.getLocalizedMessage());
          return universalResponse.builder()
                    .message("User does not exist please sign up")
                    .status("0")
                    .data(loginRequest)
                    .build();


        }

        return universalResponse.builder()
                .message("User does not exist please sign up")
                .status("0")
                .data(loginRequest)
                .build();
    }
}