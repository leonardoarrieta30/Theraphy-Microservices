package com.digitalholics.healthrecordandexpertise.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiagnosisResource {
    private Integer physiotherapistId;
    private Integer patientId;
    private String diagnosis;
    private String date;
}
