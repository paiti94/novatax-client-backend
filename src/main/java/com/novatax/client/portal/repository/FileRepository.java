package com.novatax.client.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novatax.client.portal.entities.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Integer> {

	boolean existsByClientIdAndGuiLocation(Integer id, String string);
	
	List<Files> findByClientIdAndFileType(Integer clientId, String fileType);

	List<Files> findByClientIdAndParentId(Integer clientId, Integer parentId);
}