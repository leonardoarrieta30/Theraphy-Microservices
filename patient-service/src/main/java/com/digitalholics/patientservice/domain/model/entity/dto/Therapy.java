package com.digitalholics.patientservice.domain.model.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Therapy {

    private String theraphyName;
    private String startAt;
    private String finishAt;
    //private Integer appointmentId;
    private Integer patientId;

}
