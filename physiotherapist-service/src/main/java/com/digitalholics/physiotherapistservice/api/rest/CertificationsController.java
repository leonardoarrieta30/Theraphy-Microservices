package com.digitalholics.physiotherapistservice.api.rest;

import com.digitalholics.physiotherapistservice.domain.service.CertificationService;
import com.digitalholics.physiotherapistservice.mapping.CertificationMapper;
import com.digitalholics.physiotherapistservice.resources.CertificationResource;
import com.digitalholics.physiotherapistservice.resources.CreateCertificationResource;
import com.digitalholics.physiotherapistservice.resources.UpdateCertificationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/certifications", produces = "application/json")
@Tag(name = "Certifications", description = "Create, read, update and delete certifications")
public class CertificationsController {
    private final CertificationService certificationService;

    private final CertificationMapper mapper;


    public CertificationsController(CertificationService certificationService, CertificationMapper mapper) {
        this.certificationService = certificationService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public Page<CertificationResource> getAllCertifications(Pageable pageable) {
        return mapper.modelListPage(certificationService.getAll(), pageable);
    }

    @GetMapping("certificationById/{certificationId}")
    public CertificationResource getCertificationById(@PathVariable Integer certificationId) {
        return mapper.toResource(certificationService.getById(certificationId));
    }


    @PostMapping("registration-certification")
    public ResponseEntity<CertificationResource> createCertification(@RequestBody CreateCertificationResource resource) {
        return new ResponseEntity<>(mapper.toResource(certificationService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("updateCertificationById{certificationId}")
    public CertificationResource updateCertification(@PathVariable Integer certificationId,
                                         @RequestBody UpdateCertificationResource resource) {
        return mapper.toResource(certificationService.update(certificationId, mapper.toModel(resource)));
    }

    @DeleteMapping("deleteCertificationById/{certificationId}")
    public ResponseEntity<?> deleteCertification(@PathVariable Integer certificationId) {
        return certificationService.delete(certificationId);
    }

}
