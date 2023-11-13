package com.digitalholics.physiotherapistservice.domain.persistence;

import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;
import com.digitalholics.physiotherapistservice.domain.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist,Integer> {

    Physiotherapist findPhysiotherapistByDni(String dni);

    Optional<Physiotherapist> findByUserId(Integer userId);

    Physiotherapist findPhysiotherapistByUserId(Integer userId);

    //Physiotherapist findPhysiotherapistByUserId(Integer userId);

    //Optional<Physiotherapist> findPatientByUserId(Integer userId);

}