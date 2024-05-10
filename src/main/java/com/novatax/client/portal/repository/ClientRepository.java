package com.novatax.client.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer> {
	// custom query to search to blog post by title or content
	@Query("SELECT c.client_id, c.first_name, c.last_name, c.email, c.phone_number, c.address, c.city, c.province, c.postal_code, c.country, c.tax_id_number, c.date_of_birth, c.notes, c.status FROM Clients c")
	List<Clients> findAllClients();
}
