package com.digitalholics.patientservice.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("patientsTherapiesMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PatientMapper patientMapper() {
        return new PatientMapper();
    }

}
