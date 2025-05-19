package com.bankManagementSystem.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.LoanApplication;
import com.bankManagementSystem.bank.repo.LoanApplicationRepository;

@Service
public class LoanApplicationService {
	
	@Autowired
	private LoanApplicationRepository loanRepo;

	public String applyForLoan(String customerId, double loanAmount, String loanPurpose) {
		if (loanAmount <= 0) {
            return "Loan amount must be greater than zero.";
        }

        String referenceNumber = "LOAN" + System.currentTimeMillis();

        LoanApplication loan = new LoanApplication(customerId, loanAmount, loanPurpose, referenceNumber);
        loanRepo.save(loan);

        return "Loan application submitted successfully. Reference: " + referenceNumber;
	}

	public List<LoanApplication> getLoanApplicationsByCustomer(String customerId) {
		return loanRepo.findByCustomerId(customerId);
	}

	public List<LoanApplication> getLoanApplicationByStatus(String status) {
		return loanRepo.findByStatus(status);
	}

}
