package com.digitalholics.socialservice.service;


import com.digitalholics.socialservice.domain.model.entity.dto.Patient;
import com.digitalholics.socialservice.domain.model.entity.Review;
import com.digitalholics.socialservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.socialservice.domain.persistence.ReviewRepository;
import com.digitalholics.socialservice.domain.service.ReviewService;
import com.digitalholics.socialservice.feignClients.PhysiotherapistFeignClient;
import com.digitalholics.socialservice.mapping.exception.ResourceNotFoundException;
import com.digitalholics.socialservice.mapping.exception.ResourceValidationException;
import com.digitalholics.socialservice.resource.CreateReviewResource;
import com.digitalholics.socialservice.resource.UpdateReviewResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final String ENTITY = "Review";

    private final ReviewRepository reviewRepository;
//    private final PhysiotherapistRepository physiotherapistRepository;
//    private final PatientRepository patientRepository;
    private final Validator validator;

    private final PhysiotherapistFeignClient physiotherapistFeignClient;
    private final RestTemplate restTemplate;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, Validator validator, PhysiotherapistFeignClient physiotherapistFeignClient, RestTemplate restTemplate) {
        this.reviewRepository = reviewRepository;
        this.validator = validator;
        this.physiotherapistFeignClient = physiotherapistFeignClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Page<Review> getAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review getById(Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, reviewId));
    }

    @Override
    public List<Review> getByPhysiotherapistId(Integer physiotherapistId) {
        List<Review> reviews = reviewRepository.findByPhysiotherapistId(physiotherapistId);

        if(reviews.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Reviews for this physiotherapist");

        return reviews;
    }

    @Override
    public Review create(CreateReviewResource reviewResource) {
        Set<ConstraintViolation<CreateReviewResource>> violations = validator.validate(reviewResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

//        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.findPatientsByUserUsername(username));
//        Patient patient = patientOptional.orElseThrow(() -> new NotFoundException("Not found patient with email: " + username));


//        Optional<Physiotherapist> physiotherapistOptional = physiotherapistRepository.findById(reviewResource.getPhysiotherapistId());
//        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(() -> new NotFoundException("Not found physiotherapist with ID: " + reviewResource.getPhysiotherapistId()));

        Review review = new Review();
        if(this.getPatientById(reviewResource.getPatientId()) == null ){
            throw new ResourceNotFoundException("Patient not found");
        }else{
            review.setPatientId(reviewResource.getPatientId());
        }
        if(this.getPhysiotherapistById(reviewResource.getPhysiotherapistId()) == null){
            throw new ResourceNotFoundException("Physiotherapist not found");
        }else{
            review.setPhysiotherapistId(reviewResource.getPhysiotherapistId());
        }
        //review.setPatient(patient);
       // review.setPhysiotherapist(physiotherapist);
        review.setContent(reviewResource.getContent());
        review.setScore(reviewResource.getScore());


        double ratingPhysiotherapist = 0.0;
        List<Review> reviewsPhysiotherapist = reviewRepository.findByPhysiotherapistId(review.getPhysiotherapistId());

        for (Review existingReview : reviewsPhysiotherapist) {
            ratingPhysiotherapist = ratingPhysiotherapist + existingReview.getScore();
;        }
        ratingPhysiotherapist = ratingPhysiotherapist + review.getScore();
        ratingPhysiotherapist = ratingPhysiotherapist/(reviewsPhysiotherapist.size()+1) ;

        this.getPhysiotherapistById(review.getPhysiotherapistId()).setRating(ratingPhysiotherapist);

        //post
        physiotherapistFeignClient.save(review.getPhysiotherapistId(), this.getPhysiotherapistById(review.getPhysiotherapistId()));
        //physiotherapistRepository.save(physiotherapist);


        return reviewRepository.save(review);
    }

    @Override
    public Review update(Integer reviewId, UpdateReviewResource request) {
        Review review = getById(reviewId);

        if (request.getContent() != null) {
            review.setContent(request.getContent());
        }
        if (request.getScore() != null) {
            review.setScore(request.getScore());
        }

        return reviewRepository.save(review);
    }


    @Override
    public ResponseEntity<?> delete(Integer reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            reviewRepository.delete(review);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,reviewId));
    }



    @Override
    public Patient getPatientById(Integer patientId){
        Patient patient  = restTemplate.getForObject("http://localhost:7010/api/v1/patients/" + patientId, Patient.class);
        return patient;
    }

    @Override
    public Physiotherapist getPhysiotherapistById(Integer physiotherapistId){
        Physiotherapist physiotherapist  = restTemplate.getForObject("http://localhost:7008/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        return physiotherapist;
    }


}
