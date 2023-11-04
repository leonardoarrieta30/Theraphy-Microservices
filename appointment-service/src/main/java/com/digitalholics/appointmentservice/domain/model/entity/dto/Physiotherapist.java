package com.digitalholics.appointmentservice.domain.model.entity.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Physiotherapist {
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
//    private Appointment appointment;
//
//    public Appointment getAppointment(){
//        return appointment;
//    }
}
