package com.digitalholics.healthrecordandexpertise.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiagnosisResource {
    private Integer physiotherapistId;
    private Integer patientId;
    private String diagnosis;
    private String date;
}
