package com.digitalholics.patientservice.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_histories")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(name = "gender")
    private String gender;

    @NotNull
    @Column(name = "size")
    private double size;

    @NotNull
    @Column(name = "weight")
    private double weight;

    @NotNull
    @NotBlank
    @Size(max = 300)
    @Column(name = "birthplace")
    private String birthplace;

    @Size(max = 300)
    @Column(name = "hereditary_history")
    private String hereditaryHistory;

    @Size(max = 300)
    @Column(name = "non_pathological_history")
    private String nonPathologicalHistory;

    @Size(max = 300)
    @Column(name = "pathological_history")
    private String pathologicalHistory;

    @OneToOne(mappedBy = "medicalHistory")
    private Patient patient;
}
