package com.digitalholics.socialservice.domain.persistence;


import com.digitalholics.socialservice.domain.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByPhysiotherapistId(Integer physiotherapistId);
}
