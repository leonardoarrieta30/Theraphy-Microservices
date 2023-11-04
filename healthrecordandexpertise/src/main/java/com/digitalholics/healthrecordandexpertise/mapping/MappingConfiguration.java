package com.digitalholics.healthrecordandexpertise.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("educationsMappingConfiguration") //del bounded context
public class MappingConfiguration {



    @Bean
    public DiagnosisMapper diagnosisMapper() { return new DiagnosisMapper();}

    @Bean
    public MedicalHistoryMapper medicalHistoryMapper() { return new MedicalHistoryMapper();}
}
