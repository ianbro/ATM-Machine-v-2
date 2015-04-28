package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import main.ErrorManager;
import bank.security.AccountAddress;
import security.Password;
import utilities.ModdedDate;

public abstract class Account {

	protected double balance;
	protected Password pin;
	public AccountAddress accountNumber;
	protected Customer owner;
	protected ArrayList<Transaction> transactions;
	protected File transactionsFile;
	protected PrintWriter transactionsWriter;
	
	public void withdrawal(double amount, String pin){
		if(this.pin.toString().equals(pin)){
			this.balance -= amount;
			double bal = this.balance+amount;
			this.transactions.add(new Transaction(bal, this.balance));
		}
	}
	
	public void deposit(double amount){
		this.balance += amount;
		double bal = this.balance-amount;
		this.transactions.add(new Transaction(bal, this.balance));
	}
	
	public double getBal(){
		return this.balance;
	}
	
	public String printBal(){
		DecimalFormat balFormat = new DecimalFormat("##00.00");
		return balFormat.format(this.balance);
	}
	
	public Object[] setPin(String old, String pin, String confirm){
		if(old.equals(this.pin.toString())){
			if(pin.equals(confirm)){
				this.pin = new Password(pin);
				return new Object[]{true, "Pin set to " + this.pin};
			}
			else{
				return new Object[]{false, "Sorry, the new pin and the confirmation pin must be the same."};
			}
		}
		else{
			return new Object[]{false, "Sorry, that is not the correct pin."};
		}
	}
	
	public Customer getOwner(){
		return this.owner;
	}
	
	public boolean transfer(double amount, String pin, Account target){
		try{
			this.withdrawal(amount, pin);
			target.deposit(amount);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean checkPin(String pinToCheck){
		if(pinToCheck.equals(this.pin.toString())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void printTransactions(){
		transactionsFile = new File("src/banks/"+this.owner.personNumber.get("bank")+"/"+this.owner.personNumber.personNumToString()+"/" + this.accountNumber.toString() + "/transactions.txt");
		try {
			transactionsWriter = new PrintWriter(transactionsFile);
			for(Transaction i: this.transactions){
				transactionsWriter.println(i);
			}
			transactionsWriter.close();
			System.out.println("printing transactions");
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(transactionsFile);
		}
	}
	
	public boolean readTransactions(){
		transactionsFile = new File("src/banks/" + this.accountNumber.get("bank") + "/" + this.accountNumber.personNumToString() + "/" + this.accountNumber + "/" + "transactions.txt");
		try {
			Scanner transactionReader = new Scanner(transactionsFile).useDelimiter("[::|<|>|\n|\r]+");
			
			while(transactionReader.hasNext()){
				String typee = transactionReader.next();
				ModdedDate date = new ModdedDate(transactionReader.next());
				double start = transactionReader.nextDouble();
				double diff = transactionReader.nextDouble();
				double end = transactionReader.nextDouble();
				
				transactions.add(new Transaction(date, start, end));
				System.out.println("reading transactions for " + this.accountNumber);
			}
			transactionReader.close();
			return true;
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(transactionsFile);
			return false;
		}
	}
}
