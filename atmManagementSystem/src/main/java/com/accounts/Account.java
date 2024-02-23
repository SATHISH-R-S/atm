package com.accounts;
/**
* program is implemented a pojo class extends Customer  that has the properties of Account details. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class Account extends Customer{
	private int accountId;
	private String holder;
	private String type;
	private String expDate;
	private String createDate;
	private long cardNumber;
	private double balance;
	private String status;
	private int pin;
		public Account(int customerID, String firstName, String lastName, String dob, String gender, String address,
	            long phoneNumber, String email, int accountId, String holder, String type, String expDate,
	            String createDate, long cardNumber, double balance, String status, int pin) {
	 super(customerID, firstName, lastName, dob, gender, address, phoneNumber, email);
	 this.accountId = accountId;
	 this.holder = holder;
	 this.type = type;
	 this.expDate = expDate;
	 this.createDate = createDate;
	 this.cardNumber = cardNumber;
	 this.balance = balance;
	 this.status = status;
	 this.pin = pin;
	} 
	public int getAccountId() {
		return accountId;
	}
	public String getHolder() {
		return holder;
	}
	public String getType() {
		return type;
	}
	public String getExpDate() {
		return expDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public double getBalance() {
		return balance;
	}
	public int getPin() {
		return pin;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}