package com.digitalholics.physiotherapistservice.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public PhysiotherapistMapper physiotherapistMapper(){
        return new PhysiotherapistMapper();
    }
}
