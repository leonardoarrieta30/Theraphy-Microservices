package com.digitalholics.physiotherapistservice.feignClients;

import com.digitalholics.physiotherapistservice.domain.model.dto.Therapy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "patient-service", url = "http://localhost:7010")
public interface PatientFeignClient {
    @PostMapping("/api/v1/theraphies/create-theraphy")
    Therapy save(@RequestBody Therapy therapy);
}
