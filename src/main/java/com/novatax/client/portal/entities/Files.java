	package com.novatax.client.portal.entities;
	
	import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
	
	@Entity
	public class Files {
	
			@Id	
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Integer id;
	
		    private String fileName;
		    private String fileType;
		    private String fileLocation;
		    private String guiLocation;
	
		    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		    @JoinColumn(name = "job_id", nullable = true)
		    @JsonIgnore
		    private Job job;
	
		    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		    @JoinColumn(name = "client_id", nullable = false)
		    @JsonIgnore
		    private Clients client;
	
		    private Date uploaded_at;
		    
		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "parent_id", nullable = true)
		    private Files parent;

		    
			public Files getParent() {
				return parent;
			}

			public void setParent(Files parent) {
				this.parent = parent;
			}

			public Integer getId() {
				return id;
			}
	
			public void setId(Integer id) {
				this.id = id;
			}
	
			public String getFileName() {
				return fileName;
			}
	
			public void setFileName(String fileName) {
				this.fileName = fileName;
			}
	
			public String getFileType() {
				return fileType;
			}
	
			public void setFileType(String fileType) {
				this.fileType = fileType;
			}
	
			public String getFileLocation() {
				return fileLocation;
			}
	
			public void setFileLocation(String fileLocation) {
				this.fileLocation = fileLocation;
			}
	
			public Job getJob() {
				return job;
			}
	
			public void setJob(Job job) {
				this.job = job;
			}
	
			public Clients getClient() {
				return client;
			}
	
			public void setClient(Clients client) {
				this.client = client;
			}
	
			public Date getUploaded_at() {
				return uploaded_at;
			}
	
			public void setUploaded_at(Date uploaded_at) {
				this.uploaded_at = uploaded_at;
			}
	
			public String getGuiLocation() {
				return guiLocation;
			}

			public void setGuiLocation(String guiLocation) {
				this.guiLocation = guiLocation;
			}
			
			public Files() {}

			public Files(String fileName, String fileType, String fileLocation, String guiLocation, Job job, Clients client,Files parent,
					Date uploaded_at) {
				super();
				this.fileName = fileName;
				this.fileType = fileType;
				this.fileLocation = fileLocation;
				this.guiLocation = guiLocation;
				this.job = job;
				this.client = client;
				this.uploaded_at = uploaded_at;
				this.parent = parent;
			}
		    
	}
