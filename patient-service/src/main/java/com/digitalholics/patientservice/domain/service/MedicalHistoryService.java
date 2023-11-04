package com.digitalholics.patientservice.domain.service;

import com.digitalholics.patientservice.domain.model.entity.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicalHistoryService {

    List<MedicalHistory> getAll();
    Page<MedicalHistory> getAll(Pageable pageable);
    MedicalHistory getById(Integer medicalHistoryId);
    MedicalHistory getByHereditaryHistory(String hereditaryHistory);
    MedicalHistory getByNonPathologicalHistory(String nonPathologicalHistory);
    MedicalHistory getByPathologicalHistory(String pathologicalHistory);
    MedicalHistory create(MedicalHistory medicalHistory);
    MedicalHistory update(Integer medicalHistoryId, MedicalHistory request);
    ResponseEntity<?> delete(Integer medicalHistoryId);
}
