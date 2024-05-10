package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Role;
import com.novatax.client.portal.entities.Users;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	// custom query to search to blog post by title or content
    
}
