package com.accounts;
/**
* program is implemented a pojo class that has the properties of customer. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class Customer {
	    private int customerID;
	    private String firstName;
	    private String lastName;
	    private String dob;
	    private String gender;
	    private String address;
	    private long phoneNumber;
	    private String email;
		public Customer(int customerID, String firstName, String lastName, String dob, String gender, String address,
				long phoneNumber, String email) {
			this.customerID = customerID;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dob = dob;
			this.gender = gender;
			this.address = address;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}
		public int getCustomerID() {
			return customerID;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public String getDob() {
			return dob;
		}
		public String getGender() {
			return gender;
		}
		public String getAddress() {
			return address;
		}
		public long getPhoneNumber() {
			return phoneNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
	    
   

}
