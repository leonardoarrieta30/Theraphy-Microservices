package com.digitalholics.socialservice.feignClients;

import com.digitalholics.socialservice.domain.model.entity.dto.Physiotherapist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "physiotherapist-service", url = "http://gateway-service:8080")
public interface PhysiotherapistFeignClient {

    //@PatchMapping("/api/v1/physiotherapists/patch/{physiotherapistId}")
    @RequestMapping(value = "/api/v1/physiotherapists/patch/{physiotherapistId}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Physiotherapist save(@PathVariable("physiotherapistId") Integer physiotherapistId, @RequestBody Physiotherapist physiotherapist);
}
