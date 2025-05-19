package com.bankManagementSystem.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankManagementSystem.bank.model.LoanApplication;
import com.bankManagementSystem.bank.service.LoanApplicationService;

@RestController
@RequestMapping("/loan")
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationService loanService;
	
	@PostMapping("/apply")
    public ResponseEntity<String> applyForLoan(@RequestParam(name="customerId") String customerId,
            @RequestParam(name="loanAmount") double loanAmount,
            @RequestParam(name="loanPurpose") String loanPurpose) {

        String result = loanService.applyForLoan(customerId, loanAmount, loanPurpose);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/applications/{customerId}")
    public ResponseEntity<List<LoanApplication>> getCustomerLoans(@PathVariable(name="customerId") String customerId) {
        List<LoanApplication> applications = loanService.getLoanApplicationsByCustomer(customerId);
        return ResponseEntity.ok(applications);
    }
    
    @GetMapping("/applicationStatus")
    public ResponseEntity<List<LoanApplication>> getLoanApplicationByStatus(@RequestParam(name="status") String status){
    	List<LoanApplication> applications = loanService.getLoanApplicationByStatus(status);
    	return ResponseEntity.ok(applications);
    }
    

}
