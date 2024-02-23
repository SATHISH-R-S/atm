package com.emoployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.atm.AtmMachine;
import com.atm.ChooseAtm;
import com.dataBase.DBConnector;
import com.exception.InvalidInputException;
public class AtmMaintainer extends Employee{
	public AtmMaintainer(String empID, String firstName, String lastName, String dob, String job, double salary,
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
					MaintainerMenu();
					break;
				case 2:
					UserIdentification.main(null);
					break;
				default:
					throw new InvalidInputException("Enter the valid Input..!");
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please Enter a valid Option.");
			UserIdentification.main(null);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public void MaintainerMenu() {
		DBConnector db=new DBConnector();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
				ChooseAtm ca=new ChooseAtm();
				short option=(short) ca.chooseAtm();	
				AtmMachine atm =db.getAtm(option);
				System.out.println("+----------------------------------------+");
				System.out.println("|      ✨ Choose The Option              |");
				System.out.println("+----------------------------------------+");
				System.out.println("|   1. 👁️ View Balance                   |");
				System.out.println("|   2. 🔄 Upload Balance                 |");
				System.out.println("|   3. 🚪 Exit                           |");
				System.out.println("+----------------------------------------+");
				System.out.print("Your Choice : ");
				short option1=Short.parseShort(reader.readLine());
				if(option1==1) {

					System.out.println("+----------------------------------------+");
					System.out.println("|  Available Amount in the ATM :"+atm.getAmount()+"|");
					System.out.println("+----------------------------------------+");
					back();
				}else if(option1==2) {
					System.out.print("Enter the amount to be uploaded");
					long amount=Long.parseLong(reader.readLine());
					db.updateATM(amount, option);
					back();
				}else if(option1==3){
					UserIdentification.main(null);
				}else {
					throw new InvalidInputException("Enter the valid Input..!");
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
