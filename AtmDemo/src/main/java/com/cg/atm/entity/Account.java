package com.cg.atm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cg.atm.exception.InsufficientAccBalanceException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue
	@Column(name = "acc_no")
	private long accNo;
	
	@Column(name = "acc_balance")
	private double accBalance;
	
	@Transient
	//@Column(name = "amount")
	private double amount;
	
	@ManyToOne
	//@JsonManagedReference
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<Transaction> transactions = new ArrayList<>();

	public Account() {
		
	}

	public Account(long accNo, double accBalance, User user) {
		super();
		this.accNo = accNo;
		this.accBalance = accBalance;
		this.user = user;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void deposit(double amount) {
		accBalance += amount;
	}
	
	public void withdraw(double amount) throws InsufficientAccBalanceException {
		if(accBalance < amount) {
			throw new InsufficientAccBalanceException();
		}
		accBalance -= amount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

 }
