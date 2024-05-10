package com.novatax.client.portal.entities;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.novatax.client.portal.repository.UserRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer task_id;
	
	@Column(nullable = false)
	private String task_description;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to", referencedColumnName = "id")
    private Users assignee;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_by", referencedColumnName = "id")
    private Users assigner;
	
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


	public Tasks(String task_description, Users assignee, Users assigner, Date due_date,
			String priority, String status, String notes, Date created_at, Date updated_at) {
		super();
		this.task_description = task_description;
		this.assignee = assignee;
		this.assigner = assigner;
		this.due_date = due_date;
		this.priority = priority;
		this.status = status;
		this.notes = notes;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}


	public Integer getTask_id() {
		return task_id;
	}


	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}


	public String getTask_description() {
		return task_description;
	}


	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}


	public Users getAssignee() {
		return assignee;
	}


	public void setAssignee(Users assignee) {
		this.assignee = assignee;
	}


	public Users getAssigner() {
		return assigner;
	}


	public void setAssigner(Users assigner) {
		this.assigner = assigner;
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
	
	
}
