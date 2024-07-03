package com.novatax.client.portal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.novatax.client.portal.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
