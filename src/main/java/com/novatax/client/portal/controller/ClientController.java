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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.UserRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientRepository clientRepository;

	 @GetMapping("/listall")
	    public List<Clients> index(){
	        return clientRepository.findAll();
	    }

	    @GetMapping("/find/{id}")
	    public Optional<Clients> show(@PathVariable String id){
	        int clientId = Integer.parseInt(id);
	        return clientRepository.findById(clientId);
	    }


	    @PostMapping("/create")
	    public Clients create(@RequestBody Map<String, String> body){
	    
	        String first_name = body.get("first_name");
	        String last_name = body.get("last_name");
	        String email = body.get("email");
	        String phone_number = body.get("phone_number");
	        String address = body.get("address");
	        String city = body.get("city");
	        String province = body.get("province");
	        String postal_code = body.get("postal_code");
	        String country = body.get("country");
	        String tax_id_number = body.get("tax_id_number");
			LocalDate date_of_birth = LocalDate.parse(body.get("date_of_birth"));
			Date sql_date_of_birth = java.sql.Date.valueOf(date_of_birth);
			String notes = body.get("notes");
			String status = body.get("status");
			int userId = Integer.parseInt(body.get("user_id"));
	        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	        return clientRepository.save(new Clients(first_name, last_name, email, phone_number, address, city, province, postal_code, country, tax_id_number, sql_date_of_birth, notes, status, user));
	
	   }


	    @PutMapping("/update/{id}")
	    public Clients update(@PathVariable String id, @RequestBody Map<String, String> body){
	    	  int clientId = Integer.parseInt(id);
	    	    Clients client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
	    	    client.setFirst_name(body.get("first_name"));
	    	    client.setLast_name(body.get("last_name"));
	    	    client.setEmail(body.get("email"));
	    	    client.setPhone_number(body.get("phone_number"));
	    	    client.setAddress(body.get("address"));
	    	    client.setCity(body.get("city"));
	    	    client.setProvince(body.get("province"));
	    	    client.setPostal_code(body.get("postal_code"));
	    	    client.setCountry(body.get("country"));
	    	    client.setTax_id_number(body.get("tax_id_number"));
	    	    client.setNotes(body.get("notes"));
	    	    client.setStatus(body.get("status"));
	    	    LocalDate date_of_birth = LocalDate.parse(body.get("date_of_birth"));
	    	    java.sql.Date sql_date_of_birth = java.sql.Date.valueOf(date_of_birth);
	    	    return clientRepository.save(client);
	    	  
	    }

	    @DeleteMapping("/delete/{id}")
	    public boolean delete(@PathVariable String id){
	        int clientId = Integer.parseInt(id);
	        clientRepository.deleteById(clientId);
	        return true;
	    }
}
