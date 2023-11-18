package com.digitalholics.therapyservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheraphyResource {
    private String theraphyName;
    private String startAt;
    private String finishAt;
    private Integer physiotherapistId;
    private Integer patientId;
}
