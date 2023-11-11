package com.digitalholics.healthrecordandexpertise.domain.service;


import com.digitalholics.healthrecordandexpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Patient;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicalHistoryService {

    List<MedicalHistory> getAll();
    Page<MedicalHistory> getAll(Pageable pageable);
    MedicalHistory getById(Integer medicalHistoryId);
    MedicalHistory create(CreateMedicalHistoryResource medicalHistory);
    MedicalHistory update(Integer medicalHistoryId, UpdateMedicalHistoryResource request);
    ResponseEntity<?> delete(Integer medicalHistoryId);
    Patient getPatientById(Integer patientId);
}
