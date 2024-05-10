package com.novatax.client.portal.entities;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.novatax.client.portal.repository.UserRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(nullable = false, unique = true)
    private String role_name;

    @ManyToMany
    @JoinTable(
        name = "Role_Permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
    
    public Role() {}
    
	public Role(Integer id, String name, Set<Permission> permissions) {
		super();
		this.role_id = id;
		this.role_name = name;
		this.permissions = permissions;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer id) {
		this.role_id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String name) {
		this.role_name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

    
}