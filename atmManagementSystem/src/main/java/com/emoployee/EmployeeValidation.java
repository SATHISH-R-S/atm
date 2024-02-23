package com.emoployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.dataBase.DBConnector;
import com.exception.BankException;

public class EmployeeValidation {
	public void back() {
		short option;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("+----------------------------------------+");
			System.out.println("|          ✨ Choose The Option          |");
			System.out.println("+----------------------------------------+");
			System.out.println("|   1. 🔁 Try Again                      |");
			System.out.println("|   2. 🚪 Exit                           |");
			System.out.println("+----------------------------------------+");
			System.out.print("Your Choice : ");
			option = Short.parseShort(reader.readLine());
			switch (option) {
				case 1:
					loginPage();
					break;
				case 2:
					UserIdentification.main(null);
					break;
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please Enter a valid Option.");
			UserIdentification.main(null);
		}
	}
	public void loginPage() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DBConnector db=new DBConnector();
		short count=1;
		while(count<=3) {
		try {
			System.out.println("Enter the Employee Id :");
			String empdId=reader.readLine();
			System.out.println("Enter the Password :");
			String password=reader.readLine();
			Employee emp=db.getEmployee(empdId);
			if(password.equals(emp.getPassword())) {
				 if(emp.getJob().equals("Manager")){
					 Manager mag=(Manager) emp;
		        	   mag.managerMenu();
		           }else if(emp.getJob().equals("Bank Staff")) {
		        	 BankStaff staff= (BankStaff) emp;
		        	 staff.bankStaffMenu();
		           }else {
		        	 AtmMaintainer maintainer=(AtmMaintainer) emp;
		        	 maintainer.MaintainerMenu();
		           }
			}else {
	            throw new BankException("Invalid Password..!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Employee ID and Password Should be in Digits..!");
			back();
		} catch (IOException e) {
			System.out.println("Employee ID and Password Should be in Digits..!");
			back();
		} catch (BankException e) {
			System.out.println(e.getMessage());
			back();
		}
		}
	}

}
