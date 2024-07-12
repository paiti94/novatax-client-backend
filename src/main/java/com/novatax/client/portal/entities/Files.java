	package com.novatax.client.portal.entities;
	
	import java.util.Date;
	import jakarta.persistence.*;
	
	@Entity
	public class Files {
	
			@Id	
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Integer id;
	
		    private String fileName;
		    private String fileType;
		    private String fileLocation;
	
		    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		    @JoinColumn(name = "job_id", nullable = true)
		    private Job job;
	
		    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		    @JoinColumn(name = "client_id", nullable = false)
		    private Clients client;
	
		    private Date uploaded_at;
		    
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
	
			public Files(String fileName, String fileType, String fileLocation, Job job, Clients client,
					Date uploaded_at) {
				super();
				this.fileName = fileName;
				this.fileType = fileType;
				this.fileLocation = fileLocation;
				this.job = job;
				this.client = client;
				this.uploaded_at = uploaded_at;
			}
		    
	}
