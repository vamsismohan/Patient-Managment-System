package com.pm.patient_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "name is required")
    @Size(max = 100,message =  "Size should be less than 100 Characters")
    private String name;

    @NotBlank(message =  "Message is required")
    private String dateOfBirth;

    @NotBlank(message = "Address is Required")
    private String address;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Registered Date is required")
    private String registeredDate;
}
