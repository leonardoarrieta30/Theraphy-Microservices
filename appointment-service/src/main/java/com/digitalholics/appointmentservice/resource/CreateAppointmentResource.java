package com.digitalholics.appointmentservice.resource;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentResource {

    private Integer id;
    private Boolean done;

    private String topic;
    private String diagnosis;

    private String date;

    private String hour;

    private String place;

    private Integer theraphyId;

}
