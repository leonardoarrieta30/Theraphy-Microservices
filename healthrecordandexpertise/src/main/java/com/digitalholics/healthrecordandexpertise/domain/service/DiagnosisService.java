package com.digitalholics.healthrecordandexpertise.domain.service;



import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Patient;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.resource.CreateDiagnosisResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisService {


    Diagnosis create(CreateDiagnosisResource diagnosis);
    ResponseEntity<?> delete(Integer diagnosisId);

    List<Diagnosis> getDiagnosisByPatientId(Integer patientId);

    Patient getPatientById(Integer patientId);
    Physiotherapist getPhysiotherapistById(Integer physiotherapistId);

}
