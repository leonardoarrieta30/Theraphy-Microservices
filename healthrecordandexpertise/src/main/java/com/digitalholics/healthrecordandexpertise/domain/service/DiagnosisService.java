package com.digitalholics.healthrecordandexpertise.domain.service;



import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisService {


    Diagnosis create(Diagnosis diagnosis);
    ResponseEntity<?> delete(Integer patientId);
}
