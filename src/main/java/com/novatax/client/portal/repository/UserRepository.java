package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Users;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	// custom query to search to blog post by title or content
	 List<Users> findByEmail(String email);
}
