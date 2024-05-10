package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Messages;
import com.novatax.client.portal.entities.Users;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Integer> {

	List<Messages> findBySender_IdOrRecipient_IdOrderByThreadId(Integer senderId, Integer recipientId);

	List<Messages> findByThreadIdOrderByTimestampAsc(Integer threadId);
}