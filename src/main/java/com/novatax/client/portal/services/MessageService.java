package com.novatax.client.portal.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novatax.client.portal.dto.MessageDTO;
import com.novatax.client.portal.entities.Messages;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.MessageRepository;
import com.novatax.client.portal.repository.UserRepository;

@Service
public class MessageService {
	  private final MessageRepository messageRepository;
	  
	  private final UserRepository userRepository;

	    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
	        this.messageRepository = messageRepository;
	        this.userRepository = userRepository;
	    }

	    // Method to save a message
	    public Messages saveMessage(Messages message) {
	        return messageRepository.save(message);
	    }

	    // Method to retrieve all messages
	    public List<Messages> getAllMessages() {
	        return messageRepository.findAll();
	    }

	    // Method to retrieve a message by ID
	    public Messages getMessageById(int id) {
	        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found with id: "+id));	    }

	    // Method to delete a message by ID
	    public void deleteMessageById(int id) {
	        messageRepository.deleteById(id);
	    }
	    
	    public Messages sendMessage(MessageDTO messageDTO) {
	        // Create a new Message entity from the DTO
	        Messages message = new Messages();
	        int senderId = messageDTO.getSender_id();
	        Users sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found with id: "+ senderId));
	        message.setSender(sender);
	        int recipientId = messageDTO.getRecipient_id();
	        Users recipient = userRepository.findById(recipientId).orElseThrow(() -> new RuntimeException("Recipient not found with id: "+ recipientId));
	        message.setRecipient(recipient);
	        message.setSubject(messageDTO.getSubject());
	        message.setBody(messageDTO.getBody());
			message.setTimestamp(messageDTO.getTimestamp());
			message.setIs_read(messageDTO.getIs_read());
			message.setIs_archived(messageDTO.getIs_archived());
			message.setIs_deleted(messageDTO.getIs_deleted());
	        message.setAttachment(messageDTO.getAttachment());
	        message.setAttachment_type(messageDTO.getAttachment_type());
	        message.setAttachment_name(messageDTO.getAttachment_name());
	        // Check if this is a new conversation thread
	        if (messageDTO.getThreadId() == null || messageDTO.getThreadId() == 0) {
	            // Generate a new threadId
	            int threadId = generateThreadId();
	            message.setThreadId(threadId);
	            
	            // For a new thread, the parentMessageId is usually null
	            message.setParentMessage(null);
	        }else {
	            // If it's a reply, use the provided threadId and parentMessageId
	            message.setThreadId(messageDTO.getThreadId());
	            int parent_msg_id = messageDTO.getParentMessageId();
	            Messages parentMsg = messageRepository.findById(parent_msg_id).orElseThrow(() -> new RuntimeException("Parent Message not found with id: "+ parent_msg_id));
	            message.setParentMessage(parentMsg);
	        }
	        return messageRepository.save(message);
	    }

	    private Integer generateThreadId() {
	    	return (int) System.currentTimeMillis();
	    }
	    
	    public List<Messages> getMessagesForUser(int user_id) {
	        // Retrieve messages for the specified user using the repository
	        return messageRepository.findBySender_IdOrRecipient_IdOrderByThreadId(user_id, user_id);
	    }

	    public List<Messages> getMessageThread(int threadId) {
	        // Retrieve messages for the specified thread using the repository
	        return messageRepository.findByThreadIdOrderByTimestampAsc(threadId);
	    }

	    public Messages replyToMessage(MessageDTO messageDTO) {
	        // Create a new Message entity from the DTO
	    	Messages message = new Messages();
	    	int senderId = messageDTO.getSender_id();
		    Users sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found with id: "+ senderId));
		    message.setSender(sender);
		    int recipientId = messageDTO.getRecipient_id();
		    Users recipient = userRepository.findById(recipientId).orElseThrow(() -> new RuntimeException("Recipient not found with id: "+ recipientId));
		    message.setRecipient(recipient);
		    message.setSubject(messageDTO.getSubject());
		    message.setBody(messageDTO.getBody());
		    message.setTimestamp(messageDTO.getTimestamp());
		    message.setIs_read(messageDTO.getIs_read());
		    message.setIs_archived(messageDTO.getIs_archived());
		    message.setIs_deleted(messageDTO.getIs_deleted());
		    message.setAttachment(messageDTO.getAttachment());
		    message.setAttachment_type(messageDTO.getAttachment_type());
		    message.setAttachment_name(messageDTO.getAttachment_name());
		    int parentMessageId = messageDTO.getParentMessageId();
		    Messages parentMessage = messageRepository.findById(parentMessageId).orElseThrow(() -> new RuntimeException("Parent Message not found with id: "+ parentMessageId));

		    // Set the thread ID of the new message to the same value as the parent message's thread ID
		    message.setThreadId(parentMessage.getThreadId());
		    message.setParentMessage(parentMessage); // Set the parent message ID to the ID of the parent message

		    // Save the message using the repository
		    return messageRepository.save(message);
	    }

	    public void deleteMessage(int messageId) {
	        // Delete the message with the specified ID using the repository
	        messageRepository.deleteById(messageId);
	    }

//	    public Messages updateMessage(int messageId, MessageDTO messageDTO) {
//	        // Retrieve the message with the specified ID from the repository
//	       Messages Message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found with id: "+ messageId));
//	       
//	            // Update the message properties based on the DTO
//	        	
//	            message.setTitle(messageDTO.getTitle());
//	            message.setBody(messageDTO.getBody());
//	            message.setAttachment(messageDTO.getAttachment());
//	            return messageRepository.save(message);
//	      
//	    }
	    
	    
}
