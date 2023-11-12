package com.digitalholics.healthrecordandexpertise.service;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Job;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.domain.persistence.JobRepository;
import com.digitalholics.healthrecordandexpertise.domain.service.JobService;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.healthrecordandexpertise.mapping.Exception.ResourceValidationException;
import com.digitalholics.healthrecordandexpertise.resource.CreateJobResource;
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
public class JobServiceImpl implements JobService {
    private static final String ENTITY = "Job";

    private final JobRepository jobRepository;

    private final Validator validator;

    private final RestTemplate restTemplate;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, Validator validator, RestTemplate restTemplate) {
        this.jobRepository = jobRepository;
        this.validator = validator;
        this.restTemplate = restTemplate;
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
    public Job create(CreateJobResource jobResource) {
        Set<ConstraintViolation<CreateJobResource>> violations = validator.validate(jobResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        Job job = new Job();
        job.setPosition(jobResource.getPosition());
        job.setOrganization(jobResource.getOrganization());
        if(this.getPhysiotherapistById(jobResource.getPhysiotherapistId()) == null){
            throw new ResourceNotFoundException("Physiotherapist not found");
        }else{
            job.setPhysiotherapistId(jobResource.getPhysiotherapistId());
        }
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

    @Override
    public Physiotherapist getPhysiotherapistById(Integer physiotherapistId){
        Physiotherapist physiotherapist  = restTemplate.getForObject("http://localhost:7008/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        return physiotherapist;
    }

}
