package com.bankManagementSystem.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankManagementSystem.bank.model.Account;
import com.bankManagementSystem.bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/openAccount")
	public ResponseEntity<Account> openAccount(@RequestBody Account account){
		return accountService.openCustomerAccount(account);
	}
	
	@PutMapping("/updateAccount/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable("accountId") int accountId, @RequestBody Account account){
		return accountService.updateCustomerAccount(accountId, account);
	}
	
	@GetMapping("/balance/{accountNumber}")
	public ResponseEntity<Double> checkAccount(@PathVariable("accountNumber") String accountNumber){
		return accountService.checkAccountBalance(accountNumber);
	}
	
	//TransferFund
	@PostMapping("/funds")
    public ResponseEntity<String> transferFunds(@RequestParam(name = "fromAccountNumber") String fromAccount,
    		@RequestParam(name="toAccountNumber")String toAccount, @RequestParam(name="amount")double amount) {

        String result = accountService.transferFunds(fromAccount, toAccount, amount);
        return ResponseEntity.ok(result);
    }
	
	 // Deposit Money API
    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam(name="accountNumber") String accountNumber,
    		@RequestParam(name="amount", defaultValue="0.0") double amount) {
        String result = accountService.depositMoney(accountNumber, amount);
        return ResponseEntity.ok(result);
    }

    // Withdraw Money API
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam(name="accountNumber") String accountNumber, 
    		@RequestParam(name="amount", defaultValue="0.0") double amount) {
        String result = accountService.withdrawMoney(accountNumber, amount);
        return ResponseEntity.ok(result);
    }
}
