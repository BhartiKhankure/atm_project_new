package com.cg.atm.service;

import com.cg.atm.entity.Transaction;
import com.cg.atm.exception.AccNoNotFoundException;

public interface TransactionService {

	public void createTransaction(Transaction transaction);

	void transferMoney(long senderAccNo, long receiverAccNo, double amount) throws AccNoNotFoundException;
}
