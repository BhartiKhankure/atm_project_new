package com.cg.atm.service;

import com.cg.atm.entity.Account;
import com.cg.atm.entity.Transaction;
import com.cg.atm.entity.User;

public interface UserService {

	public User createUser(User userRequest);

	//public User deleteUser(long userId, long accNo, long transactionId);

	public Account deleteAccount(long accNo);
	
	public Account deposit(long accNo, double amount);

	Account withdraw(long accNo, double amount);

	Account createAccount(Account account);

	public Transaction createTransaction(Transaction transaction);

	//Account deleteAccount(Account accNo);

	public User deleteUser(long userId);
	


}
