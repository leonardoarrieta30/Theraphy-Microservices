package com.digitalholics.appointmentservice.feignClients;

import com.digitalholics.appointmentservice.domain.model.entity.dto.Theraphy2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "therapy-service", url = "http://localhost:8080")
//@RequestMapping("/api/v1/theraphies")
public interface TherapyFeignClient {

    @PostMapping("/api/v1/theraphies/create-theraphy")
    Theraphy2 save(@RequestBody Theraphy2 theraphy2);

//
//    @GetMapping("/api/v1/theraphies/byPhysiotherapist/{physiotherapistId}")
//    Therapy getTherapy(@PathVariable("physiotherapistId") Integer physiotherapistId);




}
