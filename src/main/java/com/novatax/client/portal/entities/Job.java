package com.novatax.client.portal.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String urgency;
    @Column(name = "`repeat`") // Enclose 'repeat' in backticks
    private String repeat;
    private Date start_date;
    private Date due_date;
    private String status;
    private String tags;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to", referencedColumnName = "id")
    private Users assigned_to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_by", referencedColumnName = "id")
    private Users assigned_by;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Clients client;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "job-tasks") 
    private List<Tasks> tasks;

    @Lob
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments; // Store serialized list as a single TEXT column


    @ElementCollection
    private List<String> files;

    
    public List<String> getComments() {
        return comments == null ? new ArrayList<>() : deserializeList(comments);
    }

    public void setComments(List<String> comments) {
        this.comments = serializeList(comments);
    }
    private String serializeList(List<String> list) {
        return String.join(",", list);
    }

    private List<String> deserializeList(String data) {
        return new ArrayList<>(List.of(data.split(",")));
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
		return start_date;
	}

	public void setStart_Date(Date startDate) {
		this.start_date = startDate;
	}

	public Date getDue_Date() {
		return due_date;
	}

	public void setDue_Date(Date dueDate) {
		this.due_date = dueDate;
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

	public Users getAssignedTo() {
		return assigned_to;
	}

	public void setAssignedTo(Users assignedTo) {
		this.assigned_to = assignedTo;
	}

	public Users getAssignedBy() {
		return assigned_by;
	}

	public void setAssignedBy(Users assignedBy) {
		this.assigned_by = assignedBy;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}


    // Constructors, getters, setters, etc.
}
