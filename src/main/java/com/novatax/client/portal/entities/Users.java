package com.novatax.client.portal.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Users {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  
	  @Column(nullable = true)
	  private String password;

	  @Column(nullable = false)
	  private String username;
	
	  @Column(nullable = false)
	  private String email;
	
	  @Column(nullable = true)
	  private String contactInfo;
	
	  @Column(nullable = true)
	  private int role_id;

	  @Column(nullable = false)
	  private String first_name;
	
	  @Column(nullable = false)
	  private String last_name;
		  
	  @Column(nullable = false)
	  private Date created_at;
		  
	  @Column(nullable = true)
	  private Date updated_at;
		  
	  @Column(nullable = false)
	  private boolean is_active;
	  
	  @Column(nullable = true)
	  private String roles = "";
	  
	  @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	  @JsonBackReference
	  private Clients client;

	  public Clients getClient() {
		return client;
	  }

	public void setClient(Clients client) {
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Users() {}
	public Users( String username, String password, String email, String contactInfo, int role_id, String first_name,
			String last_name, Date created_at, Date updated_at, boolean is_active) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactInfo = contactInfo;
		this.role_id = role_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.is_active = is_active;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	  public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

		public void setId(Integer id) {
			this.id = id;
		}


//	  
//	   @Override
//	    public String toString() {
//	        return "";
//	    }
	  
}
