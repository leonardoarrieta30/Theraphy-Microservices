package com.digitalholics.physiotherapistservice.domain.service;

import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;
import com.digitalholics.physiotherapistservice.resources.CreatePhysiotherapistResource;
import com.digitalholics.physiotherapistservice.resources.UpdatePhysiotherapistResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysiotherapistService {
    List<Physiotherapist> getAll();
    Page<Physiotherapist> getAll(Pageable pageable);
    Physiotherapist getById(Integer patientId);
    Physiotherapist create(CreatePhysiotherapistResource physiotherapist);
    Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request);
    ResponseEntity<?> delete(Integer physiotherapistId);
}
