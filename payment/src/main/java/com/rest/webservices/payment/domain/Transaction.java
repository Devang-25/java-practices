package com.rest.webservices.payment.domain;

import java.util.Date;

public class Transaction {
	private int id;
	private int payerId;
	private int receiverId;
	private double amount;
	private Date transactionDate;
	private TransactionStatus status;
	public int getId() {
		return id;
	}
	public int getPayerId() {
		return payerId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public double getAmount() {
		return amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", payerId=" + payerId + ", receiverId=" + receiverId + ", amount=" + amount
				+ ", transactionDate=" + transactionDate + ", status=" + status + "]";
	}
	
	
}
