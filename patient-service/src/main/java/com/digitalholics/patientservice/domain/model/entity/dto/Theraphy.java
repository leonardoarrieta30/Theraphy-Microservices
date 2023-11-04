package com.digitalholics.patientservice.domain.model.entity.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theraphy {

    private Integer id;
    private String theraphyName;
    private String appointmentQuantity;
    private String appointmentGap;
    private String startAt;
    private String finishAt;

}
