package com.digitalholics.physiotherapistservice.service;

import com.digitalholics.physiotherapistservice.domain.model.Job;
import com.digitalholics.physiotherapistservice.domain.persistence.JobRepository;
import com.digitalholics.physiotherapistservice.domain.service.JobService;
import com.digitalholics.physiotherapistservice.mapping.exception.ResourceNotFoundException;
import com.digitalholics.physiotherapistservice.mapping.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    private static final String ENTITY = "Job";

    private final JobRepository jobRepository;

    private final Validator validator;

    public JobServiceImpl(JobRepository jobRepository, Validator validator) {
        this.jobRepository = jobRepository;
        this.validator = validator;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Page<Job> getAll(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public Job getById(Integer jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, jobId));
    }

    @Override
    public Job create(Job job) {
        Set<ConstraintViolation<Job>> violations = validator.validate(job);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return jobRepository.save(job);
    }

    @Override
    public Job update(Integer jobId, Job request) {
        Set<ConstraintViolation<Job>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return jobRepository.findById(jobId).map( certification ->
                        jobRepository.save(
                                certification.withPosition(request.getPosition()).
                                        withOrganization(request.getOrganization())
                                        ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,jobId));
    }

    @Override
    public ResponseEntity<?> delete(Integer jobId) {
        return jobRepository.findById(jobId).map(job -> {
            jobRepository.delete(job);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,jobId));
    }
}
