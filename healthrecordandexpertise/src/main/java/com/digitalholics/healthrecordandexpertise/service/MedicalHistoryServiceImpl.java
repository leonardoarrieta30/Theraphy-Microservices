package com.digitalholics.healthrecordandexpertise.service;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Patient;
import com.digitalholics.healthrecordandexpertise.domain.persistence.MedicalHistoryRepository;
import com.digitalholics.healthrecordandexpertise.domain.service.MedicalHistoryService;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceValidationException;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private static final String ENTITY = "MedicalHistory";

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final Validator validator;
    private final RestTemplate restTemplate;

    @Autowired
    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, Validator validator, RestTemplate restTemplate) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.validator = validator;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MedicalHistory> getAll() {
        return medicalHistoryRepository.findAll();
    }

    @Override
    public Page<MedicalHistory> getAll(Pageable pageable) {
        return medicalHistoryRepository.findAll(pageable);
    }

    @Override
    public MedicalHistory getById(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    //create medical history
    @Override
    public MedicalHistory create(CreateMedicalHistoryResource medicalHistoryResource) {
        Set<ConstraintViolation<CreateMedicalHistoryResource>> violations = validator.validate(medicalHistoryResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setGender(medicalHistoryResource.getGender());
        medicalHistory.setSize(medicalHistoryResource.getSize());
        medicalHistory.setWeight(medicalHistoryResource.getWeight());
        medicalHistory.setBirthplace(medicalHistoryResource.getBirthplace());
        medicalHistory.setHereditaryHistory(medicalHistoryResource.getHereditaryHistory());
        medicalHistory.setNonPathologicalHistory(medicalHistoryResource.getNonPathologicalHistory());
        medicalHistory.setPathologicalHistory(medicalHistoryResource.getPathologicalHistory());
        if(this.getPatientById(medicalHistoryResource.getPatientId()) == null){
            throw new ResourceNotFoundException("Patient Not found");
        }else{
            medicalHistory.setPatientId(medicalHistoryResource.getPatientId());
        }
        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public MedicalHistory update(Integer medicalHistoryId, UpdateMedicalHistoryResource request) {
        MedicalHistory medicalHistory = getById(medicalHistoryId);

        if(medicalHistory == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Medical History with ID:"+ medicalHistoryId);

        if (request.getGender() != null) {
            medicalHistory.setGender(request.getGender());
        }
        if (request.getSize() != null) {
            medicalHistory.setSize(request.getSize());
        }
        if (request.getWeight() != null) {
            medicalHistory.setWeight(request.getWeight());
        }
        if (request.getBirthplace() != null) {
            medicalHistory.setBirthplace(request.getBirthplace());
        }
        if (request.getHereditaryHistory() != null) {
            medicalHistory.setHereditaryHistory(request.getHereditaryHistory());
        }
        if (request.getNonPathologicalHistory() != null) {
            medicalHistory.setNonPathologicalHistory(request.getNonPathologicalHistory());
        }
        if (request.getPathologicalHistory() != null) {
            medicalHistory.setPathologicalHistory(request.getPathologicalHistory());
        }

        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public ResponseEntity<?> delete(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory -> {
            medicalHistoryRepository.delete(medicalHistory);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,medicalHistoryId));
    }


    @Override
    public Patient getPatientById(Integer patientId){
        Patient patient  = restTemplate.getForObject("http://gateway-service:8080/api/v1/patients/" + patientId, Patient.class);
        return patient;
    }








}
