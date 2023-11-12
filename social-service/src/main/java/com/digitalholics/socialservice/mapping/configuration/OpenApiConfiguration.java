package com.digitalholics.socialservice.mapping.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class OpenApiConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("http://localhost:4200/").allowedMethods("*").allowedHeaders("*");
    }

    @Bean
    public OpenAPI customOpenApi(

    ) {
        return new OpenAPI()
                .info(new Info()
                        .title("IoTheraphy API")
                        .version("1.0")
                        .description("Theraphy RESTful API, made with java and springboot, applying domain-driven architecture approach to Fundamentos de Software Course.")
                        .termsOfService("https://digitalholics-3-0.github.io/Front-End-Theraphy/tos")
                        .license(new License()
                                .name("Apache 2.0 License")
                                .url("https://digitalholics-3-0.github.io/Front-End-Theraphy/license"))
                        .contact(new Contact()
                                .url("https://digitalholics-3-0.github.io/Front-End-Theraphy.studio")
                                .name("Theraphy,.studio")));

    }
}
