package com.atm;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class Transaction implements Comparator{
	private int transactionId;
	private long senderNumber;
	private String date;
	private String time;
	private double amount;
	private String type;
	private long receiverNumber;
	public Transaction(int transactionId, long senderNumber, String date,String time, double amount, String type,
			long receiverNumber) {
		this.transactionId = transactionId;
		this.senderNumber = senderNumber;
		this.date = date;
		this.time=time;
		this.amount = amount;
		this.type = type;
		this.receiverNumber = receiverNumber;
	}
	public Transaction() {
	}
	public int getTransactionId() {
		return transactionId;
	}
	public long getSenderNumber() {
		return senderNumber;
	}
	public String getDate() {
		return date;
	}
	public double getAmount() {
		return amount;
	}
	public String getType() {
		return type;
	}
	public long getReceiverNumber() {
		return receiverNumber;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public void setSenderNumber(long senderNumber) {
		this.senderNumber = senderNumber;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setReceiverNumber(long receiverNumber) {
		this.receiverNumber = receiverNumber;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
	    // Pad strings with spaces to achieve uniform column width
	    String formattedTransactionId = String.format("%-14s", transactionId);
	    String formattedSenderNumber = String.format("%-12s", senderNumber);
	    String formattedDate = String.format("%-10s", date);
	    String formattedTime = String.format("%-10s", time);
	    String formattedAmount = String.format("₹%.2f ", amount);
	    String formattedType = String.format("%-12s", type);
	    String formattedReceiverNumber = String.format("%-12s", receiverNumber);

	    return String.format("%s | %s | %s | %s | %s | %s | %s",
	            formattedTransactionId, formattedSenderNumber, formattedDate,
	            formattedTime, formattedAmount, formattedType, formattedReceiverNumber);
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
	 return Integer.compare(((Transaction) o1).getTransactionId(),((Transaction) o2).getTransactionId());
	}
}
