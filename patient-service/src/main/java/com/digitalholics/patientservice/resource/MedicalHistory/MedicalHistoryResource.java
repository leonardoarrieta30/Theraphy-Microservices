package com.digitalholics.patientservice.resource.MedicalHistory;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryResource {

    private Integer id;

    private String gender;

    private double size;

    private double weight;

    private String birthplace;

    private String hereditaryHistory;

    private String nonPathologicalHistory;

    private String pathologicalHistory;
}
