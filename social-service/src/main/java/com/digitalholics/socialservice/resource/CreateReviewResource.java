package com.digitalholics.socialservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewResource {
    private String content;
    private Integer score;
    private Integer physiotherapistId;
    private Integer patientId;
}
