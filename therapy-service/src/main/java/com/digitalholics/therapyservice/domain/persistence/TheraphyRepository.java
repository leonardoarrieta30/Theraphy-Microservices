package com.digitalholics.therapyservice.domain.persistence;


import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheraphyRepository extends JpaRepository<Theraphy, Integer> {


}
