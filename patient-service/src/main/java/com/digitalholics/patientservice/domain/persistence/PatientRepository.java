package com.digitalholics.patientservice.domain.persistence;

import com.digitalholics.patientservice.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findPatientByDni(String dni);

    Optional<Patient> findByUserId(Integer userId);
    Patient findPatientByUserId(Integer userId);
    //Optional<Patient> findByUserId(Integer userId);
}
