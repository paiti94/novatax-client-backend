package com.novatax.client.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novatax.client.portal.dto.JobDTO;
import com.novatax.client.portal.entities.Job;
import com.novatax.client.portal.services.JobService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Integer id) {
        JobDTO job = jobService.getJobById(id);
        if (job == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "No jobs found for Job ID: " + id);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getJobsByClientId(@PathVariable int clientId) {
        List<Job> jobs = jobService.getJobByClientId(clientId);
        if (jobs == null || jobs.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "No jobs found for client ID: " + clientId);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    
    @PostMapping
    public Job createJob(@RequestBody JobDTO jobDto) {
    		 return jobService.createJob(jobDto);
    	     
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody JobDTO jobDTO) throws Exception {
            Job job = jobService.updateJob(id, jobDTO);
            return new ResponseEntity<>(job, HttpStatus.OK);
    }

    // Other endpoints...
}