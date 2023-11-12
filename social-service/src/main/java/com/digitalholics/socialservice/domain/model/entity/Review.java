package com.digitalholics.socialservice.domain.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String content;

    @Max(5)
    @Min(1)
    private Integer score;

//    @ManyToOne
//    @JoinColumn(name = "physiotherapist_id")
//    @JsonIgnore
//    private Physiotherapist physiotherapist;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    @JsonIgnore
//    private Patient patient;


    private Integer physiotherapistId;
    private Integer patientId;


}
