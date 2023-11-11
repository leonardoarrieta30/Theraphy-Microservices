package com.digitalholics.patientservice.domain.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {
    private String diagnosis;
    private Integer physiotherapistId;
    private Integer patientId;
    private String date;
}
