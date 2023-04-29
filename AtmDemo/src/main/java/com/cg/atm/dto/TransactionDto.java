package com.cg.atm.dto;

import java.sql.Timestamp;

public class TransactionDto {
	private long transactionId;
	private double transactionAmount;
	private Timestamp transactionDateTime;
	private String transactionType;
	
	public TransactionDto(long transactionId, double transactionAmount, Timestamp transactionDateTime,
			String transactionType) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.transactionDateTime = transactionDateTime;
		this.transactionType = transactionType;
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
	
}
