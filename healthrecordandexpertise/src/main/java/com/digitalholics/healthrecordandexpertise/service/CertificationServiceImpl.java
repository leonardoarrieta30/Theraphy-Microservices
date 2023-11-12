package com.digitalholics.healthrecordandexpertise.service;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Certification;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.domain.persistence.CertificationRepository;
import com.digitalholics.healthrecordandexpertise.domain.service.CertificationService;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceValidationException;
import com.digitalholics.healthrecordandexpertise.resource.CreateCertificationResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class CertificationServiceImpl implements CertificationService {

    private static final String ENTITY = "Certification";

    private final CertificationRepository certificationRepository;

    private final Validator validator;
    private final RestTemplate restTemplate;

    @Autowired
    public CertificationServiceImpl(CertificationRepository certificationRepositoryRepository, Validator validator, RestTemplate restTemplate) {
        this.certificationRepository = certificationRepositoryRepository;
        this.validator = validator;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Certification> getAll() {
        return certificationRepository.findAll();
    }

    @Override
    public Page<Certification> getAll(Pageable pageable) {
        return certificationRepository.findAll(pageable);
    }

    @Override
    public Certification getById(Integer certificationId) {
        return certificationRepository.findById(certificationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, certificationId));
    }

    @Override
    public Certification create(CreateCertificationResource certificationResource) {
        Set<ConstraintViolation<CreateCertificationResource>> violations = validator.validate(certificationResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Certification certification = new Certification();
        certification.setYear(certificationResource.getYear());
        certification.setPhotoUrl(certificationResource.getPhotoUrl());
        certification.setTitle(certificationResource.getTitle());
        certification.setSchool(certificationResource.getSchool());
        if(this.getPhysiotherapistById(certificationResource.getPhysiotherapistId()) == null){
            throw new ResourceNotFoundException("Physiotherapist not found");
        }else{
            certification.setPhysiotherapistId(certificationResource.getPhysiotherapistId());
        }


        return certificationRepository.save(certification);
    }

    @Override
    public Certification update(Integer certificationId, Certification request) {
        Set<ConstraintViolation<Certification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return certificationRepository.findById(certificationId).map( certification ->
                        certificationRepository.save(
                                certification.withTitle(request.getTitle()).
                                        withSchool(request.getSchool()).
                                        withYear(request.getYear()).
                                        withPhotoUrl(request.getPhotoUrl())
                                        ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,certificationId));
    }

    @Override
    public ResponseEntity<?> delete(Integer certificationId) {
        return certificationRepository.findById(certificationId).map(certification -> {
           certificationRepository.delete(certification);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,certificationId));
    }

    @Override
    public Physiotherapist getPhysiotherapistById(Integer physiotherapistId){
        Physiotherapist physiotherapist  = restTemplate.getForObject("http://localhost:7008/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        return physiotherapist;
    }

}
