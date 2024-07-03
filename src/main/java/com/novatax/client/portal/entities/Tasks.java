package com.novatax.client.portal.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks") 
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type; 
	
	@Column(nullable = true)
	private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference(value = "client-tasks")
    private Clients client;
    
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", nullable = false)
    @JsonBackReference(value = "job-tasks")
    private Job job;

    
	@Column
	private Date due_date;
	
	@Column
	private String priority;
	
	@Column
	private String status;
	
	@Column
	private String notes;
	
	@Column
	private Date created_at;
	
	@Column
	private Date updated_at;

	
	public Tasks() {}


	public Tasks(String task_description, Date due_date,
			String priority, String status, String notes, Date created_at, Date updated_at) {
		super();
		this.description = task_description;
		this.due_date = due_date;
		this.priority = priority;
		this.status = status;
		this.notes = notes;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}


	public Clients getClient() {
		return client;
	}


	public void setClient(Clients client) {
		this.client = client;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer task_id) {
		this.id = task_id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String task_description) {
		this.description = task_description;
	}


	public Date getDue_date() {
		return due_date;
	}


	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Date getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	
}
