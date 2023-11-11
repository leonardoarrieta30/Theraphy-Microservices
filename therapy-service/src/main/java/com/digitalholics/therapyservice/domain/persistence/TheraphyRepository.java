package com.digitalholics.therapyservice.domain.persistence;


import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.resource.TheraphyResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheraphyRepository extends JpaRepository<Theraphy, Integer> {

    //Theraphy findByAppointmentId(Integer appointmentId);
    Theraphy findTheraphyByPhysiotherapistId(Integer physiotherapistId);
    List<Theraphy> findTheraphiesByPhysiotherapistId(Integer physiotherapistId);

    List<Theraphy> findTheraphiesByPatientId(Integer patientId);

   // Theraphy findByPatientId(Integer patientId);
}
