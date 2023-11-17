package com.digitalholics.healthrecordandexpertise.api.rest;


import com.digitalholics.healthrecordandexpertise.domain.service.JobService;
import com.digitalholics.healthrecordandexpertise.mapping.JobMapper;
import com.digitalholics.healthrecordandexpertise.resource.CreateJobResource;
import com.digitalholics.healthrecordandexpertise.resource.JobResource;
import com.digitalholics.healthrecordandexpertise.resource.UpdateJobResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/healthrecordandexpertise/jobs", produces = "application/json")
@Tag(name = "Jobs", description = "Create, read, update and delete jobs")
public class JobsController {
    private final JobService jobService;

    private final JobMapper mapper;


    public JobsController(JobService jobService, JobMapper mapper) {
        this.jobService = jobService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public Page<JobResource> getAllJobs(Pageable pageable) {
        return mapper.modelListPage(jobService.getAll(), pageable);
    }

    @GetMapping("{jobId}")
    public JobResource getJobById(@PathVariable Integer jobId) {
        return mapper.toResource(jobService.getById(jobId));
    }


    @PostMapping()
    public ResponseEntity<JobResource> createJob(@RequestBody CreateJobResource resource) {
        return new ResponseEntity<>(mapper.toResource(jobService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("updatedJobById{jobId}")
    public JobResource updateJob(@PathVariable Integer jobId,
                                                     @RequestBody UpdateJobResource resource) {
        return mapper.toResource(jobService.update(jobId, mapper.toModel(resource)));
    }

    @DeleteMapping("{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Integer jobId) {
        return jobService.delete(jobId);
    }
}
