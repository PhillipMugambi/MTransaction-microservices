package com.MTransaction.microservice.service;
import com.MTransaction.microservice.dto.RegistrationResponse;
import com.MTransaction.microservice.models.Account;
import com.MTransaction.microservice.models.User;
import com.MTransaction.microservice.repositories.UserRepository;
import com.MTransaction.microservice.utils.HelperUtility;
import com.MTransaction.microservice.dto.RegisterRequest;
import com.MTransaction.microservice.dto.UniversalResponse;
import com.MTransaction.microservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationResponse registrationResponse;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    public UniversalResponse response;

    public UniversalResponse register(RegisterRequest request) {

        try {
            if (userRepository.findUserByEmail(request.getEmail()) != null) {
                return UniversalResponse.builder()
                        .message("User with this email already exist")
                        .status("1")
                        .build();
            }
            else if (userRepository.findUserByMobileNumber(request.getMobileNumber()) == null) {

                String checkedNumber = HelperUtility.checkPhoneNumber(request.getMobileNumber());
                if(checkedNumber.equals("Invalid phone number")){
                    return UniversalResponse.builder()
                            .message("Invalid phone number")
                            .status("1")
                            .data(request)
                            .build();
                }
                else {
                    var user = User.builder()
                            .firstName(request.getFirstName())
                            .middleName(request.getMiddleName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .mobileNumber(checkedNumber)
                            .pin(passwordEncoder.encode(request.getPin()))
                            .gender(request.getGender())
                            .dateOfBirth(request.getDateOfBirth())
                            .occupation(request.getOccupation())
                            .country(request.getCountry())
                            .city(request.getCity())
                            .state(request.getState())
                            .currentAddress(request.getCurrentAddress())
                            .permanentAddress(request.getPermanentAddress())
                            .pinCode(request.getPinCode()).build();

                    if (accountRepository.findByAccountNumber(checkedNumber)==null){
                        try {
                            userRepository.save(user);

                            var account = Account.builder()
                                    .accountNumber(user.getMobileNumber())
                                    .openedBy(user.getMobileNumber())
                                    .accountBalance(0.00)
                                    .balanceBefore(0.00)
                                    .build();
                            accountRepository.save(account);
                            registrationResponse.setPhoneNumber(checkedNumber);
                            log.info("User registered successfully");
                            return UniversalResponse.builder()
                                    .message("User registered successfully")
                                    .status("1")
                                    .data(registrationResponse)
                                    .build();
                        }
                        catch (Exception ex) {
                            System.out.println("Registration failed{}" + ex);
                            return UniversalResponse.builder()
                                    .message("Registration failed")
                                    .status("1")
                                    .build();
                        }
                    }
                    else {
                        return UniversalResponse.builder()
                                .message("User already registered, please login")
                                .status("1")
                                .build();
                    }
                }
            }
            else {
                return UniversalResponse.builder()
                        .message("User already registered, please login")
                        .status("1")
                        .build();
            }
        }
        catch (Exception ex) {
            System.out.println("Saving User Failed{}" + ex);
            //TODO: Save method to be a boolean,if fails to save send error
        }
        return response;
    }
}