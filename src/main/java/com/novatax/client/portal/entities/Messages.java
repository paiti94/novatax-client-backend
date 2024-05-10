package com.novatax.client.portal.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer message_id;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "sender_id", referencedColumnName = "id") // Specify the foreign key column name
	    private Users sender;

	  @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "recipient_id", referencedColumnName = "id") // Specify the foreign key column name
	    private Users recipient;
	
	@Column(nullable = false)
	private String subject;

	@Column(nullable = false)
	private String body;
	
	@Column
	private Date timestamp;
	    
	@Column
	private boolean is_read;
	   
	@Column
	private boolean is_archived;
	  
	@Column
	private boolean is_deleted;
	    
	@Column
	private String attachment;

	@Column
	private String attachment_type;
	
	@Column
	private String attachment_name;

    @Column
	private Integer threadId;

    @OneToMany(mappedBy = "parentMessage")
    private List<Messages> replies = new ArrayList<>();
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_message_id", nullable = true)
	private Messages parentMessage;
	    
	@Column
	private String status;

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Users getSender() {
		return sender;
	}

	public void setSender(Users sender) {
		this.sender = sender;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public boolean isIs_archived() {
		return is_archived;
	}

	public void setIs_archived(boolean is_archived) {
		this.is_archived = is_archived;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachment_type() {
		return attachment_type;
	}

	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	public List<Messages> getReplies() {
		return replies;
	}

	public void setReplies(List<Messages> replies) {
		this.replies = replies;
	}

	public Messages getParentMessage() {
		return parentMessage;
	}

	public void setParentMessage(Messages parentMessage) {
		this.parentMessage = parentMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Messages(Integer message_id, Users sender, Users recipient, String subject, String body, Date timestamp,
			boolean is_read, boolean is_archived, boolean is_deleted, String attachment, String attachment_type,
			String attachment_name, Integer threadId, List<Messages> replies, Messages parentMessage, String status) {
		super();
		this.message_id = message_id;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
		this.timestamp = timestamp;
		this.is_read = is_read;
		this.is_archived = is_archived;
		this.is_deleted = is_deleted;
		this.attachment = attachment;
		this.attachment_type = attachment_type;
		this.attachment_name = attachment_name;
		this.threadId = threadId;
		this.replies = replies;
		this.parentMessage = parentMessage;
		this.status = status;
	}

	public Messages() {}

}
