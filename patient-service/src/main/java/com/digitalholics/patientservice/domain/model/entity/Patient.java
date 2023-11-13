package com.digitalholics.patientservice.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "password")
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 300)
    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull
    @NotBlank
    @Column(name = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthday;

    @NotNull
    @NotBlank
    @Column(name = "appointment_quantity")
    @Size(max = 300)
    private String appointmentQuantity;

    @NotNull
    @NotBlank
    @Column(name = "location")
    private String location;

    @Column(name = "user_id")
    private Integer userId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "medical_history_id", referencedColumnName = "id")
//    private MedicalHistory medicalHistory;

}
