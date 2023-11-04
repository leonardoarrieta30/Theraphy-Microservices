package com.digitalholics.patientservice.service;

import com.digitalholics.patientservice.domain.model.entity.MedicalHistory;
import com.digitalholics.patientservice.domain.persistence.MedicalHistoryRepository;
import com.digitalholics.patientservice.domain.service.MedicalHistoryService;
import com.digitalholics.patientservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.patientservice.mapping.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private static final String ENTITY = "Medical History";

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final Validator validator;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, Validator validator) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.validator = validator;
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
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    @Override
    public MedicalHistory getByHereditaryHistory(String hereditaryHistory) {
        return medicalHistoryRepository.findByHereditaryHistory(hereditaryHistory)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY));
    }

    @Override
    public MedicalHistory getByNonPathologicalHistory(String nonPathologicalHistory) {
        return medicalHistoryRepository.findByNonPathologicalHistory(nonPathologicalHistory)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY));
    }

    @Override
    public MedicalHistory getByPathologicalHistory(String pathologicalHistory) {
        return medicalHistoryRepository.findByPathologicalHistory(pathologicalHistory)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY));
    }

    @Override
    public MedicalHistory create(MedicalHistory medicalHistory) {

        Set<ConstraintViolation<MedicalHistory>> violations = validator.validate(medicalHistory);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public MedicalHistory update(Integer medicalHistoryId, MedicalHistory request) {

        Set<ConstraintViolation<MedicalHistory>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory ->
                medicalHistoryRepository.save(
                        medicalHistory.withGender(request.getGender())
                                .withSize(request.getSize())
                                .withWeight(request.getWeight())
                                .withBirthplace(request.getBirthplace())
                                .withHereditaryHistory(request.getHereditaryHistory())
                                .withNonPathologicalHistory(request.getNonPathologicalHistory())
                                .withPathologicalHistory(request.getPathologicalHistory())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    @Override
    public ResponseEntity<?> delete(Integer medicalHistoryId) {
        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory -> {
                medicalHistoryRepository.delete(medicalHistory);
                return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }
}
