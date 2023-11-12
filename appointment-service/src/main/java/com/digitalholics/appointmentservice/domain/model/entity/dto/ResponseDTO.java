package com.digitalholics.appointmentservice.domain.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Physiotherapist physiotherapist;
    //private Appointment appointment;
    private Theraphy2 theraphy2;
}
