package com.digitalholics.healthrecordandexpertise.domain.service;


import com.digitalholics.healthrecordandexpertise.domain.model.entity.Certification;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.resource.CreateCertificationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CertificationService {
    List<Certification> getAll();
    Page<Certification> getAll(Pageable pageable);
    Certification getById(Integer certificationId);
    Certification create(CreateCertificationResource certificationResource);
    Certification update(Integer certificationId, Certification request);
    ResponseEntity<?> delete(Integer certificationId);

    Physiotherapist getPhysiotherapistById(Integer physiotherapistId);
}
