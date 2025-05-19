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

import com.bankManagementSystem.bank.model.BillPayment;
import com.bankManagementSystem.bank.service.BillPaymentService;

@RestController
@RequestMapping("/bill")
public class BillPaymentController {
	
	@Autowired
	private BillPaymentService billPaymentService;

	 @PostMapping("/pay")
	  public ResponseEntity<String> payBill(
	            @RequestParam(name="accountNumber") String accountNumber,
	            @RequestParam(name="billerName") String billerName,
	            @RequestParam(name="amount") double amount,
	            @RequestParam(name="referenceNumber") String referenceNumber) {

	        String result = billPaymentService.processBillPayment(accountNumber, billerName, amount, referenceNumber);
	        return ResponseEntity.ok(result);
	    }
	 
	 @GetMapping("/payment-history/{accountNumber}")
	 public ResponseEntity<List<BillPayment>> getPaymentHistory(@PathVariable(name="accountNumber") String accountNumber){
		 return ResponseEntity.ok(billPaymentService.getPaymentHistory(accountNumber));
	 }
	 
	 
}
