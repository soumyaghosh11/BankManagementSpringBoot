package com.bankManagementSystem.bank.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

	 private String accountNumber;
	 private String type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
	 private double amount;
	    
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
    private String status;
    
    // Constructors
    public Transaction() {
        this.transactionDate = new Date(); // Set current timestamp
    }

	public Transaction(String accountNumber, String type, double amount, Account account, String status) {
		this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.status = status;
        this.transactionDate = new Date();
	}


}
