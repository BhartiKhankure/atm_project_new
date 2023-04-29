package com.cg.atm.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name = "transaction_id")
	private long transactionId;

	@Column(name = "transaction_amount")
	private double transactionAmount;

	@Column(name = "transaction_date_time")
	private Timestamp transactionDateTime;

	@Column(name = "transaction_type")
	private String transactionType;

	@ManyToOne
	// @JsonManagedReference
	@JoinColumn(name = "accNo")
	private Account account;

	public Transaction() {

	}

	public Transaction(long transactionId, double transactionAmount, Timestamp transactionDateTime,
			String transactionType, Account account) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.transactionDateTime = transactionDateTime;
		this.transactionType = transactionType;
		this.account = account;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Timestamp getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
