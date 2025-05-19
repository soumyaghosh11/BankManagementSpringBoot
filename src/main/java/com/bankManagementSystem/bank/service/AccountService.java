package com.bankManagementSystem.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.Account;
import com.bankManagementSystem.bank.model.Transaction;
import com.bankManagementSystem.bank.repo.AccountRepository;
import com.bankManagementSystem.bank.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;

	public ResponseEntity<Account> openCustomerAccount(Account account) {
		try {
            Account savedAccount = accountRepo.save(account);
            return new ResponseEntity<>(savedAccount, HttpStatus.CREATED); // HTTP 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
        }
	}

	public ResponseEntity<Account> updateCustomerAccount(int accountId, Account updatedAccount) {
		Account existingAccount = accountRepo.findById(accountId).get();
		if(existingAccount!=null) {
			if(updatedAccount.getAccountNumber()!=null)
				existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
			if(updatedAccount.getAccountType()!=null)
				existingAccount.setAccountType(updatedAccount.getAccountType());
			if(updatedAccount.getBalance()!= 0.0)
			    existingAccount.setBalance(updatedAccount.getBalance());
			if(updatedAccount.getBranch()!=null)
				existingAccount.setBranch(updatedAccount.getBranch());
			if(updatedAccount.getIfscCode()!=null)
				existingAccount.setIfscCode(updatedAccount.getIfscCode());
			return new ResponseEntity<>(accountRepo.save(existingAccount), HttpStatus.OK);
		}
		else {
			throw new RuntimeException("Account not found with ID: " + accountId);
		}
	}


	public ResponseEntity<Double> checkAccountBalance(String accountNumber) {
		Optional<Account> request = accountRepo.getByAccountNumber(accountNumber);
		if(request!=null) {
			Account account = request.get();
			return new  ResponseEntity<>(account.getBalance(), HttpStatus.OK);
		}
		else {
			throw new RuntimeException("Account not found with Account Number: " + accountNumber);
		}
	}
	
	@Transactional
    public String transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Optional<Account> fromAccountOpt = accountRepo.findByAccountNumber(fromAccountNumber);
        Optional<Account> toAccountOpt = accountRepo.findByAccountNumber(toAccountNumber);

        // Validate accounts
        if (fromAccountOpt.isEmpty() || toAccountOpt.isEmpty()) {
            return "One or both accounts not found.";
        }

        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();
        Double transferAmount = Double.valueOf(amount);

        // Check sufficient balance
        if (fromAccount.getBalance()- transferAmount < 0) {
            return "Insufficient balance in the source account.";
        }

        // Perform transfer
        fromAccount.setBalance(fromAccount.getBalance()-transferAmount);
        toAccount.setBalance(toAccount.getBalance()+transferAmount);

        // Save updated accounts
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        
     // Save transaction
	    Transaction fromTransaction = new Transaction(fromAccountNumber, "TRANSFER", amount, fromAccount, "SUCCESS");
	    transactionRepo.save(fromTransaction);
	    
	    Transaction toTransaction = new Transaction(toAccountNumber, "TRANSFER", amount, toAccount, "SUCCESS");
	    transactionRepo.save(toTransaction);
	    
        return "Funds transferred successfully.";
    }

	@Transactional
	public String depositMoney(String accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepo.findByAccountNumber(accountNumber);
		if(accountOpt.isEmpty())
			return "Account not found";
		Account account = accountOpt.get();
		account.setBalance(account.getBalance()+amount);
		
		// Save transaction
	    Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, account, "SUCCESS");
	    transactionRepo.save(transaction);
	    
		return "Deposit successful. New balance:"+account.getBalance();	
		
	}

	@Transactional
	public String withdrawMoney(String accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepo.findByAccountNumber(accountNumber);
		if(accountOpt.isEmpty())
			return "Account not found";
		Account account = accountOpt.get();
		if(account.getBalance()-amount<0) {
			// Save transaction
		    Transaction transaction = new Transaction(accountNumber, "WITHDRAWAL", amount, account, "FAILED");
		    transactionRepo.save(transaction);
			return "Insufficient bank balance";
		}
		else {
		account.setBalance(account.getBalance()-amount);
		// Save transaction
	    Transaction transaction = new Transaction(accountNumber, "WITHDRAWAL", amount, account, "SUCCESS");
	    transactionRepo.save(transaction);
		return "Withdrawal successfull. New Balance:"+account.getBalance();
		}
	}

}
