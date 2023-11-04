package com.digitalholics.physiotherapistservice.domain.persistence;

import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist,Integer> {

    Physiotherapist findPhysiotherapistByDni(String dni);

}