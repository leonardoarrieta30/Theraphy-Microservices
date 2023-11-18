package com.digitalholics.socialservice.domain.service;

import com.digitalholics.socialservice.domain.model.entity.dto.Patient;
import com.digitalholics.socialservice.domain.model.entity.Review;
import com.digitalholics.socialservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.socialservice.resource.CreateReviewResource;
import com.digitalholics.socialservice.resource.UpdateReviewResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    Page<Review> getAll(Pageable pageable);
    Review getById(Integer reviewId);
    List<Review> getByPhysiotherapistId(Integer physiotherapistId);
    Review create(CreateReviewResource review);
    Review update(Integer reviewId, UpdateReviewResource request);
    ResponseEntity<?> delete(Integer reviewId);
    Patient getPatientById(Integer patientId);
    Physiotherapist getPhysiotherapistById(Integer physiotherapistId);
    List<Review> getPatientId(Integer patientId);

    List<Review> getPatientIdAndPhysiotherapistId(Integer patientId, Integer physiotherapistId);
}
