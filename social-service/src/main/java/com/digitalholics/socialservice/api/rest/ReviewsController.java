package com.digitalholics.socialservice.api.rest;


import com.digitalholics.socialservice.domain.service.ReviewService;
import com.digitalholics.socialservice.mapping.ReviewMapper;
import com.digitalholics.socialservice.resource.CreateReviewResource;
import com.digitalholics.socialservice.resource.ReviewResource;
import com.digitalholics.socialservice.resource.UpdateReviewResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/reviews", produces = "application/json")
@Tag(name = "Reviews", description = "Create, read, update and delete reviews")
public class ReviewsController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    public ReviewsController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ReviewResource> getAllReviews(Pageable pageable) {
        return mapper.modelListPage(reviewService.getAll(), pageable);
    }

    @GetMapping("{reviewId}")
    public ReviewResource getReviewById(@PathVariable Integer reviewId) {
        return mapper.toResource(reviewService.getById(reviewId));
    }

    @GetMapping("byPhysiotherapistId/{physiotherapistId}")
    public Page<ReviewResource> getReviewsByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
        return mapper.modelListPage(reviewService.getByPhysiotherapistId(physiotherapistId), pageable);
    }


    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        return new ResponseEntity<>(mapper.toResource(reviewService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{reviewId}")
    public ResponseEntity<ReviewResource> patchReview(
            @PathVariable Integer reviewId,
            @RequestBody UpdateReviewResource request) {

        return new  ResponseEntity<>(mapper.toResource(reviewService.update(reviewId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId) {
        return reviewService.delete(reviewId);
    }
}
