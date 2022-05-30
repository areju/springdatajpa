package com.arjun.springpersistence.springdatajpa.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private LocalDate registrationdate;
	private String email;
	private int level;
	private boolean active;
	
    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, LocalDate registrationDate) {
        this.username = username;
        this.registrationdate = registrationDate;
    }

	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	public LocalDate getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(LocalDate registrationDate) {
		this.registrationdate = registrationDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("User").append("{").append("id=").append(id).append("email=")
				.append(email).append("registrationDate=").append(registrationdate).append("}");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if( this == obj) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		User user = (User) obj;
		return user.getId().equals(id);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id);
	}
	
	

}
