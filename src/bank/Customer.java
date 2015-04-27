package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import main.ErrorManager;
import main.FileManager;
import main.ATM_Main;
import bank.security.AccountAddress;
import security.Password;

public class Customer {

	private String fullName;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String houseAddress;
	private Password password;
	public ArrayList<Account> accounts = new ArrayList<Account>();
	public AccountAddress personNumber;
	public Bank parentBank;
	public String userName;
	private File accountsFile;
	public Scanner accountsReader;
	public PrintWriter accountsWriter;
	private ArrayList<File> accountFiles = new ArrayList<File>();
	
	public Customer(Bank parent, String username, String name, String phone, String address, String pass){
		this.firstName = seperateName(name)[0];
		this.lastName = seperateName(name)[1];
		this.fullName = seperateName(name)[0]+" "+seperateName(name)[1];
		this.phoneNum = phone;
		personNumber = new AccountAddress(parent.getID().get("bank"), AccountAddress.format(parent.customers.size()+1));
		this.password = new Password(pass);
		this.houseAddress = address;
		this.parentBank = parent;
		this.userName = username;
	}
	
	public String getPhoneNumber(){
		return this.phoneNum;
	}
	
	public String getAddress(){
		return this.houseAddress;
	}
	
	public void openCustomerAccountsFile(){
		this.accountsFile = new File("src/bank/customerFiles/" + this.personNumber.personNumToString() + ".txt");
	}
	
	public Customer(Bank parent, String name){
		this.fullName = name;
		this.parentBank = parent;
		personNumber = new AccountAddress(parent.getID().get("bank"), AccountAddress.format(parent.customers.size()+1));
	}
	
	public String getName(){
		return this.fullName;
	}
	
	public String printName(){
		return this.firstName +" "+ this.lastName;
	}
	
	public static String[] seperateName(String name){
		for(int i = 0; i < name.length(); i++){
			if(name.charAt(i) == ' '){
				return new String[]{name.substring(0, i), name.substring(i+1)};
			}
		}
		return new String[]{name};
	}
	
	public static String formatName(String name){
		return seperateName(name)[0]+"_"+seperateName(name)[1];
	}
	
	public void addCheckingAccount(double startingAmount, String pin){
		Checking toAdd = new Checking(this, startingAmount, pin, this.accounts.size()+1);
		FileManager.createAccountDir(toAdd);
		FileManager.createTransactionstxt(toAdd);
		this.accounts.add(toAdd);
	}
	
	public void readCheckingAccount(double startingAmount, String pin){
		Checking toAdd = new Checking(this, startingAmount, pin, this.accounts.size()+1);
		FileManager.createAccountDir(toAdd);
		this.accounts.add(toAdd);
	}
	
	public void addSavingsAccount(double startingAmount, String pin){
		Savings toAdd = new Savings(this, startingAmount, pin, this.accounts.size()+1);
		FileManager.createAccountDir(toAdd);
		FileManager.createTransactionstxt(toAdd);
		this.accounts.add(toAdd);
	}
	
	public void readSavingsAccount(double startingAmount, String pin){
		Savings toAdd = new Savings(this, startingAmount, pin, this.accounts.size()+1);
		FileManager.createAccountDir(toAdd);
		this.accounts.add(toAdd);
	}
	
	public Password getPassword(){
		return this.password;
	}
	
	public Object[] resetPassword(String old, String newPass, String newAgain){
		if(old.equals(password.toString())){
			if(newAgain.equals(newPass)){
				password = new Password(newPass);
				return new Object[]{true,"Password reset to " + newPass};
			}
			else{
				return new Object[]{false, "Sorry, the two new passwords do not match eachother."};
			}
		}
		else {
			return new Object[]{false, "Sorry, original password not correct."};
		}
	}
	
	//userName,fullName,phone,address,password
	public String toString(){
		String accountsToString = "";
		if(this.accounts.size() == 0){
			accountsToString = "NONE";
		}
		for(int i = 0; i < this.accounts.size(); i ++){
			if(i > 0){
				accountsToString = accountsToString + ";";
			}
			accountsToString = accountsToString + this.accounts.get(i).toString();
		}
		return (this.userName+","+this.fullName+","+this.phoneNum+","+this.personNumber.personNumToString()+","+this.password);
	}
	
	
	
	
	
	//reading
	public static Customer convertToCustomer(Bank parent, String username, String name, String phone, String address, String pass){
		Customer toAdd = new Customer(parent, username, name, phone, address, pass);
		toAdd.readAccounts();
		return toAdd;
	}
	
	public void readAccounts(){
		accountsFile = new File("src/banks/"+this.personNumber.get("bank")+"/"+this.personNumber.personNumToString()+"/accounts.txt");
		try {
			accountsReader = new Scanner(accountsFile).useDelimiter("[,|:|\n|\r]+");
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(accountsFile);
		}
		
		while(accountsReader.hasNext()){
			readNextAccount();
		}
		accountsReader.close();
	}
	
	public void readNextAccount(){
		String accountType = accountsReader.next();
		double startAmount = 0;
		String pin = null;
		if(accountType.equals("Checking")){
			String notUsed = accountsReader.next();
			startAmount = accountsReader.nextDouble();
			pin = accountsReader.next();
			this.readCheckingAccount(startAmount, pin);
		}
		else if(accountType.equals("Savings:")){
			String notUsed = accountsReader.next();
			startAmount = accountsReader.nextDouble();
			this.readSavingsAccount(startAmount, pin);
		}
	}
	
	
	
	
	
	//printing(start here. create method "printAccounts()")
	public void printAccounts(){
		accountsFile = new File("src/banks/"+this.personNumber.get("bank")+"/"+this.personNumber.personNumToString()+"/accounts.txt");
		try {
			accountsWriter = new PrintWriter(accountsFile);
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(accountsFile);
		}
		for(int i = 0; i < accounts.size(); i ++){
			this.accounts.get(i).printTransactions();
			accountsWriter.println(accounts.get(i).toString());
//																System.out.println(i.toString());
		}
		accountsWriter.close();
	}
	
	public Account get(String address){
		return this.accounts.get(Integer.valueOf(address)-1);
	}
}
