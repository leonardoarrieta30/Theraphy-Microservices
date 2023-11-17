package com.digitalholics.patientservice.feignsClients;

import com.digitalholics.patientservice.domain.model.entity.dto.Diagnosis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "healthrecordandexpertise-service", url = "http://localhost:8080")
public interface DiagnosisFeignClient {


    @GetMapping("/api/v1/diagnoses/diagnosisByPatientId/{patientId}")
    List<Diagnosis> getDiagnosisByPatientId(@PathVariable("patientId") Integer patientId);


}
