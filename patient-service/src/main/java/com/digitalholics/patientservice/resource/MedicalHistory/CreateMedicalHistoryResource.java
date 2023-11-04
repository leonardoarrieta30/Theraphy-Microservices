package com.digitalholics.patientservice.resource.MedicalHistory;

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
public class CreateMedicalHistoryResource {

    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    private double size;

    @NotNull
    private double weight;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String birthplace;

    @Size(max = 300)
    private String hereditaryHistory;

    @Size(max = 300)
    private String nonPathologicalHistory;

    @Size(max = 300)
    private String pathologicalHistory;
}
