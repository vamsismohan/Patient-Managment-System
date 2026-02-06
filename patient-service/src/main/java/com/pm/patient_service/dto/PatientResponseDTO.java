package com.pm.patient_service.dto;

import lombok.Data;

@Data
public class PatientResponseDTO {

    private String id;
    private String name;
    private String dateOfBirth;
    private String address;
    private String email;
    
}
