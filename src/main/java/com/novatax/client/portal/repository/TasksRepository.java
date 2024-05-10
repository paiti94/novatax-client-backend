package com.novatax.client.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByAssigneeId(Integer id);
	// custom query to search to blog post by title or content
    
}
