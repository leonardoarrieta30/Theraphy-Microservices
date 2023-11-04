package com.digitalholics.physiotherapistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhysiotherapistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysiotherapistServiceApplication.class, args);
    }

}
