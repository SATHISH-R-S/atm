package com.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.exception.InvalidInputException;
/**
* program is implemented a class that has the list of atm branches. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class ChooseAtm {
	public int chooseAtm(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("+----------------------------------------+");
		System.out.println("|        🏦 Choose The ATM Branch        |");
		System.out.println("+----------------------------------------+");
		System.out.println("|   1. 🌐 Main Branch ATM                |");
		System.out.println("|   2. 🌆 City Center ATM                |");
		System.out.println("|   3. 🏙️ Downtown ATM                   |");
		System.out.println("|   4. 🏡 Suburb Branch ATM              |");
		System.out.println("|   5. 🌄 North Branch ATM               |");
		System.out.println("+----------------------------------------+");
		try {
			System.out.print("Your Choice : ");
			short option=Short.parseShort(reader.readLine());
			
			switch(option) {
			case 1:
				return 111;
			case 2:
				return 112;
			case 3:
				return 113;
			case 4:
				return 114;
			case 5:
				return 115;
			default:
				throw new InvalidInputException("Invalid option");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
			UserIdentification.main(null);
		} catch (IOException e) {
			System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
			UserIdentification.main(null);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			UserIdentification.main(null);
		}
		return 0;
	}
}
