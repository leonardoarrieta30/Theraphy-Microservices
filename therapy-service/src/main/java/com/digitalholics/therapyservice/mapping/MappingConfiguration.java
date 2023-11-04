package com.digitalholics.therapyservice.mapping;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("theraphiesTreatmentsMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public TheraphyMapper theraphyMapper() { return new TheraphyMapper();}
    @Bean
    public TreatmentMapper treatmentMapper() { return new TreatmentMapper();}
}
