package com.cg.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.atm.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccNo(long accNo);

	Account deleteByAccNo(long accNo);

	//Account findByAccNo(Account accNo);
}
