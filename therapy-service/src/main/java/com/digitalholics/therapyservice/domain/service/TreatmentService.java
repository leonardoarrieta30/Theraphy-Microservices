package com.digitalholics.therapyservice.domain.service;

import com.digitalholics.therapyservice.domain.model.entity.Treatment;
import com.digitalholics.therapyservice.resource.Treatment.CreateTreatmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TreatmentService {

    List<Treatment> getAll();

    Page<Treatment> getAll(Pageable pageable);

    Treatment getById(Integer treatmentId);

    Treatment create(CreateTreatmentResource treatmentResource);

    //List<Treatment> getTreatmentByTheraphyId(Integer theraphyId);

    Treatment update(Integer treatmentId, Treatment request);

    ResponseEntity<?> delete(Integer treatmentId);

}
