package com.novatax.client.portal.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.UserRepository;

@Service
public class ClientService {
	  @Autowired
	    private ClientRepository clientRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Transactional
	    public Clients createClient(Clients client) {
	        Clients savedClient = clientRepository.save(client);
	        Optional<Users> userOpt = userRepository.findByEmail(client.getEmail());
	        if (userOpt.isPresent()) {
	            Users user = userOpt.get();
	            savedClient.setUser(user);
	            user.setClient(savedClient);
	            userRepository.save(user);
	            clientRepository.save(savedClient);
	        }
	        return savedClient;
	    }
}
