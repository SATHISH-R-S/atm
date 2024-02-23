package com.emoployee;
public class Employee {
	private String empID;
	private String firstName;
	private String lastName;
	private String dob;
	private String job;
	private double salary;
	private String hireDate;
	private String email;
	private String password;
	private String address;
	private int bankID;
	public Employee(String empID, String firstName, String lastName, String dob, String job, double salary,
			String hireDate, String email, String password, String address, int bankID) {
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.job = job;
		this.salary = salary;
		this.hireDate = hireDate;
		this.email = email;
		this.password = password;
		this.address = address;
		this.bankID = bankID;
	}
	public String getEmpID() {
		return empID;
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
	public String getJob() {
		return job;
	}
	public double getSalary() {
		return salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}
	public int getBankID() {
		return bankID;
	}
}
