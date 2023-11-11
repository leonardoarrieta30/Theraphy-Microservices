package com.digitalholics.therapyservice.service;

import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.domain.model.entity.Treatment;
import com.digitalholics.therapyservice.domain.persistence.TheraphyRepository;
import com.digitalholics.therapyservice.domain.persistence.TreatmentRepository;
import com.digitalholics.therapyservice.domain.service.TreatmentService;
import com.digitalholics.therapyservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.therapyservice.mapping.Exception.ResourceValidationException;
import com.digitalholics.therapyservice.resource.Treatment.CreateTreatmentResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TreatmentServiceImpl implements TreatmentService {



    private static final String ENTITY = "Treatment";

    private final TreatmentRepository treatmentRepository;
    private final TheraphyRepository theraphyRepository;


    private final Validator validator;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, TheraphyRepository theraphyRepository, Validator validator) {
        this.treatmentRepository = treatmentRepository;
        this.theraphyRepository = theraphyRepository;
        this.validator = validator;
    }

    @Override
    public List<Treatment> getAll() {
        return treatmentRepository.findAll();
    }

    @Override
    public Page<Treatment> getAll(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }

    @Override
    public Treatment getById(Integer treatmentId) {
        return treatmentRepository.findById(treatmentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, treatmentId));
    }



    @Override
    public Treatment create(CreateTreatmentResource treatmentResource) {
        Set<ConstraintViolation<CreateTreatmentResource>> violations = validator.validate(treatmentResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

        Optional<Theraphy> theraphyOptional = theraphyRepository.findById(treatmentResource.getTheraphyId());

        Theraphy theraphy = theraphyOptional.orElseThrow(() -> new NotFoundException("Theraphy not found with ID: " + treatmentResource.getTheraphyId()));

        Treatment treatment = new Treatment();
        return treatmentRepository.save(treatment);
    }


//    @Override
//    public List<Treatment> getTreatmentByTheraphyId(Integer theraphyId) {
//        return treatmentRepository.findTreatmentByTheraphyId(theraphyId);
//    }

    @Override
    public Treatment update(Integer treatmentId, Treatment request) {
        Set<ConstraintViolation<Treatment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return treatmentRepository.findById(treatmentId).map(treatment ->
                treatmentRepository.save(
                        treatment.withDescription(request.getDescription())
                                .withVideoUrl(request.getVideoUrl())
                                .withDuration(request.getDuration())
                                .withTitle(request.getTitle())
                                .withDescription(request.getDescription())
                                .withDay(request.getDay())
                                .withViewed(request.getViewed())
                )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, treatmentId));
    }

    @Override
    public ResponseEntity<?> delete(Integer treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .map(treatment -> {
                    treatmentRepository.delete(treatment);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, treatmentId));
    }
}









