package com.accounts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ATM_management_System.atmManagementSystem.UserIdentification;
import com.dataBase.DBConnector;
import com.exception.*;
import com.regularExpression.*;
/**
* program is implemented a  class   that has the properties of New Account Creation. 
* 
*
* @author Sathish R S (Expleo)
* @since 20 feb 2024
*/
public class NewAccount {
    private int customerID;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String address;
    private long phoneNumber;
    private String email;
	private int accountId;
	private String holder;
	private String type;
	private String expireDate;
	private String createdDate;
	private long cardNumber;
	private double balance;
	private String status="Active";
	private int pin;
	public void newCustomerAccount() {
	 RegularExpression regex = new RegularExpression();
     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     DBConnector db=new DBConnector();
     short count;
     System.out.println("Kindly Enter the Details in Correct format");
     count = 0;
     while (count <3) {
         try {
             System.out.print("Enter First Name (e.g., Sai): ");
             firstName = reader.readLine();
             if (regex.isValidName(firstName)) {
                 break;
             } else {
                 if (count < 2) {
                     throw new InvalidInputException("Please Enter a valid Name");
                 } else {
                     throw new InvalidInputException("Invalid Input. Please Try Later");
                 }
             }
         } catch (InvalidInputException | IOException e) {
             System.out.println(e.getMessage());
             if (count ==2) {
            	 UserIdentification.main(null);
             }
             count++;  // Increment count outside the if condition
         }
     }
     count = 0;
     while (count < 3) {
         try {
             System.out.print("Enter Last Name (e.g., prasanna): ");
             lastName = reader.readLine();
             if (regex.isValidName(lastName)) {
                 break;
             } else {
                 if (count < 2) {
                     throw new InvalidInputException("Please Enter a valid Name");
                 } else {
                     throw new InvalidInputException("Invalid Input. Please Try Later");
                 }
             }
         } catch (InvalidInputException | IOException e) {
             System.out.println(e.getMessage());
             if (count ==2) {
            	 UserIdentification.main(null);
             }
             count++;  // Increment count outside the if condition
         }
     }
     count = 0;
     while (count < 3) {
     	try {
         System.out.print("Enter Date of Birth (yyyy-MM-dd) : ");
          dob = reader.readLine();
          LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          break;
     	}
     catch (Exception e){
         System.out.println(" Invalid Date Formate Please Try Again "+e.getMessage());
         if (count ==2) {
         	System.out.println(" Please Try Later "+e.getMessage());
         	UserIdentification.main(null);
         }
         count++;  // Increment count outside the if condition
     }
     }
     count=0;
		     while (count < 3) {
		     	try {
		
		     		System.out.println("Choose the gender : ");
		     		System.out.println("1.Male");
		     		System.out.println("2.Female");
		     		System.out.println("3.others");
		     		int option= Integer.parseInt(reader.readLine());
		     		switch(option) {
		     		case 1:
		     			gender="Male";
		     			count=4;
		     			break;
		     		
		     		case 2:
		     			gender="Female";
		     			count=4;
		     			break;
		     		case 3:
		     			gender="others";
		     			count=4;
		     			break;
		            default:
		            	throw new InvalidInputException("Invalid Option. Please Try Later");
		         } 
		     }catch (InvalidInputException | IOException e) {
		     System.out.println(e.getMessage());
		     if (count ==2) {
		    	 UserIdentification.main(null);
		     }
		     count++;  // Increment count outside the if condition
		 }
	   }
		     count = 0;
	            while (count < 3) {
	           try {
	        	   System.out.print("Enter Address: ");
	        	   address = reader.readLine();
	            if(regex.isValidAddress(address)){
	            	break;
	            } else {
	                if (count < 2) {
	                    throw new InvalidInputException("Please Enter a valid Address");
	                } else {
	                    throw new InvalidInputException("Invalid Address. Please Try Later");
	                }
	            }
	          } catch (InvalidInputException | IOException e) {
	            System.out.println(e.getMessage());
	            if (count ==2) {
	            	UserIdentification.main(null);
	            }
	            count++;  // Increment count outside the if condition
	        }
	        } 
	            count = 0;
	            while (count < 3) {
		        try {
		            System.out.print("Enter Phone Number: ");
		            phoneNumber = Long.parseLong(reader.readLine());
		            if(regex.isValidNumber(phoneNumber)) {
		            	break;
		            } else {
		                if (count < 2) {
		                    throw new InvalidInputException("Please Enter a valid Phone Number");
		                } else {
		                    throw new InvalidInputException("Invalid Number. Please Try Later");
		                }
		            }
		          } catch (InvalidInputException | IOException e) {
		            System.out.println(e.getMessage());
		            if (count ==2) {
		            	UserIdentification.main(null);
		            }
		            count++;  // Increment count outside the if condition
		        }
		     
		     }
		     count = 0;
	           while (count < 3) {
	            	try {
	            System.out.print("Enter Email: ");
	            email = reader.readLine();
	            if(regex.isValidEmail(email)) {
	            	break;
	            } else {
	                if (count < 2) {
	                    throw new InvalidInputException("Please Enter a valid E-mail");
	                } else {
	                    throw new InvalidInputException("Invalid E-mail. Please Try Later");
	                }
	            }
	          } catch (InvalidInputException | IOException e) {
	            System.out.println(e.getMessage());
	            if (count ==2) {
	            	UserIdentification.main(null);
	            }
	            count++;  // Increment count outside the if condition
	        }
	        } 
	            holder=firstName+" "+lastName;
	            count = 0;
	            while (count < 3) {
	            	try {

	            		System.out.println("Choose Account type : ");
	            		System.out.println("1.Savings Account");
	            		System.out.println("2.Current Account");
	            		int option= Integer.parseInt(reader.readLine());;
	            		if(option==1) {
	            			type="Savings";
	            			count=4;
	            			break;
	            		}
	            		else if(option==2) {
	            			type="Current";
	            			count=4;
	            			break;
	            		}
	            		else if (count < 2) {
	                    throw new InvalidInputException("Please Enter a valid Option");
	                } else {
	                    throw new InvalidInputException("Invalid Option. Please Try Later");
	                }
	            }catch (InvalidInputException | IOException e) {
	            System.out.println(e.getMessage());
	            if (count ==2) {
	            	UserIdentification.main(null);
	            }
	            count++;  // Increment count outside the if condition
	        }
	        }
	            createdDate = LocalDate.now().toString();
	            expireDate = LocalDate.now().plusYears(10).toString();
	            count = 0;
	            while (count < 3) {
	            	try {

	            		System.out.println("you can create your Account with the Initial balance or \n"
	            				+ "With Zero balance by Entering Amount 0 as Input");
	            	            System.out.print("Enter Initial Balance : ");
	            	            String bal=reader.readLine();
	            	            if(regex.isValidAmount(bal)) {
	            	            balance = Double.parseDouble(bal);
	            	            break;
	            	            }
	            		      else if (count < 2) {
	    		                         throw new InvalidInputException("Please Enter a valid Amount ");
	    		             } else {
	    		                throw new InvalidInputException("Invalid Amount. Please Try Later");
	    		            }
	    		        }catch (InvalidInputException | IOException e) {
	    		        System.out.println(e.getMessage());
	    		        if (count ==2) {
	    		        	UserIdentification.main(null);
	    		        }
	    		        count++;  // Increment count outside the if condition
	    		    }
	    		    }
	            count = 0;
	            String pin1 = null;
	            String pin2 = null ;
	            while (count < 3) {
	            	try {
	            		   System.out.print("Enter the PIN: ");
	                       pin1 = reader.readLine();
	            if(regex.isValidPin(pin1)) {
	            	break;
	            } else {
	                if (count < 2) {
	                    throw new InvalidInputException("Please Enter a valid PIN");
	                } else {
	                    throw new InvalidInputException("Invalid PIN. Please Try Later");
	                }
	            }
	          } catch (InvalidInputException | IOException e) {
	            System.out.println(e.getMessage());
	            if (count ==2) {
	            	UserIdentification.main(null);
	            }
	            count++;  // Increment count outside the if condition
	        }
	        }
	            count = 0;
	            while (count < 3) {
	            	try {
	            		   System.out.print("Re-Enter the PIN: ");
	                       pin2 = reader.readLine();
	            if(regex.isValidPin(pin2)) {
	            	break;
	            } else {
	                if (count < 2) {
	                    throw new InvalidInputException("Please Enter a valid PIN");
	                } else {
	                    throw new InvalidInputException("Invalid PIN. Please Try Later");
	                }
	            }
	          } catch (InvalidInputException | IOException e) {
	            System.out.println(e.getMessage());
	            if (count ==2) {
	            	UserIdentification.main(null);
	            }
	            count++;  // Increment count outside the if condition
	        }
	        }try {
	            if(!(pin1.equals(pin2))) {
	            	throw new InvalidInputException("Invalid Match of PIN. Please Try Later");
	            }
	            else {
	            	pin= Integer.parseInt(pin1); 
	            	customerID=db.getCustomerId()+1;
	            	accountId=customerID;
	    	        cardNumber=db.getCardNumber()+1;
	    	        if (type.equals("Savings")) {
	    	        SavingsAccount savingsAccount = new SavingsAccount(customerID, firstName, lastName, dob, gender, address,
	    	                phoneNumber, email, accountId, holder, type, expireDate, createdDate, cardNumber, balance, status, pin);
	    	        db.putNewCustomer(savingsAccount);
	    	        db.putNewAccount(savingsAccount);
	    	        System.out.println("New Customer Added Successfully ");
	    	        System.out.println(" Card Number for the Customer : "+savingsAccount.getCardNumber());
	    	        }else {
	    	        	CurrentAccount currentAccount = new CurrentAccount(customerID, firstName, lastName, dob, gender, address,
	    		                phoneNumber, email, accountId, holder, type, expireDate, createdDate, cardNumber, balance, status, pin);
	    	        	db.putNewCustomer(currentAccount);
	    		        db.putNewAccount(currentAccount);
	    		        System.out.println("New Customer Added Successfully ");
	    		        System.out.println(" Card Number for the Customer : "+currentAccount.getCardNumber());
	    	        }
	            }
	        }catch (InvalidInputException e) {
	            System.out.println(e.getMessage());
	            UserIdentification.main(null);
	        }
	        
	        
	
	}
	
}
