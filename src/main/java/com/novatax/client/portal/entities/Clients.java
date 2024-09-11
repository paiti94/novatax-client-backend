package com.novatax.client.portal.entities;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.novatax.client.portal.repository.UserRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Clients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String first_name;
	
	@Column(nullable = false)
	private String last_name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = true)
	private String phone_number;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String province;
	
	@Column(nullable = false)
	private String postal_code;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = true)
	private String tax_id_number;
	
	@Column(nullable = false)
	private Date date_of_birth;
	
	@Column(nullable = true)
	private String notes;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = true)
	private String tags;
	
	@Column(nullable = true)
	private String company_info;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonManagedReference
	private Users user;
	
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "client-tasks")
    private List<Tasks> tasks;


	
	public Clients() {}
	
	public Clients(String first_name, String last_name, String email, String phone_number, String address, String city,
			String province, String postal_code, String country, String tax_id_number, Date date_of_birth, String notes,
				String status) {
			super();
			this.first_name = first_name;
			this.last_name = last_name;
			this.email = email;
			this.phone_number = phone_number;
			this.address = address;
			this.city = city;
			this.province = province;
			this.postal_code = postal_code;
			this.country = country;
			this.tax_id_number = tax_id_number;
			this.date_of_birth = date_of_birth;
			this.notes = notes;
			this.status = status;
		}

	public Clients(String first_name, String last_name, String email, String phone_number, String address, String city,
		String province, String postal_code, String country, String tax_id_number, Date date_of_birth, String notes,
			String status, Users user) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
		this.city = city;
		this.province = province;
		this.postal_code = postal_code;
		this.country = country;
		this.tax_id_number = tax_id_number;
		this.date_of_birth = date_of_birth;
		this.notes = notes;
		this.status = status;
		this.user = user;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer client_id) {
		this.id = client_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTax_id_number() {
		return tax_id_number;
	}

	public void setTax_id_number(String tax_id_number) {
		this.tax_id_number = tax_id_number;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}
}
