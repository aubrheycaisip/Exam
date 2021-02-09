package com.java.exam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.exam.model.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long>{

	@Query(value="SELECT * FROM bank_account where account_number = :accountNumber and user_id = :userId", nativeQuery = true)
	BankAccount findByAccountNumberAndUserId(Long accountNumber, Long userId);
	
	BankAccount findByAccountNumber(Long rbn);
}
