package com.cg.atm.dto;

public class AccountDto {
	
	private long accNo;
	private double accBalance;
	private double amount;
	private long senderAccNo;
	private long receiverAccNo;
	
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
	public long getSenderAccNo() {
		return senderAccNo;
	}
	public void setSenderAccNo(long senderAccNo) {
		this.senderAccNo = senderAccNo;
	}
	public long getReceiverAccNo() {
		return receiverAccNo;
	}
	public void setReceiverAccNo(long receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	
}
