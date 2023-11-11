package com.digitalholics.therapyservice.domain.persistence;


import com.digitalholics.therapyservice.domain.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

   // List<Treatment> findTreatmentByTheraphyId(Integer theraphyId);

}
