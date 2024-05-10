package com.novatax.client.portal.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.novatax.client.portal.entities.Tasks;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	 @GetMapping("/users/listall")
	    public List<Users> index(){
	        return userRepository.findAll();
	    }

	    @GetMapping("/users/findbyid/{id}")
	    public Users show(@PathVariable String id){
	    	Integer userId = Integer.parseInt(id);
	        return userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	    }
	    
	    @GetMapping("/users")
	    public List<Users> getTaskDetails(@RequestParam String email) {
	    	List<Users> users = userRepository.findByEmail(email);
	    	if(users != null && !users.isEmpty()) {
	    		return users;
	    	}
	    	return null;
	    }


//	    @PostMapping("/register")
//	    public ResponseEntity<String> create(@RequestBody Map<String, String> body){
//	    	Users savedUser = null;
//	    	ResponseEntity response = null;
//	    	try {
//	    		String username = body.get("username");
//		        String password = body.get("password");
//		        String hashPwd = passwordEncoder.encode(password);
//		        String email = body.get("email");
//		        String first_name = body.get("first_name");
//		        String last_name = body.get("last_name");
//		        String contactInfo = body.get("contactInfo");
//				Integer role_id = Integer.parseInt(body.get("role_id"));
//				LocalDate localDate = LocalDate.now();
//				java.sql.Date sqlDate2 = java.sql.Date.valueOf(localDate);
//			
//		        savedUser = userRepository.save(new Users(username, hashPwd,email, contactInfo, role_id, first_name,last_name, sqlDate2,sqlDate2, true ));
//		        if(savedUser.getId() > 0) {
//		        	response = ResponseEntity
//		        			.status(HttpStatus.CREATED)
//		        					.body("Given user details are successfully registered");
//		        }
//	    	}catch(Exception ex) {
//	    		response = ResponseEntity
//	    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	    				.body("An exception occured due to "+ ex.getMessage());
//	    	}
//	    	return response;
//	        
//	    }

	    @PutMapping("/users/update/{id}")
	    public Users update(@PathVariable String id, @RequestBody Map<String, String> body){
	    	  int userId = Integer.parseInt(id);
	    	  Users user = userRepository.findById(userId)
                      .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	    	  user.setUsername(body.get("username"));
	    	  user.setEmail(body.get("email"));
	    	  user.setFirst_name(body.get("first_name"));
	    	  user.setLast_name(body.get("last_name"));
	    	  user.setContactInfo(body.get("contactInfo"));
	    	  user.setRole_id(Integer.parseInt(body.get("role_id")));
	    	  LocalDate localDate = LocalDate.now();
	    	  java.sql.Date sqlDate2 = java.sql.Date.valueOf(localDate);
	    	  user.setUpdated_at(sqlDate2);
	    	  return userRepository.save(user);
	    }
	    
	    @PutMapping("/users/updatepassword/{id}")
	    public Users updatePassword(@PathVariable String id, @RequestBody Map<String, String> body){
	    	  int userId = Integer.parseInt(id);
	    	  Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	    	  user.setPassword(body.get("password"));
	    	  return userRepository.save(user);
	    }

	    @DeleteMapping("/users/delete/{id}")
	    public boolean delete(@PathVariable String id){
	        int userId = Integer.parseInt(id);
	        userRepository.deleteById(userId);
	        return true;
	    }
//	    
//	    @GetMapping("/user")
//	    public Users getUserDetailsAfterLogin(Authentication authentication) {
//	        List<Users> users = userRepository.findByEmail(authentication.getName());
//	        if (users.size() > 0) {
//	            return users.get(0);
//	        } else {
//	            return null;
//	        }
//
//	    }
	    
}
