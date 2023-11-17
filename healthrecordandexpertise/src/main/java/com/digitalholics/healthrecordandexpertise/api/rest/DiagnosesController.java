package com.digitalholics.healthrecordandexpertise.api.rest;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import com.digitalholics.healthrecordandexpertise.domain.service.DiagnosisService;
import com.digitalholics.healthrecordandexpertise.mapping.DiagnosisMapper;
import com.digitalholics.healthrecordandexpertise.resource.CreateDiagnosisResource;
import com.digitalholics.healthrecordandexpertise.resource.DiagnosisResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/healthrecordandexpertise/diagnoses", produces = "application/json")
@Tag(name = "Diagnoses", description = "Read and delete diagnoses")
public class DiagnosesController {

    private final DiagnosisService diagnosisService;

    private final DiagnosisMapper mapper;

    public DiagnosesController(DiagnosisService diagnosisService, DiagnosisMapper mapper) {
        this.diagnosisService = diagnosisService;
        this.mapper = mapper;
    }


    @PostMapping
    public DiagnosisResource createDiagnosis(@RequestBody CreateDiagnosisResource resource) {
        return mapper.toResource(diagnosisService.create(resource));
    }

    @DeleteMapping("{diagnosisId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer diagnosisId) {
        return diagnosisService.delete(diagnosisId);
    }

    @GetMapping("/diagnosisByPatientId/{patientId}")
    public ResponseEntity<List<Diagnosis>> getDiagnosisByPatientId(@PathVariable("patientId") Integer patientId){
        List<Diagnosis> diagnosisList =  diagnosisService.getDiagnosisByPatientId(patientId);
        return ResponseEntity.ok(diagnosisList);
    }







}
