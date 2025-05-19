package com.bankManagementSystem.bank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankManagementSystem.bank.model.BillPayment;

@Repository
public interface BillPaymentRepository extends JpaRepository<BillPayment, Integer>{

	List<BillPayment> findByAccount_AccountNumber(String accountNumber);

}
