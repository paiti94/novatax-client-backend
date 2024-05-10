package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.TaxReturn;
import com.novatax.client.portal.entities.Users;

import java.util.List;

@Repository
public interface TaxReturnRepository extends JpaRepository<TaxReturn, Integer> {
	// custom query to search to blog post by title or content
	
}
