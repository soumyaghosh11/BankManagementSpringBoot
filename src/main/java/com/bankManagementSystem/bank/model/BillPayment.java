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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BillPayment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int billId;
	private String accountNumber;
    private String billerName;
    private double amount;
    private String referenceNumber;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public BillPayment() {
        this.paymentDate = new Date(); // Auto-set timestamp
    }

    public BillPayment(String accountNumber, String billerName, double amount, String referenceNumber, Account account) {
        this.accountNumber = accountNumber;
        this.billerName = billerName;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
        this.account = account;
        this.paymentDate = new Date();
    }
}
