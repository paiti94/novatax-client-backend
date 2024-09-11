package com.novatax.client.portal.services;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.FileRepository;
import com.novatax.client.portal.repository.UserRepository;

import javax.annotation.PostConstruct;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService {

	@Autowired
    private Keycloak keycloak;

    @Autowired
    private UserRepository usersRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private FileStorageService fileService;
    
    @Autowired
    private FileRepository fileRepository;
    
    @Transactional
    @PostConstruct
    public void fetchUsers() throws Exception {
    	 RealmResource realmResource = keycloak.realm("novaclientportal");
    	    UsersResource usersResource = realmResource.users();
    	    List<UserRepresentation> users = usersResource.list();
    	    
    	    for (UserRepresentation keycloakUser : users) {
    	        Users user = mapToUserEntity(keycloakUser);
    	        Optional<Users> existingUserOpt = usersRepository.findByUsername(user.getUsername());
    	        
    	        if (existingUserOpt.isPresent()) {
    	            Users existingUser = existingUserOpt.get();
    	            updateUserFields(existingUser, user, keycloakUser);
    	            usersRepository.save(existingUser);

    	            // Check and create default folders if they do not exist
    	           // ensureDefaultFoldersExist(existingUser.getClient());
    	        } else {
    	            if (isUserNew(user)) {
    	                Optional<Clients> clientOpt = clientRepository.findByEmail(user.getEmail());
    	                if (clientOpt.isPresent()) {
    	                    Clients client = clientOpt.get();
    	                    user.setClient(client);
    	                    client.setUser(user);
    	                    saveUserAndClient(user, client);
    	                    // Create default folders
    	                    ensureDefaultFoldersExist(client);
    	                } else {
    	                    // Handle case where client is not present
    	                    saveUserAndClient(user, null);
    	                }
    	            }
    	        }
    	    }
    }
    
    public List<Users> getAllAdminUsers() {
        List<Users> allUsers = usersRepository.findAll();
        return allUsers.stream()
                       .filter(user -> Optional.ofNullable(user.getRoles())
                                               .orElse("")
                                               .contains("ADMIN"))
                       .collect(Collectors.toList());
    }

    @Transactional
    public void saveUserAndClient(Users user, Clients client) {
        if (client != null) {
            if (client.getId() != null) {
                client = clientRepository.findById(client.getId()).orElseThrow();
                client = clientRepository.save(client); 
            } else {
                client = clientRepository.save(client); // Save the client to generate the ID
            }
            user.setClient(client);
        }

        if (user.getId() != null) {
            user = usersRepository.findById(user.getId()).orElseThrow();
        }
        
        user.setClient(client);
        usersRepository.save(user); // Save the user which references the client
        if (client != null) {
            client.setUser(user);
            clientRepository.save(client); // Save the client to update the user reference
        }
    }
    

    private Users mapToUserEntity(UserRepresentation keycloakUser) {
        Users user = new Users();
        user.setUsername(keycloakUser.getUsername());
        user.setEmail(keycloakUser.getEmail());
        user.setFirst_name(keycloakUser.getFirstName());
        user.setLast_name(keycloakUser.getLastName());
        user.setIs_active(keycloakUser.isEnabled());
        user.setCreated_at(new Date(keycloakUser.getCreatedTimestamp()));  // Using Keycloak timestamp
        user.setUpdated_at(new Date(System.currentTimeMillis()));  // Current timestamp
        user.setRoles(fetchUserRoles(keycloakUser.getId()));
        return user;
    }

    
    private boolean isUserNew(Users user) {
        return !usersRepository.findByUsername(user.getUsername()).isPresent() &&
               !usersRepository.findByEmail(user.getEmail()).isPresent();
    }
    
    
    private String fetchUserRoles(String userId) {
    	 RealmResource realmResource = keycloak.realm("novaclientportal");
    	 RoleMappingResource roleMappingResource = realmResource.users().get(userId).roles();

    	    // Fetch realm-level roles
    	 List<RoleRepresentation> realmRoles = roleMappingResource.realmLevel().listEffective();
    	 List<String> roleNames = new ArrayList<>();
    	 for (RoleRepresentation role : realmRoles) {
    	      roleNames.add(role.getName());
    	 }
    	 String rolesString = String.join(",", roleNames);
    	 return rolesString;
    }
    
    private void updateUserFields(Users existingUser, Users newUser, UserRepresentation newUserRepresentation ) {

        if (!existingUser.getEmail().equals(newUser.getEmail())) {
            existingUser.setEmail(newUser.getEmail());
        }
        if (!existingUser.getFirst_name().equals(newUser.getFirst_name())) {
            existingUser.setFirst_name(newUser.getFirst_name());
        }
        if (!existingUser.getLast_name().equals(newUser.getLast_name())) {
            existingUser.setLast_name(newUser.getLast_name());
        }
        if (newUser.getContactInfo() != null && !newUser.getContactInfo().equals(existingUser.getContactInfo())) {
            existingUser.setContactInfo(newUser.getContactInfo());
        }
        if (newUser.getRole_id() != existingUser.getRole_id()) {
            existingUser.setRole_id(newUser.getRole_id());
        }
        if (newUser.isIs_active() != existingUser.isIs_active()) {
            existingUser.setIs_active(newUser.isIs_active());
        }
        existingUser.setUpdated_at(new Date(System.currentTimeMillis()));

        String newRoles = fetchUserRoles(newUserRepresentation.getId());
        if (!newRoles.equals(existingUser.getRoles())) {
            existingUser.setRoles(newRoles);
        }
        // Add other fields that need to be updated
    }
    
    private void ensureDefaultFoldersExist(Clients client) throws Exception {
        boolean sharedFolderExists = fileRepository.existsByClientIdAndGuiLocation(client.getId(), "/shared");
        boolean internalFolderExists = fileRepository.existsByClientIdAndGuiLocation(client.getId(), "/internal");

        if (!sharedFolderExists) {
            fileService.storeFolder("Client Shared Folder", null, client.getId(), "/shared", null);
        }

        if (!internalFolderExists) {
            fileService.storeFolder("Internal Folder", null, client.getId(), "/internal", null);
        }
    }
    
    
    
}
