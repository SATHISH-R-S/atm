package com.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.dataBase.DBConnector;
import com.exception.BankException;
import com.exception.InvalidInputException;
import com.regularExpression.RegularExpression;
/**
* program is implemented a class that has the option of the atm machine. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class AtmMachineDisplay {
	private Card card;
    private short count;
	DBConnector db = new DBConnector();
   public AtmMachineDisplay() {
	   count=1;   // Initializes the validation count by 1
   }
   // for continue and exit option
	@SuppressWarnings("static-access")
	public void back(int id,Card card) {
		short option;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("+----------------------------------------+");
			System.out.println("|      ✨ Choose The Option               |");
			System.out.println("+----------------------------------------+");
			System.out.println("|   1. ➡️ Continue..                     |");
			System.out.println("|   2. 🚪 Exit                           |");
			System.out.println("+----------------------------------------+");
			System.out.print("Your Choice : ");
			option = Short.parseShort(reader.readLine());
			switch (option) {
				case 1:
					atmmenu(id,card);
					break;
				case 2:
					UserIdentification ui = new UserIdentification();
					ui.main(null);
					break;
				default:
					throw new InvalidInputException("Invalid option");
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
			UserIdentification.main(null);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			UserIdentification.main(null);
		}
	}
	//check the card and pin detail of the customer for further process
	public void authentication(int id) {
		
		RegularExpression regex = new RegularExpression();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Please Enter the Card Number :");
			long cardNumber = Long.parseLong(reader.readLine());
			System.out.println();
			if (!regex.isValidaccNumber(cardNumber)) {
				throw new BankException("Card Number Should be 16-digits..!");
			}else if(!db.getStatus(cardNumber)){ // check the Account is active or not
				throw new BankException("Your account has been blocked please contanct the Back for further Information");
			}else {
			card = db.getCard(cardNumber);
			System.out.print("Please Enter the PIN Number :");
			int pin=Integer.parseInt(reader.readLine());
			if (!(pin==card.getPin())) {
				throw new BankException("Invalid  PIN ..!\n Please Try Again..!");
			} else {
				atmmenu(id,card);
			}
			}
		    }catch (BankException e) {
				System.out.println(e.getMessage());
				count++;
				if(count==4) {
					System.out.println("Your Account has been Blocked Please try Again Later");
					UserIdentification.main(null);
				}
				else {
					authentication(id);
				}
		    }
			catch (NumberFormatException e) {
				System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
				authentication(id);
			} catch (IOException e) {
				System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
				authentication(id);
			}
	}
    // menu of the Atm
	public void atmmenu(int id,Card card) {
		AtmMachine atm =db.getAtm(id);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
				short type=(short) db.getType(card);
				System.out.println("+----------------------------------------+");
				System.out.println("|      💳  " + atm.getBankNmae() + " ATM              |");
				System.out.println("+----------------------------------------+");
				System.out.println("|      🌟 Welcome, " + card.getHolder() + "      |");
				System.out.println("|         Choose The Service             |");
				System.out.println("|   1. 💰 Balance Enquiry                |");
				System.out.println("|   2. 💵 Deposit                        |");
				System.out.println("|   3. 💸 Withdraw                       |");
				System.out.println("|   4. 📜 Mini Statement                 |");
				System.out.println("|   5. 🔄 Fund Transfer                  |");
				System.out.println("|   6. 🔐 PIN Reset                      |");
				System.out.println("|   7. 🚪 Exit                           |");
				System.out.println("+----------------------------------------+");
				System.out.print("Your Choice : ");
				short option = Short.parseShort(reader.readLine());
				switch (option) {
					case 1:
						atm.balanceEnquiry(card);
						back(id,card);
						break;
					case 2:
						atm.deposite(card);
						back(id,card);
						break;
					case 3:
						atm.withdraw(card,type);
						back(id,card);
						break;
					case 4:
						atm.miniStatement(card);
						back(id,card);
						break;
					case 5:
						System.out.println("Enter the Account Number of the Receiver");
						long receiverNumber = Long.parseLong(reader.readLine());
						Card card1 = db.getCard(receiverNumber);
						atm.fundtransfer(card, card1);
						back(id,card);
						break;
					case 6:
						atm.pinChange(card);
						back(id,card);
						break;
					case 7:
						UserIdentification.main(null);
						break;
					default:
						throw new BankException("Please Enter a Valid Option");
				}
		}catch (BankException e) {
			System.out.println(e.getMessage());
			count++;
			if(count==4) {
				System.out.println("Your Account has been Blocked Please try Again Later");
				UserIdentification.main(null);
			}
			else {
				back(id,card);
			}
			
		}catch (NumberFormatException e) {
			System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
			back(id,card);
		} catch (IOException e) {
			System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
			back(id,card);
		}
	}
}
