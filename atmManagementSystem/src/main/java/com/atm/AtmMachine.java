package com.atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.dataBase.DBConnector;
import com.exception.BankException;
import com.exception.InvalidInputException;
/**
* program is implemented a class that has  the operations of the ATM. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class AtmMachine {
	private int atmId;
	private String bankName;
	private String branch;
	private double amount;
	public AtmMachine(int atmId, String bankName, String branch, double amount) {
		this.atmId = atmId;
		this.bankName = bankName;
		this.branch = branch;
		this.amount = amount;
	}
	public AtmMachine() {
		// TODO Auto-generated constructor stub
	}
	public int getAtmId() {
		return atmId;
	}
	public String getBankNmae() {
		return bankName;
	}
	public String getBranch() {
		return branch;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	LocalDate date;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	DBConnector db=new DBConnector();
	Transaction trans;
	AtmMachineDisplay ad=new AtmMachineDisplay();
	public void balanceEnquiry(Card card) {
		System.out.println("+----------------------------------------+");
		System.out.println("|          💰 Account Balance             |");
		System.out.println("+----------------------------------------+");
		System.out.println("| Card Number: xxxx xxxx xxxx " + card.getCardNumber() % 10000 + " |");
		System.out.println("| Holder Name: " + card.getHolder() + "                 |");
		System.out.println("| Available Balance: ₹" + card.getBalance() + "         |");
		System.out.println("+----------------------------------------+");
	}
	// Withdraw method
	public void withdraw(Card card,short type) {
		System.out.println("Enter the Amount ");
		try {
			double amt=Double.parseDouble(reader.readLine());
			double temp=amt;
			// transaction limit of a day
			if(amt+db.getTotalAmountOfDay(card.getCardNumber())<40000){
				//check for the amount available in the atm
				if(amt<amount) {
					//check the single transaction limit
					if(amt<10000) {
						//check the amount value is valid for atm or not
						if((amt%100)==0) {
							//check for overdraft limit
							if(amt<=card.getBalance()+((type==1)?0:10000)){
								amount-=amt;
								card.setBalance(card.getBalance()-amt);
								db.updatebalance(card);
								date=LocalDate.now();
								String sdate= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
								LocalTime time = LocalTime.now();  
							    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
							    String ftime = time.format(formatter);
								trans=new Transaction(db.getTransId()+1,card.getCardNumber(),sdate,ftime,amt,"Debited",0);
								db.putTransaction(trans);
								db.updateATM(amount,atmId);
								short five=(short) (amt/500);
								amt%=500;
								short two=(short) (amt/200);
								amt%=200;
								short one=(short) (amt/100);
								System.out.println("Kindly Take Your Amount ");
								if(five!=0) {
									if(two!=0&&one!=0) {
										System.out.println("Five Hundred Notes - "+five);
										System.out.println("Two  Hundred Notes - "+two);
										System.out.println("One  Hundred Notes - "+one);
									}else if(two!=0) {
										System.out.println("Five Hundred Notes - "+five);
										System.out.println("One  Hundred Notes - "+one);
									}else {
										System.out.println("Five Hundred Notes - "+five);
									}
								}else if(two!=0&&one!=0) {
									System.out.println("Two  Hundred Notes - "+two);
									System.out.println("One  Hundred Notes - "+one);
								}else if(two==0) {
									System.out.println("One  Hundred Notes - "+one);
								}else {
									System.out.println("Two  Hundred Notes - "+two);
								}
								}
							else {
								throw new BankException("Insufficient Balance : "); 
							}
						}else {
							throw new BankException("Enter the Valid Amount (eg.100,500,1000)");
						}
					}else {
						throw new BankException("Withdraw Limit is 10000 Please Ensure Your Amount is between it");
					}
				}else{
					throw new BankException("Insufficient Balance in ATM");
				}
			}else {
				throw new BankException("You'r trying to cross the daily limit . Please contact the bank for further Information");
			}
			System.out.print("Do you Want Receipt (y-Yes/n-NO) : ");
			char recept=reader.readLine().charAt(0);
			if(recept=='y') {
				System.out.println("+----------------------------------------+");
				System.out.println("|          💳 " + bankName + " ATM              |");
				System.out.println("+----------------------------------------+");
				System.out.println("|  Account Number  : XXXX XXXX XXXX " + card.getCardNumber() % 10000 + " |");
				System.out.println("|  Amount          : ₹" + temp + "                 |");
				System.out.println("|  Date            : " + date + "           |");
				System.out.println("|  Transaction Type: Withdraw            |");
				System.out.println("|  Current Balance : ₹" + card.getBalance() + "               |");
				System.out.println("| ************** THANK YOU ************** |");
				System.out.println("+----------------------------------------+");

			}
		} catch (NumberFormatException e) {
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,card);
		} catch (IOException e) {
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,card);
		} catch (BankException e) {
			System.out.println(e.getMessage());
			ad.back(atmId,card);
		}
	}
	// method to do deposite operation
	public void deposite(Card card) {
		System.out.println("Enter the Amount :");
		try {
			double amt=Double.parseDouble(reader.readLine());
			amount+=amt;
			card.setBalance(card.getBalance()+amt);
			db.updatebalance(card);
			date=LocalDate.now();
			String sdate= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 LocalTime time = LocalTime.now();  
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		     String ftime = time.format(formatter);
			trans=new Transaction(db.getTransId()+1,card.getCardNumber(),sdate,ftime,amt,"Credited",0);
			db.putTransaction(trans);
			db.updateATM(amount,atmId);
			System.out.println("Amount Deposited Successfully");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,card);
			}
		}
	//method for mini statement
	@SuppressWarnings("unchecked")
	public void miniStatement(Card card) {
		try {
			ArrayList<Transaction> list;
			list=db.miniStatement(card);
			Collections.sort(list, new Transaction());//sort(list);
			System.out.println("****************************************************************************************************");
			System.out.println("*                                        MINI STATEMENT                                            *");
			System.out.println("****************************************************************************************************");
			System.out.println("TransactionId  |   SenderNumber   |    Date    |   Time     |  Amount   |    Type    |     ReceiverNumber     ");
			list.stream().forEach(System.out::println);
			System.out.println("****************************************************************************************************");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block1
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,card);
		}
	}
	// method to change pin
	public void pinChange(Card card) {
		 Random random = new Random();
	        // Generate a random integer
	        int otp =Math.abs(random.nextInt() % 1000000);
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notification.txt"))) {
	            writer.write(String.valueOf(otp));
	            System.out.println("Pin reseting OTP has send to your registred phone number ..");
	        } catch (IOException e) {
	            System.err.println("Error writing OTP to file: " + e.getMessage());
	        }
	        try {
	        	System.out.println("Enter the OTP : ");
				int otp1=Integer.parseInt(reader.readLine());
				if(otp==otp1) {
					System.out.println("Enter the new Pin : ");
					int pin1=Integer.parseInt(reader.readLine());
					System.out.println("Re-Enter the Enter the new Pin : ");
					int pin2=Integer.parseInt(reader.readLine());
					if(pin1==pin2) {
						card.setPin(pin2);
						db.pinReset(card);
					}
				}else {
					throw new InvalidInputException("Invalid OTP");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Please Enter a valid Input.");
				ad.back(atmId,card);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Please Enter a valid Input.");
				ad.back(atmId,card);
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());;
			}
	}
	public void fundtransfer(Card cardT,Card cardR){
		double amt;
		
		try {
			amt = Double.parseDouble(reader.readLine());
			if(amt+db.getTotalAmountOfDay(cardT.getCardNumber())<40000){
			if(amt<=cardT.getBalance()) {
				cardT.setBalance(cardT.getBalance()-amt);
				cardR.setBalance(cardR.getBalance()+amt);
				date=LocalDate.now();
				String sdate= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				 LocalTime time = LocalTime.now();  
			     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			     String ftime = time.format(formatter);
				trans=new Transaction(db.getTransId()+1,cardT.getCardNumber(),sdate,ftime,amt,"Debited",cardR.getCardNumber());
				db.putTransaction(trans);
				trans=new Transaction(db.getTransId()+1,cardR.getCardNumber(),sdate,ftime,amt,"Credited",cardT.getCardNumber());
				db.putTransaction(trans);
				db.updatebalance(cardT);
				db.updatebalance(cardR);
			}
			else {
				throw new BankException("Insufficient Balance : ");
			}
		}
		    else {
			throw new BankException("You'r trying to cross the daily limit . Please contact the bank for further Information");
		}
		}catch (NumberFormatException e) {
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,cardT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Please Enter a valid Input.");
			ad.back(atmId,cardT);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			ad.back(atmId,cardT);
		}
	}
	@Override
	public String toString() {
	    // Pad strings with spaces to achieve uniform column width
	    String formattedAtmId = String.format("%-6s", atmId);
	    String formattedBankName = String.format("%-12s", bankName);
	    String formattedBranch = String.format("%-12s", branch);
	    String formattedAmount = String.format("$%.2f", amount);

	    return String.format("%s | %s | %s | %s",
	            formattedAtmId, formattedBankName, formattedBranch, formattedAmount);
	}

}