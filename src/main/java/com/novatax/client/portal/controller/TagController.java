package com.novatax.client.portal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novatax.client.portal.dto.JobDTO;
import com.novatax.client.portal.entities.Job;
import com.novatax.client.portal.entities.Tag;
import com.novatax.client.portal.repository.TagRepository;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	TagRepository tagRepository;
	
	@GetMapping
	public List<Tag> getAllTags() {	
		return tagRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> getTag(@PathVariable int tagId) {
		 Optional<Tag> tag = tagRepository.findById(tagId);
		 return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Tag createTag(@RequestBody Tag tag) {
		 return tagRepository.save(tag);
	     
	}
	
	 @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        tagRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
	 
}
