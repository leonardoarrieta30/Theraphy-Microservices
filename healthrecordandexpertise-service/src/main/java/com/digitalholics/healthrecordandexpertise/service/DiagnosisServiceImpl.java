package com.digitalholics.healthrecordandexpertise.service;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Patient;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.domain.persistence.DiagnosisRepository;
import com.digitalholics.healthrecordandexpertise.domain.service.DiagnosisService;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceValidationException;
import com.digitalholics.healthrecordandexpertise.resource.CreateDiagnosisResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private static final String ENTITY = "Diagnosis";

    private final DiagnosisRepository diagnosisRepository;


    private final Validator validator;

    private final RestTemplate restTemplate;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, Validator validator, RestTemplate restTemplate) {
        this.diagnosisRepository = diagnosisRepository;

        this.validator = validator;
        this.restTemplate = restTemplate;
    }




    @Override
    public Diagnosis create(CreateDiagnosisResource diagnosisResource) {
        Set<ConstraintViolation<CreateDiagnosisResource>> violations = validator.validate(diagnosisResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosis((diagnosisResource.getDiagnosis()));
        diagnosis.setDate((diagnosisResource.getDate()));

        if(this.getPatientById(diagnosisResource.getPatientId()) == null){
            throw new ResourceNotFoundException("Patient not found");
        }else{
            diagnosis.setPatientId((diagnosisResource.getPatientId()));
        }
        if(this.getPhysiotherapistById(diagnosisResource.getPhysiotherapistId()) == null){
            throw new ResourceNotFoundException("Physiotherapist not found");
        }else{
            diagnosis.setPhysiotherapistId((diagnosisResource.getPhysiotherapistId()));
        }
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public ResponseEntity<?> delete(Integer diagnosisId) {
        return diagnosisRepository.findById(diagnosisId).map(diagnosis -> {
            diagnosisRepository.delete(diagnosis);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,diagnosisId));
    }

    @Override
    public List<Diagnosis> getDiagnosisByPatientId(Integer patientId) {
        return diagnosisRepository.findDiagnosisByPatientId(patientId);
    }

    @Override
    public Patient getPatientById(Integer patientId){
        Patient patient  = restTemplate.getForObject("http://gateway-service:8080/api/v1/patients/" + patientId, Patient.class);
        return patient;
    }
    @Override
    public Physiotherapist getPhysiotherapistById(Integer physiotherapistId){
        Physiotherapist physiotherapist  = restTemplate.getForObject("http://gateway-service:8080/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        return physiotherapist;
    }

}
