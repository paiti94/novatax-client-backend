package com.novatax.client.portal.dto;

import java.util.Date;
import java.util.List;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;

public class JobDTO {
    private Integer id;
    private String name;
    private String description;
    private String urgency;
    private String repeat;
    private Date start_Date;
    private Date due_Date;
    private String status;
    private String tags;
    private List<String> comments;
    private List<String> files;
    private Users assignedTo;
    private Users assignedBy;
    private Clients client;
    private List<TaskDTO> tasks;
    
    
	public List<TaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
	public void setClient(Clients client) {
		this.client = client;
	}
	public Users getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Users assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Users getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(Users assignedBy) {
		this.assignedBy = assignedBy;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public Date getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(Date startDate) {
		this.start_Date = startDate;
	}
	public Date getDue_Date() {
		return due_Date;
	}
	public void setDue_Date(Date dueDate) {
		this.due_Date = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	public Clients getClient() {
		return client;
	}
	public void setClientId(Clients client) {
		this.client = client;
	}

    // getters and setters
    
}