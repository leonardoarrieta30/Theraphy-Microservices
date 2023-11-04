package com.digitalholics.patientservice.resource.MedicalHistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMedicalHistoryResource {

    private String gender;

    private double size;

    private double weight;

    private String birthplace;

    private String hereditaryHistory;

    private String nonPathologicalHistory;

    private String pathologicalHistory;
}
