package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.FileManager;
import bank.security.AccountAddress;
import security.Password;
import utilities.ModdedDate;

public class Savings extends Account{

	public Savings(Customer owner, double startingAmount, String pin, int nthAccount){
		this.balance = startingAmount;
		this.pin = new Password(pin);
		this.owner = owner;
		this.transactions = new ArrayList<Transaction>();
		this.accountNumber = new AccountAddress(owner.personNumber.get("bank"), owner.personNumber.get("customer"), AccountAddress.format(nthAccount));
	}
	
	public boolean readTransactions(){
		transactionsFile = new File("/banks/" + this.accountNumber.get("bank") + "/" + this.accountNumber.personNumToString() + "/" + this.accountNumber + "/" + "transactions.txt");
		try {
			Scanner transactionReader = new Scanner(transactionsFile).useDelimiter("[::|<|>|\n|\r]+");
			String typee = transactionReader.next();
			ModdedDate date = new ModdedDate(transactionReader.next());
			double start = transactionReader.nextDouble();
			double diff = transactionReader.nextDouble();
			double end = transactionReader.nextDouble();
			transactionReader.close();
			
			transactions.add(new Transaction(date, start, end));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public String toString(){
		String retVal = ("Savings:" + this.accountNumber.toString() + "," + this.printBal() + "," + this.pin);
		return retVal;
	}
}
