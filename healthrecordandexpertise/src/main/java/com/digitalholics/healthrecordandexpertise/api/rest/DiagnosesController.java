package com.digitalholics.healthrecordandexpertise.api.rest;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import com.digitalholics.healthrecordandexpertise.domain.service.DiagnosisService;
import com.digitalholics.healthrecordandexpertise.mapping.DiagnosisMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/diagnoses", produces = "application/json")
@Tag(name = "Diagnoses", description = "Read and delete diagnoses")
public class DiagnosesController {

    private final DiagnosisService diagnosisService;

    private final DiagnosisMapper mapper;

    public DiagnosesController(DiagnosisService diagnosisService, DiagnosisMapper mapper) {
        this.diagnosisService = diagnosisService;
        this.mapper = mapper;
    }

    @GetMapping("/diagnosisByPatientId/{patientId}")
    public ResponseEntity<List<Diagnosis>> getDiagnosisByPatientId(@PathVariable("patientId") Integer patientId){
        List<Diagnosis> diagnosisList =  diagnosisService.getDiagnosisByPatientId(patientId);
        return ResponseEntity.ok(diagnosisList);
    }


}
