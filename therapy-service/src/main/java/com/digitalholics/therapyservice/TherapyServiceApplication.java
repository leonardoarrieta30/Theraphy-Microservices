package com.digitalholics.therapyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class TherapyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TherapyServiceApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
