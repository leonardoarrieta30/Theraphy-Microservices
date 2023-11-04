package com.digitalholics.patientservice.api.rest;

import com.digitalholics.patientservice.domain.service.MedicalHistoryService;
import com.digitalholics.patientservice.mapping.MedicalHistoryMapper;
import com.digitalholics.patientservice.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.patientservice.resource.MedicalHistory.MedicalHistoryResource;
import com.digitalholics.patientservice.resource.MedicalHistory.UpdateMedicalHistoryResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/medical_histories", produces = "application/json")
@Tag(name = "Medical Histories", description = "Create, read, update and delete medical histories")
public class MedicalHistoriesController {
    private final MedicalHistoryService medicalHistoryService;

    private final MedicalHistoryMapper mapper;

    public MedicalHistoriesController(MedicalHistoryService medicalHistoryService, MedicalHistoryMapper mapper) {
        this.medicalHistoryService = medicalHistoryService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public Page<MedicalHistoryResource> getAllMedicalHistories(Pageable pageable) {
        return mapper.modelListPage(medicalHistoryService.getAll(), pageable);
    }

    @GetMapping("medicalHistoryById/{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryById(@PathVariable Integer medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getById(medicalHistoryId));
    }

    @PostMapping("registration-medical-history")
    public ResponseEntity<MedicalHistoryResource> createMedicalHistory(@RequestBody CreateMedicalHistoryResource resource) {
        return new ResponseEntity<>(mapper.toResource(medicalHistoryService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("updateMedicalHistoryById/{medicalHistoryId}")
    public MedicalHistoryResource updateMedicalHistory(@PathVariable Integer medicalHistoryId,
        @RequestBody UpdateMedicalHistoryResource resource) {
        return mapper.toResource(medicalHistoryService.update(medicalHistoryId, mapper.toModel(resource)));
    }

    @DeleteMapping("deleteMedicalHistoryById/{medicalHistoryId}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable Integer medicalHistoryId) {
        return  medicalHistoryService.delete(medicalHistoryId);
    }
}
