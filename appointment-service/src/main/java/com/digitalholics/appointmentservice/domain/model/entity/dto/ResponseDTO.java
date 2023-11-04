package com.digitalholics.appointmentservice.domain.model.entity.dto;

import com.digitalholics.appointmentservice.domain.model.entity.Appointment;
import com.digitalholics.appointmentservice.domain.model.entity.Treatment;
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
