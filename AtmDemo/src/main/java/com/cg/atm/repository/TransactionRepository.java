package com.cg.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.atm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Transaction findByTransactionId(long transactionId);

}
