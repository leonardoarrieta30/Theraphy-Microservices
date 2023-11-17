package com.digitalholics.patientservice.feignsClients;

import com.digitalholics.patientservice.domain.model.entity.dto.Therapy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "therapy-service", url = "http://localhost:8080")
public interface TherapyFeignClient {
    @PostMapping("/api/v1/theraphies/create-theraphy")
    Therapy save(@RequestBody Therapy therapy);

//    @GetMapping("/api/v1/theraphies/byPhysiotherapist/{physiotherapistId}")
//    Therapy getTherapy(@PathVariable("physiotherapistId") Integer physiotherapistId);
}
