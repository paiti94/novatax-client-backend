package com.novatax.client.portal.services;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import java.util.Optional;

@Service
public class EntityListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostPersist
    @PostUpdate
    public void onPostPersistOrUpdate(Object entity) {
        if (entity instanceof Users) {
            Users user = (Users) entity;
            Optional<Clients> clientOpt = clientRepository.findByEmail(user.getEmail());
            if (clientOpt.isPresent()) {
                Clients client = clientOpt.get();
                if (client.getUser() == null) {
                    client.setUser(user);
                    user.setClient(client);
                    clientRepository.save(client);
                }
            }
        } else if (entity instanceof Clients) {
            Clients client = (Clients) entity;
            Optional<Users> userOpt = userRepository.findByEmail(client.getEmail());
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                if (user.getClient() == null) {
                    user.setClient(client);
                    client.setUser(user);
                    userRepository.save(user);
                }
            }
        }
    }
}