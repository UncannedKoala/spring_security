package com.spring_security.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String user_name;

	private String password;

//	The fetching has to be EAGER here, because by the time when it is lazily loaded the transaction responsible to fetch this object is already commited.
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authorities", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authority;
}
