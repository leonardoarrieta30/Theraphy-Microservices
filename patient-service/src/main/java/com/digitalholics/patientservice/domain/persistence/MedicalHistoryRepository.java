package com.digitalholics.patientservice.domain.persistence;

import com.digitalholics.patientservice.domain.model.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

    Optional<MedicalHistory> findById(Integer medicalHistoryId);

    Optional<MedicalHistory> findByHereditaryHistory(String hereditaryHistory);

    Optional<MedicalHistory> findByNonPathologicalHistory(String nonPathologicalHistory);

    Optional<MedicalHistory> findByPathologicalHistory(String pathologicalHistory);
}
