package com.bankManagementSystem.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BankManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementSystemApplication.class, args);
	}

}
