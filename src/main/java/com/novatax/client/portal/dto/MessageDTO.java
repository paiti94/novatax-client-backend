package com.novatax.client.portal.dto;

import java.sql.Date;
import java.time.LocalDate;



public class MessageDTO {
	    private Integer sender_id;
	    private Integer recipient_id;
	    private String subject;
	    private String body;
	    private Date timestamp;
	    private Boolean is_read;
	    private Boolean is_archived;
	    private Boolean is_deleted;
	    private String attachment;
	    private String attachment_type;
	    private String attachment_name;
	    private Integer threadId;
	    private Integer parentMessageId;
		private String status;
		
		public Integer getSender_id() {
			return sender_id;
		}
		public void setSender_id(Integer senderId) {
			this.sender_id = senderId;
		}
		public Integer getRecipient_id() {
			return recipient_id;
		}
		public void setRecipient_id(Integer recipientId) {
			this.recipient_id = recipientId;
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
		public Boolean getIs_read() {
			return is_read;
		}
		public void setIs_read(Boolean is_read) {
			this.is_read = is_read;
		}
		public Boolean getIs_archived() {
			return is_archived;
		}
		public void setIs_archived(Boolean is_archived) {
			this.is_archived = is_archived;
		}
		public Boolean getIs_deleted() {
			return is_deleted;
		}
		public void setIs_deleted(Boolean is_deleted) {
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
		public Integer getParentMessageId() {
			return parentMessageId;
		}
		public void setParentMessageId(Integer parentMessageId) {
			this.parentMessageId = parentMessageId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public MessageDTO( Integer senderId, Integer recipientId, String subject, String body,
				 Boolean is_read, Boolean is_archived, Boolean is_deleted, String attachment,
				String attachment_type, String attachment_name, Integer threadId, Integer parentMessageId,
				String status) {
			
			super();
			this.sender_id = senderId;
			
			this.recipient_id = recipientId;
			this.subject = subject;
			this.body = body;
			LocalDate currentDate = LocalDate.now();
			Date sqlDate = Date.valueOf(currentDate);
			this.timestamp = sqlDate;
			this.is_read = is_read;
			this.is_archived = is_archived;
			this.is_deleted = is_deleted;
			this.attachment = attachment;
			this.attachment_type = attachment_type;
			this.attachment_name = attachment_name;
			this.threadId = threadId;
			this.parentMessageId = parentMessageId;
			this.status = status;
		}
		public MessageDTO() {}

}

