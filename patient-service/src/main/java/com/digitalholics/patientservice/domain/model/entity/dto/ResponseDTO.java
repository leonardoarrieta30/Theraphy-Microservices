package com.digitalholics.patientservice.domain.model.entity.dto;

import com.digitalholics.patientservice.domain.model.entity.Patient;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Appointment appointment;
    private Theraphy theraphy;
    private Patient patient;
}
