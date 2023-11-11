package com.digitalholics.therapyservice.resource;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheraphyResource {


    private String theraphyName;
    private String appointmentQuantity;
    private String appointmentGap;

    private String startAt;

    private String finishAt;

    private Integer physiotherapistId;
    //private Integer patientId;
}
