package com.bankManagementSystem.bank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankManagementSystem.bank.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> getByAccountNumber(String accountNumber);

	Optional<Account> findByAccountNumber(String fromAccountNumber);

}
