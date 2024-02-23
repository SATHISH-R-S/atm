package com.dataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import com.accounts.*;
import com.atm.AtmMachine;
import com.atm.Card;
import com.atm.Transaction;
import com.emoployee.AtmMaintainer;
import com.emoployee.BankStaff;
import com.emoployee.Employee;
import com.emoployee.Manager;
import com.exception.BankException;
/**
* program is implemented a class that has  All the data base connection . 
* 
*
* @author Sathish R S (Expleo)
* @since 20 feb 2024
*/
public class DBConnector {

		 final String URL="jdbc:oracle:thin:@localhost:1521:xe";
		 final String userName="SYSTEM";
		 final String password="sathish1718";
		 Connection con;
		 public DBConnector() {
			 try {
				 Class.forName("oracle.jdbc.driver.OracleDriver");
				 con=DriverManager.getConnection(URL,userName,password);
			 }catch(Exception e) {
					System.out.println(e.getMessage());
			 }
		 }
		 private final String CARD="SELECT card_Number,HolderName,PIN,Balance FROM ATM.ACCOUNT WHERE card_Number=?";
		 public Card getCard(long cardNumber) throws BankException {
			 Card card = null;
			 try {
				PreparedStatement cardinfo=con.prepareStatement(CARD);
				cardinfo.setLong(1, cardNumber);
				ResultSet result=cardinfo.executeQuery();
				 if (result.next()) {
			            card = new Card(result.getLong(1), result.getString(2), result.getInt(3), result.getDouble(4));
			        } else {
			            throw new BankException("Invalid Account Number ..!\nPlease Try Again ..!");
			        }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return card;
		 }
		 private final String UPDATE_BALANCE="UPDATE ATM.ACCOUNT SET Balance = ? WHERE card_Number = ?";
		 public void updatebalance(Card card) {
			 try (PreparedStatement cardinfo = con.prepareStatement(UPDATE_BALANCE)) {
				 cardinfo.setDouble(1, card.getBalance());
				 cardinfo.setLong(2, card.getCardNumber());
				 cardinfo.execute();
			 } catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			 
		 }
		 private final String INSERT_TRANSACTION="INSERT INTO ATM.TRANSACTION VALUES (?, ?,  TO_DATE(?, 'YYYY-MM-DD'),?,?,?, ?)";
		 public void putTransaction(Transaction trans){
			 try (PreparedStatement transinfo = con.prepareStatement(INSERT_TRANSACTION)) {
				 transinfo.setInt(1,trans.getTransactionId());
				 transinfo.setLong(2,trans.getSenderNumber());
				 transinfo.setString(3,trans.getDate());
				 transinfo.setString(4, trans.getTime());
				 transinfo.setDouble(5,trans.getAmount());
				 transinfo.setString(6,trans.getType());
				 transinfo.setLong(7,trans.getReceiverNumber());
				 transinfo.execute();
			 }catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			 
			 }
		 private final String GET_T_ID="SELECT MAX(TRANSACTION_ID) FROM ATM.TRANSACTION";
		 public int getTransId() {
			 int transId=0;
				try {
					Statement stat=con.createStatement();
					ResultSet result=stat.executeQuery(GET_T_ID);
					if(result.next()) {
						transId=result.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			return transId;
			 
		 }
		 public final String GET_MINI_STATEMENT="SELECT * FROM (SELECT * FROM ATM.TRANSACTION WHERE Sender_Number = ?"
		 		+" ORDER BY Transaction_DATE DESC) WHERE ROWNUM <= 5";
		@SuppressWarnings("rawtypes")
		public ArrayList miniStatement(Card card){
			 ArrayList<Transaction> list=new ArrayList<>();
			 try (PreparedStatement transinfo = con.prepareStatement(GET_MINI_STATEMENT)) {
					transinfo.setLong(1,card.getCardNumber());
					ResultSet result=transinfo.executeQuery();
					if(result!=null) {
					while(result.next()) {
						list.add(new Transaction(result.getInt(1),result.getLong(2),result.getDate(3).toString(),result.getString(4),result.getDouble(5),
								result.getString(6),result.getLong(7)));
					}
					}else {
						System.out.println("No transactions found..");
					}
			
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
			}
			// Collections.sort(list,new Transaction());
			return list;
		 }
		 public final String SET_PIN="UPDATE ATM.ACCOUNT SET PIN = ? WHERE card_Number = ?";
		 public void pinReset(Card card) {
			 try (PreparedStatement accinfo = con.prepareStatement(SET_PIN)) {
				 accinfo.setInt(1, card.getPin());
				 accinfo.setLong(2,card.getCardNumber());
				 accinfo.execute();
				 System.out.println("PIN was Reseted Successfully");
				 
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		 }	 
		 public final String GET_ATM="SELECT ATM.ATM.ATM_ID, ATM.BANK.Bank_Name, ATM.ATM.Branch, ATM.ATM.Amount "
		 		+ "FROM ATM.ATM "
		 		+ "JOIN ATM.BANK ON ATM.ATM.Bank_ID = ATM.BANK.Bank_ID "
		 		+ "WHERE ATM.ATM.ATM_ID = ?";
		 public AtmMachine getAtm(int id) {
			 AtmMachine atm=null;
			 try (PreparedStatement atminfo = con.prepareStatement(GET_ATM)) {
				 atminfo.setInt(1,id);
					ResultSet result=atminfo.executeQuery();
				 if(result.next()) {
					 atm=new AtmMachine(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4));
				 }
				 
			 } catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return atm;
		 }
		 public final String GET_TYPE="SELECT AccountType FROM ATM.ACCOUNT WHERE card_Number=?";
		 public int getType(Card card) {
			 try (PreparedStatement typeinfo = con.prepareStatement(GET_ATM)) {
				 typeinfo.setLong(1,card.getCardNumber());
					ResultSet result=typeinfo.executeQuery();
				 if(result.next()) {
					if(result.getString(1).equals("Savings"))
						return 1;						
					else
						return 0;
				 }
				 
			 } catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return 0;
			 
		 }
		 public final String UPDATE_ATM="UPDATE ATM.ATM SET Amount = ? WHERE ATM_ID = ?";
		 public void updateATM(double amount,int id) {
			 try (PreparedStatement atminfo = con.prepareStatement(UPDATE_ATM)) {
				 atminfo.setDouble(1,amount);
				 atminfo.setLong(2,id);
				 atminfo.execute();
			 } catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			 
		 }
		 private final String GET_Employee="SELECT * FROM ATM.EMPLOYEE WHERE Employee_ID=?";
		 public Employee getEmployee(String id) throws BankException {
			 Employee employee=null;
			 try {
				PreparedStatement empinfo=con.prepareStatement(GET_Employee);
				empinfo.setString(1,id);
				ResultSet result=empinfo.executeQuery();
				 if (result.next()) {
						String empID = result.getString(1);
				        String firstName = result.getString(2);
				        String lastName = result.getString(3);
				        String dob = result.getString(4);
				        String job = result.getString(5);
				        double salary = result.getDouble(6);
				        String hireDate = result.getString(7);
				        String email = result.getString(8);
				        String password = result.getString(9);
				        String address = result.getString(10);
				        int bankID = result.getInt(11);
			           if(result.getString(5).equals("Manager")){
			        	    employee = new Manager(empID, firstName, lastName, dob, 
			        			   job, salary, hireDate, email, password, address, bankID);
			           }else if(result.getString(5).equals("Bank Staff")) {
			        	   employee=new BankStaff(empID, firstName, lastName, dob, 
			        			   job, salary, hireDate, email, password, address, bankID);
			           }else if(result.getString(5).equals("Atm Maintainer")) {
			        	   employee=new AtmMaintainer(empID, firstName, lastName, dob, 
			        			   job, salary, hireDate, email, password, address, bankID);
			           }
			        } else {
			            throw new BankException("Invalid Employee ID..!");
			        }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return employee;
		 }
		 private final String GET_C_ID="SELECT MAX(Customer_ID) FROM ATM.CUSTOMER";
		 public int getCustomerId() {
			 int custId=0;
				try {
					Statement stat=con.createStatement();
					ResultSet result=stat.executeQuery(GET_C_ID);
					if(result.next()) {
						custId=result.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			return custId;
			 
		 }
		 private final String GET_C_NUMBER="SELECT MAX(card_Number) FROM ATM.ACCOUNT";
		 public long getCardNumber() {
			 long cardNumber=0;
				try {
					Statement stat=con.createStatement();
					ResultSet result=stat.executeQuery(GET_C_NUMBER);
					if(result.next()) {
						cardNumber=result.getLong(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			return cardNumber;
			 
		 }
		 private String INSERT_CUSTOMER="INSERT INTO ATM.CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		 public void putNewCustomer(Customer acc) {
			 int customerID = acc.getCustomerID();
		        String firstName = acc.getFirstName();
		        String lastName = acc.getLastName();
		        Date dateOfBirth = Date.valueOf(acc.getDob());
		        String address = acc.getAddress();
		        String gender = acc.getGender();
		        long phoneNumber = acc.getPhoneNumber();
		        String email = acc.getEmail();
		        	try {
						PreparedStatement newCustomer=con.prepareStatement(INSERT_CUSTOMER);
						newCustomer.setInt(1, customerID);
						newCustomer.setString(2, firstName);
						newCustomer.setString(3, lastName);
						newCustomer.setDate(4, dateOfBirth);
						newCustomer.setString(5, address);
						newCustomer.setString(6, gender);
						newCustomer.setLong(7, phoneNumber);
						newCustomer.setString(8, email);
						newCustomer.execute();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}			 
		 }
		 private String INSERT_ACCOUNT= "INSERT INTO ATM.ACCOUNT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 public void putNewAccount(Account acc) {
			    int accountID = acc.getAccountId();
		        int customerID = acc.getCustomerID();
		        String holderName = acc.getHolder();
		        String accountType = acc.getType();
		        Date createdDate = Date.valueOf(acc.getCreateDate());
		        Date expireDate = Date.valueOf(acc.getExpDate());
		        double interestRate ;
		        double overdraftLimit ;
		        if(acc.getType().equals("Savings")) {
		        	interestRate = 0.06;
			        overdraftLimit = 0;
		        }else {
		        	interestRate = 0.00;
			        overdraftLimit = 10000;
		        }
		        double balance = acc.getBalance();
		        int bankID = 11001;
		      //  long cardNumber = acc.getCardNumber();
		        int pin =acc.getPin();
		        String status = "Active";
			 try {
				PreparedStatement newAccount=con.prepareStatement(INSERT_ACCOUNT);
				newAccount.setInt(1, accountID);
				newAccount.setInt(2, customerID);
				newAccount.setString(3, holderName);
				newAccount.setString(4, accountType);
				newAccount.setDate(5, createdDate);
				newAccount.setDate(6, expireDate);
				newAccount.setDouble(7, interestRate);
				newAccount.setDouble(8, overdraftLimit);
				newAccount.setDouble(9, balance);
				newAccount.setInt(10, bankID);
				newAccount.setLong(11, acc.getCardNumber());
				newAccount.setInt(12, pin);
				newAccount.setString(13, status);
				newAccount.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		 }
		 public final String UPDATE_INTEREST="UPDATE ATM.ACCOUNT SET Balance = Balance * (1 + Interest_Rate) WHERE Bank_ID = 1"
		 		+ "AND Status = 'Active'";
		 public void updateInterest() {
				try {
					Statement stat=con.createStatement();
					stat.execute(UPDATE_INTEREST);
					System.out.println("Interest Updated Successfully for All the Savings Account");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
		 }
		 public final String UPDATE_STATUS="UPDATE ATM.ACCOUNT SET Status = ? WHERE CARD_NUMBER = ?";
		 public void updateStatus(long cardNumber,String status) {
			 try {
					PreparedStatement update=con.prepareStatement(UPDATE_STATUS);
					update.setString(1, status);
					update.setLong(2, cardNumber);
					update.execute();
					System.out.println("Status updated Successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
		 }
		 private final String ATM_DETAILS="SELECT * FROM ATM.ATM WHERE Bank_ID=11001";
		 @SuppressWarnings("rawtypes")
		public ArrayList getAtmDetails() {
			 ArrayList<AtmMachine> atmList=new ArrayList<>();
			 
			try {
				Statement stat = con.createStatement();
				ResultSet result=stat.executeQuery(ATM_DETAILS);
				 while(result.next()) {
					 atmList.add(new AtmMachine(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4)));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return atmList;
				
		 }
		 public final String UPDATE_ADDRESS="UPDATE ATM.CUSTOMER SET Address = ? WHERE Customer_ID="
		 		+ "(SELECT Customer_ID FROM ATM.ACCOUNT WHERE CARD_NUMBER = ?)";
		 public void updateAddress(long cardNumber,String address) {
			 try {
					PreparedStatement update=con.prepareStatement(UPDATE_ADDRESS);
					update.setString(1, address);
					update.setLong(2, cardNumber);
					update.execute();
					System.out.println("Address updated Successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
		 }
		 public final String UPDATE_PHONE_NUMBER="UPDATE ATM.CUSTOMER SET PhoneNumber = ? WHERE Customer_ID="
			 		+ "(SELECT Customer_ID FROM ATM.ACCOUNT WHERE CARD_NUMBER = ?)";
		 public void updatePhoneNumber(long cardNumber,long phoneNumber) {
			 try {
					PreparedStatement update=con.prepareStatement(UPDATE_PHONE_NUMBER);
					update.setLong(1, phoneNumber);
					update.setLong(2, cardNumber);
					update.execute();
					System.out.println("PhoneNumber updated Successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
		 }
		 public final String CHANGE_PIN="UPDATE ATM.ACCOUNT SET PIN = ? WHERE card_Number = ?";
		 public void changePin(long cardNumber,int pin) {
			 try (PreparedStatement accinfo = con.prepareStatement(CHANGE_PIN)) {
				 accinfo.setInt(2, pin);
				 accinfo.setLong(1,cardNumber);
				 accinfo.execute();
				 System.out.println("PIN  changed Successfully");
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		 }
		 public final String GET_TRANSACTION="SELECT * FROM ATM.TRANSACTION";
		 @SuppressWarnings("rawtypes")
		public ArrayList getTransaction(){
			 ArrayList<Transaction> list=new ArrayList<>();
			 try  {
				 Statement stat = con.createStatement();
				 ResultSet result=stat.executeQuery(GET_TRANSACTION);
				 while(result.next()) {
					 list.add(new Transaction(result.getInt(1),result.getLong(2),result.getDate(3).toString(),result.getString(4),result.getDouble(5),
							 result.getString(6),result.getLong(7)));
				 }
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
			}
			return list;
		 }
		 public final String GET_TRANSACTION_OF_CUSTONER="SELECT * FROM ATM.TRANSACTION WHERE Sender_Number = ?";
		 @SuppressWarnings("rawtypes")
		public ArrayList getTransactionOfCustomer(long cardNumber){
			 ArrayList<Transaction> list=new ArrayList<>();
			 try (PreparedStatement transinfo = con.prepareStatement(GET_TRANSACTION_OF_CUSTONER)) {
					transinfo.setLong(1,cardNumber);
					ResultSet result=transinfo.executeQuery();
					while(result.next()) {
						list.add(new Transaction(result.getInt(1),result.getLong(2),result.getDate(3).toString(),result.getString(4),result.getDouble(5),
								result.getString(6),result.getLong(7)));
					}
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
			}
			return list;
		 }
		 public final String GET_STATUS="SELECT Status FROM ATM.ACCOUNT WHERE card_Number = ?";
		 public boolean getStatus(long cardNumber) {
			 try {
					PreparedStatement status=con.prepareStatement(GET_STATUS);
					status.setLong(1, cardNumber);
					ResultSet result=status.executeQuery();
					if(result.next()) {
						if(result.getString(1).equals("Active")) {
							return true;
						}else {
							return false;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			return false;
			 
		 }
		 public final String GET_TOTAL_OF_DAY="SELECT Amount FROM ATM.TRANSACTION WHERE Sender_Number = ? AND Transaction_DATE=?";
		 public double getTotalAmountOfDay(long cardNumber) {
				double amount=0;
			 try {
					PreparedStatement status=con.prepareStatement(GET_TOTAL_OF_DAY);
					LocalDate date=LocalDate.now();
					Date sqlDate = Date.valueOf(date);
					status.setLong(1, cardNumber);
					status.setDate(2, sqlDate);
					ResultSet result=status.executeQuery();
					while(result.next()) {
						amount+=result.getDouble(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			 System.out.println(amount);
			 
			return amount;
		 }
		 
}