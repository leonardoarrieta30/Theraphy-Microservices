package com.digitalholics.patientservice.domain.model.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private Integer id;
    private Boolean done;
    private String topic;
    private String diagnosis;
    private String date;
    private String hour;
    private String place;
    private Therapy therapy;

}
