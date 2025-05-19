package com.bankManagementSystem.bank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.Transaction;
import com.bankManagementSystem.bank.repo.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepo;

	 public List<Transaction> getTransactionStatement(String accountNumber, LocalDateTime startDateTime, LocalDateTime endDateTime) {
	        return transactionRepo.findByAccount_AccountNumberAndTransactionDateBetween(accountNumber, startDateTime, endDateTime);
	 }
	
	
}
