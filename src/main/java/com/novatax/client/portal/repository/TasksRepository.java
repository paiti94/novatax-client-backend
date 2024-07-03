package com.novatax.client.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByJob_Id(Integer jobId);
	// custom query to search to blog post by title or content

	List<Tasks> findByClient_Id(Integer clientId);
    
}
