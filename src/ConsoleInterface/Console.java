package ConsoleInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.ATM_Main;
import bank.Account;
import bank.Bank;
import bank.Checking;
import bank.Customer;
import bank.Transaction;
import bank.security.AccountAddress;

public abstract class Console {
	static Scanner kbReader;
	
	public static boolean promptForExit(){
		System.out.println("Exit? y/n");
		kbReader = new Scanner(System.in);
		String choice = kbReader.next();
		if(choice.equals("y")){
			System.out.println("Thank you. Have a good day.");
			ATM_Main.activeBank.activeCustomer = null;
			ATM_Main.activeBank = null;
			return false;
		}
		else if(choice.equals("n")){
			return true;
		}
		else {
			System.out.println("Please enter 'y' or 'n' for yes or no respectively.");
			promptForExit();
		}
		return false;
	}
	
	public static boolean promptForBank(){
		for(Bank b : ATM_Main.banks){
			System.out.print("	" + b.getName());
		}
		System.out.println("\n>>> Your Bank:");
		kbReader = new Scanner(System.in);
		String bank = kbReader.nextLine();
		if(bank.equals("P")){
			System.exit(0);
		}
		ATM_Main.login(bank);
		try {
			if(ATM_Main.activeBank.equals(null)){
				return false;
			} else{
				return true;
			}
		} catch(NullPointerException n){
			System.out.println("Could not find bank: "+bank);
			return false;
		}
	}
	
	public static boolean promptLogin(){
		if(promptForBank()){
		
			System.out.println("Login:");
			
			//user name
			System.out.println("User Name:");
			kbReader = new Scanner(System.in);
			String userName = kbReader.nextLine();
			
			//password
			System.out.println("Password:");
			kbReader = new Scanner(System.in);
			String password = kbReader.nextLine();
			
			ATM_Main.activeBank.login(userName, password);
			try {
				if(ATM_Main.activeBank.activeCustomer.equals(null)){
					return false;
				} else{
					return true;
				}
			} catch(NullPointerException n){
				System.out.println("Could not find account: "+userName+".");
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public static void showMainMenu(){
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(ATM_Main.activeBank.getName() + ": " + ATM_Main.activeBank.activeCustomer.getName());
		System.out.println("Menu:");
		System.out.println("Accounts (Type only the account number (000.000.000) to view it):");
		showAccounts();
		
		System.out.println("Transfer");
		System.out.println("Account Settings");
		System.out.println("Logout");
		
		System.out.println("Please type the option you would like");
		String choice = getChoice();
		runChoice(choice);
	}
	
	public static void showAccountMenu(Account a){
		System.out.println(a.getClass().getName().substring(5) + ": " + a.accountNumber.toString());
		showTransactions(a);
		System.out.println("Please select an option:");
		System.out.println("	Withdrawal\n	Deposit");
		String choice = getChoice();
		
		{
			if (choice.equals("Deposit")){
				System.out.println("Amount:");
				a.deposit(new Scanner(System.in).nextDouble());
			}
			else if (choice.equals("Withdrawal")){
				System.out.println("Pin: ");
				String pin = new Scanner(System.in).next();
				System.out.println("Amount: ");
				double amount = new Scanner(System.in).nextDouble();
				a.withdrawal(amount, pin);
			}
			else{
				System.out.println("Sorry, that is not an option.");
			}
		}
	}
	
	public static void showTransactions(Account a){
		for(Transaction t: a.getTransactions()){
			System.out.println("		" + t.getDate() + "............" + t.type + "............" + " $" + t.diff + ",    Balance: $" + t.endAmount);
		}
	}
	
	public static void showAccounts(){
		for(int i = 0; i < ATM_Main.activeBank.activeCustomer.accounts.size(); i ++){
			if(ConsoleMain.activeCustomer.accounts.get(i).getClass().equals(Checking.class)){
				System.out.println("  "+(i+1)+". Checking: "+ConsoleMain.activeCustomer.accounts.get(i).accountNumber);
			}
			else{
				System.out.println("  "+(i+1)+". Savings: "+ConsoleMain.activeCustomer.accounts.get(i).accountNumber);
			}
		}
	}
	
	public static String getChoice(){
		Scanner kbReader = new Scanner(System.in);
		String choice = kbReader.nextLine();
		return choice;
	}
	
	public static void runChoice(String choice){
		try{
			int i = Integer.valueOf(choice.substring(0, 3));
			try{
				Account a = ATM_Main.searchAccountByNumber(choice.substring(0, 3), choice.substring(4, 7), choice.substring(8, 11));
				showAccountMenu(a);
			} catch(Exception e){
				System.out.println("Sorry, the account "+choice+" does not exist.");
			}
		} catch(NumberFormatException e){
			switch(choice){
			case "Transfer":
				promptTransfer();
				break;
			case "Account Settings":
				showAccountSettingsMenu();
				break;
			case "Logout":
				ATM_Main.logout();
				ConsoleMain.loggedIn = false;
				break;
			default:
				System.out.println("Sorry, that is not an option. Try capitolizing the first letter of each word.");
				break;
			}
		} catch (StringIndexOutOfBoundsException s){
			System.out.println("Please enter a choice.");
		}
	}
	
	public static void showAccountSettingsMenu(){
		System.out.println("Please choose an option:\n	Change Password\n	Change Account Pin\n	Change User Name");
		kbReader = new Scanner(System.in);
		String choice;
		try {
			choice = kbReader.nextLine();
			switch (choice){
			case "Change Password":
				promptPasswordChange();
				break;
			case "Change Account Pin":
				promptPinChange();
				break;
			case "Change User Name":
				promptUserNameChange();
				break;
			default:
				System.out.println("Sorry, that is not an option. Try capitolizing the first letter of each word.");
				break;
			}
		} catch (StringIndexOutOfBoundsException s){
			System.out.println("Please enter a choice.");
		}
	}
	
	public static void promptUserNameChange(){
		System.out.println("New User Name:");
		kbReader = new Scanner(System.in);
		String toChange = kbReader.nextLine();
		if(toChange.contains(" ")){
			System.out.println("Sorry, User Name cannot contain spaces or commas.");
		}
		else {
			ATM_Main.activeBank.activeCustomer.userName = toChange;
			System.out.println("User Name set to " + ATM_Main.activeBank.activeCustomer.userName);
		}
	}
	
	public static void promptPinChange(){
		System.out.println("Please enter the address to the account:");
		kbReader = new Scanner(System.in).useDelimiter("[.|\n|\r]+");
		Account target = null;
		try{
			target = ATM_Main.searchAccountByNumber(kbReader.next(), kbReader.next(), kbReader.next());
		} catch(Exception e){
			System.out.println("Sorry, please enter the address in form '###.###.###'\nagain?");
			kbReader = new Scanner(System.in);
			if(kbReader.next().equalsIgnoreCase("yes")){
				promptPinChange();
			} else {
				return;
			}
		}
			
		System.out.println("Origional pin:");
		kbReader = new Scanner(System.in);
		String o = kbReader.next();
		
		System.out.println("New:");
		kbReader = new Scanner(System.in);
		String n = kbReader.next();
		
		System.out.println("Confirm:");
		kbReader = new Scanner(System.in);
		String c = kbReader.next();
		
		System.out.println(target.setPin(o, n, c)[1]);
	}
	
	public static void promptPasswordChange(){
		//original
		System.out.print("Original password: ");
		kbReader = new Scanner(System.in);
		String passGuess = kbReader.next();
		
		//new
		System.out.print("New password: ");
		kbReader = new Scanner(System.in);
		String newPass = kbReader.next();
		
		//confirm
		System.out.print("New password again: ");
		kbReader = new Scanner(System.in);
		String confirm = kbReader.next();
		
		Object[] changedPassword = ATM_Main.activeBank.activeCustomer.resetPassword(passGuess, newPass, confirm);
		System.out.println("\n" + changedPassword[1]);
	}
	
	public static void promptTransfer(){
		//getting from account
		System.out.println("Account (number only: ex. '000.000.000') to transfer from:");
		kbReader = new Scanner(System.in).useDelimiter("[.|\n|\r]");
		String s1 = kbReader.next();
		String s2 = kbReader.next();
		String s3 = kbReader.next();
		
		AccountAddress fromAddress = new AccountAddress(s1, s2, s3);
		Account from;
		
		try{
			from = ATM_Main.searchAccountByAddress(fromAddress);
		} catch (IndexOutOfBoundsException a){
			System.out.println("Sorry, could not find account with address: " + fromAddress.toString());
			return;
		} catch (NumberFormatException n){
			System.out.println("Sorry, Please type the address of the account in form (000.000.000).");
			n.printStackTrace();
			return;
		}
		
		//getting to account
		System.out.println("Account (number only: ex. '000.000.000') to transfer to:");
		kbReader = new Scanner(System.in).useDelimiter("[.|\n|\r]");
		AccountAddress address = new AccountAddress(kbReader.next(), kbReader.next(), kbReader.next());
		Account target;
		
		try{
			target = ATM_Main.searchAccountByAddress(address);
		} catch (IndexOutOfBoundsException a){
			System.out.println("Sorry, could not find account with address: " + address.toString());
			return;
		} catch (NumberFormatException n){
			System.out.println("Sorry, Please type the address of the account in form (000.000.000).");
			n.printStackTrace();
			return;
		}
		
		//getting your pin
		System.out.println("Your pin:");
		kbReader = new Scanner(System.in);
		String pin = kbReader.next();
		double amount;
		
		if(target.checkPin(pin)){
			//getting amount to transfer
			System.out.println("Amount to transfer: ");
			kbReader = new Scanner(System.in).useDelimiter("[$|\n|\r]");
			try {
				amount = kbReader.nextDouble();
				if(amount < 0){
					System.out.println("Sorry, you cannont transfer negative dollars.");
				}
				else {
					//transfering
					from.transfer(amount, pin, target);
				}
			} catch (InputMismatchException i) {
				System.out.println("Sorry, please enter an amount in form (0.00).");
			}
		}
		else {
			System.out.println("Sorry, that is not the correct password.");
		}
	}
}
