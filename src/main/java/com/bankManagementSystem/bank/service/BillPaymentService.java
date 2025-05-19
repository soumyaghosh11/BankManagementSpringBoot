package com.bankManagementSystem.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.Account;
import com.bankManagementSystem.bank.model.BillPayment;
import com.bankManagementSystem.bank.model.Transaction;
import com.bankManagementSystem.bank.repo.AccountRepository;
import com.bankManagementSystem.bank.repo.BillPaymentRepository;
import com.bankManagementSystem.bank.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class BillPaymentService {

	 @Autowired
	    private AccountRepository accountRepo;

	    @Autowired
	    private BillPaymentRepository billPaymentRepo;

	    @Autowired
	    private TransactionRepository transactionRepo;

	    @Transactional
	    public String processBillPayment(String accountNumber, String billerName, double amount, String referenceNumber) {
	        Optional<Account> accountOpt = accountRepo.findByAccountNumber(accountNumber);

	        if (accountOpt.isEmpty()) {
	            return "Account not found.";
	        }
	        Account account = accountOpt.get();
	        if (account.getBalance() < amount) {
	            return "Insufficient funds for bill payment.";
	        }

	        // Deduct amount
	        account.setBalance(account.getBalance() - amount);
	        accountRepo.save(account);

	        // Save bill payment transaction
	        BillPayment billPayment = new BillPayment(accountNumber, billerName, amount, referenceNumber, account);
	        billPaymentRepo.save(billPayment);

	        // Log transaction
	        Transaction transaction = new Transaction(accountNumber, "BILL_PAYMENT", amount, account, "SUCCESS");
	        transactionRepo.save(transaction);

	        return "Bill payment successful for " + billerName;
	    }

	    public List<BillPayment> getPaymentHistory(String accountNumber) {
	        return billPaymentRepo.findByAccount_AccountNumber(accountNumber);
	    }
	
	
}
