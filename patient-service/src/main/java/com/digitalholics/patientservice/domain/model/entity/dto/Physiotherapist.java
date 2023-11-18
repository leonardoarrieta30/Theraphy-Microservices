package com.digitalholics.patientservice.domain.model.entity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Physiotherapist {
    private String dni;
    private String specialization;
    private Integer age;
    private String location;
    private String photoUrl;
    private String birthdayDate;
    private Double rating;
    private Integer yearsExperience;
    private Double fees;
    private Integer userId;
}
