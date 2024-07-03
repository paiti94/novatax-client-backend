package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novatax.client.portal.entities.File;

public interface FileRepository extends JpaRepository<File, Long> {
}