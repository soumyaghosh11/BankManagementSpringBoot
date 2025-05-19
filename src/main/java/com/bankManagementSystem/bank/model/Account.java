package com.bankManagementSystem.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountId;
	private String accountType;
	private double balance;
	private String accountNumber;
	private String ifscCode;
	private String branch;
	private boolean isActive;
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt;
	
	@ManyToOne
    @JoinColumn(name = "customerId")
    private User user; 
	
	@PrePersist
	protected void onCreate() {
	   createdAt = new java.util.Date();
	   updatedAt = new java.util.Date();
	}

	@PreUpdate
	protected void onUpdate() {
	    updatedAt = new java.util.Date();
	}
}
