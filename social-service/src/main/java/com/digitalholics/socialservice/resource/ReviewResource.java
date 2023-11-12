package com.digitalholics.socialservice.resource;


import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResource {
    private Integer id;
    private String content;
    private Integer score;
//    private PhysiotherapistResource physiotherapist;
//    private PatientResource patient;

    private Integer physiotherapistId;
    private Integer patientId;
}
