package com.atm;
/**
* program is implemented a pojo class that has the properties of card. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class Card {
	private long cardNumber=0;
	private String holder;
	private int pin=0;
	private double balance;
	public Card(long cardNumber,String holder, int pin, double balance) {
		this.cardNumber = cardNumber;
		this.holder=holder;
		this.pin = pin;
		this.balance = balance;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public int getPin() {
		return pin;
	}
	public double getBalance() {
		return balance;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getHolder() {
		return holder;
	}

	@Override
	public String toString() {
		return "Card [cardNumber=" + cardNumber + ", holder=" + holder + ", pin=" + pin + ", balance=" + balance + "]";
	}
	
}
