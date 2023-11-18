package com.digitalholics.appointmentservice.domain.model.entity.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Physiotherapist {
    private Integer id;
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
//    private Appointment appointment;
//
//    public Appointment getAppointment(){
//        return appointment;
//    }
}
