package com.ATM_management_System.atmManagementSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.atm.AtmMachineDisplay;
import com.atm.*;
import com.emoployee.EmployeeValidation;
import com.exception.*;
/**
* program is implemented a class that identify the user's type. 
* 
*
* @author Sathish R S (Expleo)
* @since 19 feb 2024
*/
public class UserIdentification 
{
	public static void main( String[] args ) 
    {
    	 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	 while(true) {
    		 System.out.println("+----------------------------------------+");
    		 try {		
    		 System.out.println("|     🌟 Welcome to the Bank Service 🌟  |");
    		 System.out.println("+----------------------------------------+");
    		 Thread.sleep(1000);
    		 System.out.println("|      💡 Empowering Your Banking        |");
    		 Thread.sleep(1000);
    		 System.out.println("|        Experience with Excellence!     |");
    		 Thread.sleep(1000);
    		 System.out.println("+----------------------------------------+");
    		 System.out.println("|         🤝 Choose the User             |");
    		 System.out.println("|   1. 🧑‍💼 Customer                       |");
    		 System.out.println("|   2. 👩‍💼 Employee                       |");
    		 System.out.println("|   3. 🚪 Exit                           |");
    		 System.out.println("+----------------------------------------+");
    		 } catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

    	 try {
    		 System.out.print("Your Choice : ");
			short option=Short.parseShort(reader.readLine());
			switch(option) {
			case 1:
				AtmMachineDisplay atmdisp=new AtmMachineDisplay();
				ChooseAtm atmoption=new ChooseAtm();
				atmdisp.authentication(atmoption.chooseAtm()); ///gets the atm id and send it to the authentication method
				break;
			case 2:
				EmployeeValidation emp=new EmployeeValidation();
				emp.loginPage();
				break;
			case 3:
				System.out.println("********THANKS FOR BANKING WITH US**********");
				System.out.println("*************HAVE A NICE DAY****************");
				System.exit(0);
				break;	
			default:
				throw new InvalidInput("Please Enter a Valid option");
			}
    	 } catch (NumberFormatException e) {
				System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
         } catch (IOException e) {
				System.out.println("Please Enter a valid Input. \uD83D\uDEA8");
         } catch (InvalidInput e) {
			System.out.println(e.getMessage());
		}
      }
    }
}
