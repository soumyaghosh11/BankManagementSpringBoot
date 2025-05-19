package com.bankManagementSystem.bank.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LoanApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int loanApplicationId;
	private String customerId;
    private double loanAmount;
    private String loanPurpose;
    private String status;  // PENDING, APPROVED, REJECTED
    private String referenceNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;

    public LoanApplication() {
        this.applicationDate = new Date(); 
        this.status = "PENDING"; 
    }

    public LoanApplication(String customerId, double loanAmount, String loanPurpose, String referenceNumber) {
        this.customerId = customerId;
        this.loanAmount = loanAmount;
        this.loanPurpose = loanPurpose;
        this.status = "PENDING";
        this.referenceNumber = referenceNumber;
        this.applicationDate = new Date();
    }
}
