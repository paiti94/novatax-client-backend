package com.novatax.client.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatax.client.portal.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

	List<Job> findByClient_Id(Integer clientId);
}
