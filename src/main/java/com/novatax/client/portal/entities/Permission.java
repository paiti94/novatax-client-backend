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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permission_id;

    @Column(nullable = false, unique = true)
    private String permission_name;
    
    @Column(nullable = false, unique = true)
    private String permission_description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	public String getPermission_name() {
		return permission_name;
	}

	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}

	public String getPermission_description() {
		return permission_description;
	}

	public void setPermission_description(String permission_description) {
		this.permission_description = permission_description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Permission(int permission_id, String permission_name, String permission_description,
			Set<Role> roles) {
		super();
		this.permission_id = permission_id;
		this.permission_name = permission_name;
		this.permission_description = permission_description;
		this.roles = roles;
	}

	public Permission() {}
    
}