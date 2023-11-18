package com.digitalholics.physiotherapistservice.domain.model.dto;

import jakarta.persistence.JoinColumn;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Therapy {
    private String theraphyName;
    private String startAt;
    private String finishAt;
    //private Integer appointmentId;
    private Integer physiotherapistId;
    private Integer patientId;
}
