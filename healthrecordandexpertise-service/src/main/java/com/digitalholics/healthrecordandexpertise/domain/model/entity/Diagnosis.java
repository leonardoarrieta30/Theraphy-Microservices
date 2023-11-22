package com.digitalholics.healthrecordandexpertise.domain.model.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diagnoses")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  
    private String diagnosis;

    @Column(name = "physiotherapist_id")
    private Integer physiotherapistId;

    @Column(name = "patient_id")
    private Integer patientId;

    @NotNull
    @NotBlank
    private String date;
}
