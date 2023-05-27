package com.MTransaction.microservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //General info
    @NotEmpty(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String firstName;
    @NotNull(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String middleName;
    @NotNull(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String lastName;
    @NotNull(message = "This field 'model' should not be empty")
    private String dateOfBirth;
    @NotNull(message = "This field 'model' should not be empty")
    private String gender;
    @NotNull(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String occupation;
    @NotNull(message = "This field 'model' should not be empty")
    private String pin;

    //Contact Information
    @NotNull(message = "This field 'model' should not be empty")
    @Column(unique = true)
    private String mobileNumber;
    @Email
    @NotNull(message = "This field 'model' should not be empty")
    private String email;
    @NotNull(message = "This field 'model' should not be empty")
    private String permanentAddress;
    @NotNull(message = "This field 'model' should not be empty")
    private String currentAddress;
    @NotNull(message = "This field 'model' should not be empty")
    private String pinCode;
    @NotNull(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String city;
    @NotNull(message = "This field 'model' should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetical characters are allowed")
    private String state;
    @NotNull(message = "This field 'model' should not be empty")
    private String country;
    @CreationTimestamp
    private LocalDateTime created_at;

}
