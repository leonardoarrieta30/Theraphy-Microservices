package com.digitalholics.physiotherapistservice.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhysiotherapistResource {
    private Integer id;
    private String dni;
    private String specialization;
    private Integer age;
    private String location;
    private String photoUrl;
    private String birthdayDate;
    private Double rating;
    private Integer consultationQuantity;
    private Integer patientQuantity;
    private Integer yearsExperience;
    private Double fees;
}
