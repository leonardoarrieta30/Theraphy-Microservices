package com.digitalholics.healthrecordandexpertise.domain.persistence;


import com.digitalholics.healthrecordandexpertise.domain.model.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {


}
