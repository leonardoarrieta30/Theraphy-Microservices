package com.digitalholics.physiotherapistservice.domain.service;

import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;
import com.digitalholics.physiotherapistservice.domain.model.dto.Patient;
import com.digitalholics.physiotherapistservice.domain.model.dto.Therapy;
import com.digitalholics.physiotherapistservice.resources.CreatePhysiotherapistResource;
import com.digitalholics.physiotherapistservice.resources.UpdatePhysiotherapistResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysiotherapistService {
    List<Physiotherapist> getAll();
    Page<Physiotherapist> getAll(Pageable pageable);
    Physiotherapist getById(Integer physiotherapistId);
    Physiotherapist create(CreatePhysiotherapistResource physiotherapist);
    Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request);
    ResponseEntity<?> delete(Integer physiotherapistId);
    Therapy saveTherapyToPatientAndPhysiotherapist(Integer physiotherapistId, Integer patientId, Therapy therapy);
    Therapy getTherapyByPhysiotherapistId(Integer physiotherapistId);
    Patient getPatientById(Integer patientId);
}
