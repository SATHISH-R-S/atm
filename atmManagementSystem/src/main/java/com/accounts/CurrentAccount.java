package com.accounts;
/**
* program is implemented a pojo class extends Account  that has the properties of Current Account . 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/

public class CurrentAccount extends Account {
	private float interestRate = 0.0f;
    private double overDraftLimit = 10000;
	public CurrentAccount(int customerID, String firstName, String lastName, String dob, String gender, String address,
			long phoneNumber, String email, int accountId, String holder, String type, String expDate,
			String createDate, long cardNumber, double balance,String status, int pin)  {
		super(customerID, firstName, lastName, dob, gender, address, phoneNumber, email, accountId, holder, type,
				expDate, createDate, cardNumber, balance,status, pin);
	}
	public float getInterestRate() {
		return interestRate;
	}
	public double getOverDraftLimit() {
		return overDraftLimit;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
	

	
	   

   
	//method to put in db
		
}
    

