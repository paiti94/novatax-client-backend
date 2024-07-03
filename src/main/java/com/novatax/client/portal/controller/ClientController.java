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
import com.novatax.client.portal.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
    ClientService clientService;

	 @GetMapping("/listall")
	    public List<Clients> index(){
	        return clientRepository.findAll();
	    }

	    @GetMapping("/find/{id}")
	    public Optional<Clients> show(@PathVariable String id){
	        int clientId = Integer.parseInt(id);
	        return clientRepository.findById(clientId);
	    }


//	    @PostMapping("/create")
//	    public ResponseEntity<?> createClient(@RequestBody Map<String, String> body) {
//	        try {
//	            String firstName = body.get("first_name");
//	            String lastName = body.get("last_name");
//	            String email = body.get("email");
//	            String phoneNumber = body.get("phone_number");
//	            String address = body.get("address");
//	            String city = body.get("city");
//	            String province = body.get("province");
//	            String postalCode = body.get("postal_code");
//	            String country = body.get("country");
//	            String taxIdNumber = body.get("tax_id_number");
//	            
//	            LocalDate dateOfBirth = LocalDate.parse(body.get("date_of_birth"));
//	            Date sqlDateOfBirth = java.sql.Date.valueOf(dateOfBirth);
//	            
//	            String notes = body.get("notes");
//	            String status = body.get("status");
//
//	            Clients client = new Clients();
//	            client.setFirst_name(firstName);
//	            client.setLast_name(lastName);
//	            client.setEmail(email);
//	            client.setPhone_number(phoneNumber);
//	            client.setAddress(address);
//	            client.setCity(city);
//	            client.setProvince(province);
//	            client.setPostal_code(postalCode);
//	            client.setCountry(country);
//	            client.setTax_id_number(taxIdNumber);
//	            client.setDate_of_birth(sqlDateOfBirth);
//	            client.setNotes(notes);
//	            client.setStatus(status);
//
//	            Clients savedClient = clientRepository.save(client);
//	            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
//	        } catch (DateTimeParseException e) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date_of_birth format");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//	        }
//	    }
	    

	    @PostMapping("/create")
	    public ResponseEntity<?> createClient(@RequestBody Clients client) {
	        try {
	        	Clients clientCreated = clientService.createClient(client);
	        	return ResponseEntity.ok(clientCreated);
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The Email is already existing in the system");
	        }
	    	
	    }
	    
	    
	    @PutMapping("/associate-user/{clientId}/{userId}")
	    public ResponseEntity<?> associateUserWithClient(@PathVariable Integer clientId, @PathVariable Integer userId) {
	        try {
	            Clients client = clientRepository.findById(clientId)
	                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

	            Users user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	            client.setUser(user);
	            clientRepository.save(client);

	            return ResponseEntity.ok(client);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	        }
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
