package com.cg.atm.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.atm.dto.AccountDto;
import com.cg.atm.dto.TransactionDto;
import com.cg.atm.dto.UserDto;
import com.cg.atm.entity.Account;
import com.cg.atm.entity.Transaction;
import com.cg.atm.entity.User;
import com.cg.atm.exception.InsufficientAccBalanceException;
import com.cg.atm.repository.AccountRepository;
import com.cg.atm.repository.TransactionRepository;
import com.cg.atm.repository.UserRepository;
import com.cg.atm.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private String withdraw = "withdrawal";
	private String deposit = "deposit";
	
	public User findByUserId(long userId) {
		return userRepository.findByUserId(userId);
	}
	
	public User deleteByUserId(long userId) {
		return userRepository.deleteByUserId(userId);
	}
	
	@Override
	public User createUser(User user) {
		User newUser = userRepository.save(user);
		Account acc = new Account();
		acc.setAccBalance(0);
		acc.setUser(newUser);
		//acc.setAccNo(0);
		createAccount(acc);
		return newUser;
	}

	@Override
	public Account createAccount(Account account) {
		Account newAccount = accountRepository.save(account);
		return newAccount;
	}
	
//	@Override
//	public User deleteUser(long userId, long accNo, long transactionId) {
//		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
//		Account account = accountRepository.findByAccNo(accNo);
//		User user = userRepository.findByUserId(userId);
//		
//		transactionRepository.delete(transaction);
//		accountRepository.delete(account);
//		userRepository.delete(user);
//		return user;
//	}
	
	@Override
	public Account deleteAccount(long accNo) {
		Account account = accountRepository.findByAccNo(accNo);
		User user = userRepository.findByUserId(accNo);
		accountRepository.delete(account);
		return account;
	}

	@Override
	public Account deposit(long accNo, double amount) {
		Account account = accountRepository.findByAccNo(accNo);
		account.deposit(amount);
		accountRepository.save(account);
		Transaction Trans =new Transaction();
		Trans.setTransactionAmount(amount);
		Trans.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
		Trans.setTransactionType(deposit);
		Trans.setAccount(account);
		createTransaction(Trans);
		return account;

	}

	public Transaction createTransaction(Transaction transaction) {
		Transaction newTransaction = transactionRepository.save(transaction);
		return newTransaction;
		
	}

	@Override
	public Account withdraw(long accNo, double amount) throws InsufficientAccBalanceException {
		Account account = accountRepository.findByAccNo(accNo);
		account.withdraw(amount);
		accountRepository.save(account);
		
		Transaction trans = new Transaction();
		trans.setTransactionAmount(amount);
		trans.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
		trans.setTransactionType(withdraw);
		trans.setAccount(account);
		createTransaction(trans);
		return account;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	public Transaction dtoTotransaction(TransactionDto transactionDto) {
		Transaction transaction = this.modelMapper.map(transactionDto, Transaction.class);
		return transaction;
	}
	
	public TransactionDto transactionToDto(Transaction transaction) {
		TransactionDto transactionDto = this.modelMapper.map(transaction, TransactionDto.class);
		return transactionDto;
	}
	
	public Account dtoToaccount(AccountDto accountDto) {
		Account account = this.modelMapper.map(accountDto, Account.class);
		return account;
	}
	
	public AccountDto accountToDto(Account account) {
		AccountDto accountDto = this.modelMapper.map(account, AccountDto.class);
		return accountDto;
	}

//	@Override
//	public Account deleteAccount(Account accNo) {
//		Account account = accountRepository.findByAccNo(accNo);
//		accountRepository.delete(accNo);
//		return account;
//	}

	@Override
    public User deleteUser(long userId ) {
    User user =userRepository.findByUserId(userId);    
    List<Account> accounts = user.getAccounts();
    for (Account account : accounts) {
        List<Transaction> transactions = account.getTransactions();
        for(Transaction transaction : transactions) {
            transactionRepository.delete(transaction);
        }
        accountRepository.delete(account);
    }
    userRepository.delete(user);
        return user;
        
	}

}
