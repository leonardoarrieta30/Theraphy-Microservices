package com.digitalholics.therapyservice.resource;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTheraphyResource {

    private Integer id;
    private String theraphyName;
    private String appointmentQuantity;
    private String appointmentGap;
    private String startAt;
    private String finishAt;
}
