package com.novatax.client.portal.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novatax.client.portal.dto.MessageDTO;
import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Messages;
import com.novatax.client.portal.entities.TaxReturn;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.TaxReturnRepository;
import com.novatax.client.portal.repository.UserRepository;
import com.novatax.client.portal.services.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin("http://localhost:4200")
public class MessageController {

	@Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            Messages message = messageService.sendMessage(messageDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getMessagesForUser(@PathVariable int userId) {
        try {
            List<Messages> messages = messageService.getMessagesForUser(userId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get messages for user: " + e.getMessage());
        }
    }

    @GetMapping("/thread/{threadId}")
    public ResponseEntity<?> getMessageThread(@PathVariable int threadId) {
        try {
            List<Messages> messages = messageService.getMessageThread(threadId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get message thread: " + e.getMessage());
        }
    }

    @PostMapping("/reply")
    public ResponseEntity<?> replyToMessage(@RequestBody MessageDTO messageDTO) {
        try {
            Messages message = messageService.replyToMessage(messageDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reply to message: " + e.getMessage());
        }
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable int messageId) {
        try {
            messageService.deleteMessage(messageId);
            return ResponseEntity.ok("Message deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete message: " + e.getMessage());
        }
    }
    
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Messages send(MessageDTO messageDTO) throws Exception{
    	Messages message = messageService.sendMessage(messageDTO); 
    	return message;
    }

//    @PutMapping("/{messageId}")
//    public ResponseEntity<?> updateMessage(@PathVariable int messageId, @RequestBody MessageDTO messageDTO) {
//        try {
//            Messages updatedMessage = messageService.updateMessage(messageId, messageDTO);
//            return ResponseEntity.ok(updatedMessage);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update message: " + e.getMessage());
//        }
//    }
}
