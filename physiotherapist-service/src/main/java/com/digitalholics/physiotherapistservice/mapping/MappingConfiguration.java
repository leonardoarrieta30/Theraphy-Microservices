package com.digitalholics.physiotherapistservice.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public CertificationMapper certificationMapper() { return new CertificationMapper();}

    @Bean
    public JobMapper jobMapper() { return new JobMapper();}
    @Bean
    public PhysiotherapistMapper physiotherapistMapper(){
        return new PhysiotherapistMapper();
    }
}
