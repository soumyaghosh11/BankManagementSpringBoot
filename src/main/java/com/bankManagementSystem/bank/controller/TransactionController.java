package com.bankManagementSystem.bank.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankManagementSystem.bank.model.Transaction;
import com.bankManagementSystem.bank.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	
	@Autowired
	private TransactionService transactionService;
	
	
	 @GetMapping("/statement")
	    public ResponseEntity<List<Transaction>> getTransactionStatement(
	            @RequestParam(name="accountNumber") String accountNumber,
	            @RequestParam(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	            @RequestParam(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		 	LocalDateTime startDateTime = startDate.atStartOfDay();
		    LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
		    List<Transaction> transactions = transactionService.getTransactionStatement(accountNumber, startDateTime, endDateTime);
	        return ResponseEntity.ok(transactions);
	    }
}
