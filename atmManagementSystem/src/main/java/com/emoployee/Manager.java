package com.emoployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.accounts.NewAccount;
import com.atm.AtmMachine;
import com.atm.Transaction;
import com.dataBase.DBConnector;
import com.exception.InvalidInputException;
public class Manager extends Employee{
	public Manager(String empID, String firstName, String lastName, String dob, String job, double salary, String hireDate,
			String email, String password, String address, int bankID) {
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
					managerMenu();
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
	public void managerMenu() {
		DBConnector db=new DBConnector();
		NewAccount nc=new NewAccount();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("+------------------------------------------+");
		System.out.println("|                                          |");
		System.out.println("|        🛠️ Choose The Operation           |");
		System.out.println("+------------------------------------------+");
		System.out.println("|   1. 🌐 Create New Account               |");
		System.out.println("|   2. 🔄 Update Interest                  |");
		System.out.println("|   3. 🔒 Activate/Deactivate Account      |");
		System.out.println("|   4. 💳 Check Balance In ATM             |");
		System.out.println("|   5. 🏡 Change Address of an Account     |");
		System.out.println("|   6. 📞 Change Phone Number of an Account|");
		System.out.println("|   7. 🔐 Change PIN                       |");
		System.out.println("|   8. 📚 View Transaction                 |");
		System.out.println("|   9. 📚 View Transaction of an Account   |");
		System.out.println("|   10.🚪 Exit                             |");
		System.out.println("+------------------------------------------+");
		System.out.print("Your Choice : ");

		try {
			short option=Short.parseShort(reader.readLine());
			switch(option) {
			case 1:
				nc.newCustomerAccount();
				back();
				break;
			case 2:
				db.updateInterest();
				back();
				break;
			case 3:
				System.out.print("Enter card number :");
				long cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter status eg(Active/Inactive) : ");
				String status=reader.readLine();
				db.updateStatus(cardNumber, status);
				back();
				break;
			case 4:
				ArrayList<AtmMachine> atm=db.getAtmDetails();
				System.out.println("**************************************************");
				System.out.println("ATM_ID |  Bank_ID     |      Branch      | Amount ");
				System.out.println("**************************************************");
				atm.stream().forEach(System.out::println);
				System.out.println("**************************************************");
				back();
				break;
			case 5:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter the new Address : ");
				String address=reader.readLine();
				db.updateAddress(cardNumber, address);
				back();
				break;
			case 6:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter the new phoneNumber : "); 
				long phoneNumber = Long.parseLong(reader.readLine());
				db.updatePhoneNumber(cardNumber, phoneNumber);
				back();
				break;
			case 7:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
				System.out.print("Enter the new phoneNumber : "); 
				int pin=Integer.parseInt(reader.readLine());
				db.updatePhoneNumber(cardNumber, pin);
				back();
				break;
			case 8:
				ArrayList<Transaction> trans=db.getTransaction();
				System.out.println("********************************************************************************************************");
				System.out.println("*                                        TRANSACTION HISTORY                                           *");
				System.out.println("********************************************************************************************************");
				System.out.println("TransactionId  |   SenderNumber   |    Date    |   Time     |  Amount   |    Type    |     ReceiverNumber     ");
				trans.stream().forEach(System.out::println);
				System.out.println("********************************************************************************************************");
				back();
				break;
			case 9:
				System.out.print("Enter card number :");
			    cardNumber = Long.parseLong(reader.readLine());
			    ArrayList<Transaction> accTrans=db.getTransactionOfCustomer(cardNumber);
			    System.out.println("********************************************************************************************************");
				System.out.println("*                                        TRANSACTION HISTORY                                           *");
				System.out.println("********************************************************************************************************");
				System.out.println("TransactionId  |   SenderNumber   |    Date    |   Time     |  Amount   |    Type    |     ReceiverNumber     ");
			    Collections.sort(accTrans, new Transaction());
			    accTrans.stream().forEach(System.out::println);
			    System.out.println("*********************************************************************************************************");
			    back();
			    break;
			case 10:
				UserIdentification.main(null);
				break;
			default:
				throw new InvalidInputException("Enter the valid option..!");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			back();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			back();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			back();
		}

	}

}
