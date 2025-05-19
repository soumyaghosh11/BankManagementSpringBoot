package com.bankManagementSystem.bank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankManagementSystem.bank.model.LoanApplication;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer>{

	List<LoanApplication> findByCustomerId(String customerId);

	List<LoanApplication> findByStatus(String status);

}
