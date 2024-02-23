package com.emoployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.accounts.NewAccount;
import com.atm.Transaction;
import com.dataBase.DBConnector;
import com.exception.InvalidInputException;

public class BankStaff extends Employee{

	public BankStaff(String empID, String firstName, String lastName, String dob, String job, double salary,
			String hireDate, String email, String password, String address, int bankID) {
		super(empID, firstName, lastName, dob, job, salary, hireDate, email, password, address, bankID);
	}
	public void back() {
		short option;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("+----------------------------------------+");
			System.out.println("|      ✨ Choose The Option              |");
			System.out.println("+----------------------------------------+");
			System.out.println("|   1. ➡️ Continue..                     |");
			System.out.println("|   2. 🚪 Exit                           |");
			System.out.println("+----------------------------------------+");
			System.out.print("Your Choice : ");
			option = Short.parseShort(reader.readLine());
			switch (option) {
				case 1:
					bankStaffMenu();
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
	@SuppressWarnings("unchecked")
	public void bankStaffMenu() {
		DBConnector db=new DBConnector();
		NewAccount nc=new NewAccount();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("+------------------------------------------+");
		System.out.println("|                                          |");
		System.out.println("|        🛠️ Choose The Operation           |");
		System.out.println("+------------------------------------------+");
		System.out.println("|   1. 🌐 Create New Account               |");
		System.out.println("|   2. 🏡 Change Address of an Account     |");
		System.out.println("|   3. 📞 Change Phone Number of an Account|");
		System.out.println("|   4. 📚 View Transaction                 |");
		System.out.println("|   5. 📚 View Transaction of an Account   |");
		System.out.println("|   6. 🚪 Exit                             |");
		System.out.println("+----------------------------------------+");
		System.out.print("Your Choice : ");

		try {
			short option=Short.parseShort(reader.readLine());
			switch(option) {
			case 1:
				nc.newCustomerAccount();
				back();
				break;
			case 2:
				System.out.print("Enter card number :");
			    long cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter the new Address : ");
				String address=reader.readLine();
				db.updateAddress(cardNumber, address);
				back();
				break;
			case 3:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter the new phoneNumber : "); 
				long phoneNumber = Long.parseLong(reader.readLine());
				db.updatePhoneNumber(cardNumber, phoneNumber);
				back();
				break;
			case 4:
				ArrayList<Transaction> trans = db.getTransaction();
				System.out.println("*****************************************TRANSACTION HISTORY********************************************");
				System.out.println("TransactionId  |     SenderNumber     |   date   |   Time  |  Amount  |   type   |     ReceiverNumber     ");
				trans.stream().forEach(System.out::println);
				back();
				break;
			case 5:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
			    ArrayList<Transaction> accTrans=db.getTransactionOfCustomer(cardNumber);
			    Collections.sort(accTrans, new Transaction());
			    System.out.println("*****************************************TRANSACTION HISTORY********************************************");
				System.out.println("TransactionId  |     SenderNumber     |   date   |   Time  |  Amount  |   type   |     ReceiverNumber     ");
			    accTrans.stream().forEach(System.out::println);
			    System.out.println("***************************************************************************************************");
			    back();
			    break;
			case 6:
				UserIdentification.main(null);
				break;
			default:
				throw new InvalidInputException("Enter the valid option..!");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			 back();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			 back();
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			 back();
		}
	}
}
