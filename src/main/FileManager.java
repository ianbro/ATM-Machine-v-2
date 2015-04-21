package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import bank.Account;
import bank.Bank;
import bank.Customer;

public abstract class FileManager {

	public static void createCustomerstxt(Bank target) {
		Path p = Paths.get("src/banks/"+target.getID().get("bank")+"/customers.txt");
		try {
			Files.createFile(p);
		} catch (IOException e) {
			ErrorManager.throwFileAlreadyExists(p);
		}
	}
	
	public static void createAccountstxt(Customer target){
		Path pathToFile = Paths.get("src/banks/"+target.parentBank.getID().get("bank") + "/" + target.personNumber.personNumToString() + "/accounts.txt");
		try {
			Files.createFile(pathToFile);
		} catch (IOException e) {
			ErrorManager.throwFileAlreadyExists(pathToFile);
		}
	}
	
	public static void createTransactionstxt(Account target){
		Path transactionPath = Paths.get("/banks/" + target.accountNumber.get("bank") + "/" + target.accountNumber.personNumToString() + "/" + target.accountNumber + "/" + "transactions.txt");
		try {
			Files.createFile(transactionPath);
		} catch (NoSuchFileException e) {
//			ErrorManager.throwFileAlreadyExists(transactionPath);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createBankDir(Bank target){
		new File("src/banks/"+target.getID().get("bank")).mkdir();
		createCustomerstxt(target);
	}
	
	public static void createCustomerDir(Customer target){
		new File("src/banks/"+target.parentBank.getID().get("bank")+"/"+target.personNumber.personNumToString()).mkdir();
		createAccountstxt(target);
	}
	
	public static void createAccountDir(Account target){
		new File("src/banks/"+target.getOwner().parentBank.getID().get("bank")+"/"+target.getOwner().personNumber.personNumToString()+"/"+target.accountNumber.toString()).mkdir();
	}
}
