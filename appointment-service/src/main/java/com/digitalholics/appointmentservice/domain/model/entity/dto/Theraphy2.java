package com.digitalholics.appointmentservice.domain.model.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Theraphy2 {
    private String theraphyName;
    private String appointmentQuantity;
    private String appointmentGap;
    private String startAt;
    private String finishAt;
    private Integer patientId;
    private Integer physiotherapistId;



}
