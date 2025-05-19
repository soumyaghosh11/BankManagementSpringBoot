package com.bankManagementSystem.bank.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankManagementSystem.bank.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccount_AccountNumberAndTransactionDateBetween(
		    String accountNumber, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
