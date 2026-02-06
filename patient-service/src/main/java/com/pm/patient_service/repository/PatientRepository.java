package com.pm.patient_service.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pm.patient_service.module.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
