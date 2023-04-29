package com.cg.atm.service.Impl;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.atm.dto.AccountDto;
import com.cg.atm.dto.TransactionDto;
import com.cg.atm.entity.Account;
import com.cg.atm.entity.Transaction;
import com.cg.atm.exception.AccNoNotFoundException;
import com.cg.atm.exception.InsufficientAccBalanceException;
import com.cg.atm.repository.AccountRepository;
import com.cg.atm.repository.TransactionRepository;
import com.cg.atm.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public String transaction = "transaction";
	private String deposit = "deposit";
	private String withdraw = "withdrawal";
	
	public void TransactionService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Transactional
	@Override
	public void transferMoney(long senderAccNo, long receiverAccNo, double amount) throws AccNoNotFoundException {
		Account sender = accountRepository.findByAccNo(senderAccNo);
		System.out.println(sender.getAccBalance());
		
		Account receiver = accountRepository.findByAccNo(receiverAccNo);
		System.out.println(receiver.getAccBalance());
		
		if(sender.getAccBalance() < amount) {
			throw new InsufficientAccBalanceException();
		}
		
		double senderAccBalance = sender.getAccBalance() - (amount);
		double receiverAccBalance = receiver.getAccBalance() + (amount);
		
		sender.setAccBalance(senderAccBalance);
		accountRepository.save(sender);
		
		receiver.setAccBalance(receiverAccBalance);
		accountRepository.save(receiver);
		
		Transaction trans = new Transaction();
		trans.setTransactionAmount(amount);
		trans.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
		trans.setTransactionType(deposit);
		trans.setAccount(receiver);
		createTransaction(trans);
		
		Transaction trans1 = new Transaction();
		trans1.setTransactionAmount(amount);
		trans1.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
		trans1.setTransactionType(withdraw);
		trans1.setAccount(sender);
		createTransaction(trans1);
	}

	@Override
	public void createTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
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
	
}
