package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novatax.client.portal.entities.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Integer> {
}