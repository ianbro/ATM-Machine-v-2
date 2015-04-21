package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ErrorManager;

import main.FileManager;
import main.ModdedDate;
import bank.security.AccountAddress;
import security.Password;

public class Checking extends Account{

	public Checking(Customer owner, double startingAmount, String pin, int nthAccount){
		this.balance = startingAmount;
		this.pin = new Password(pin);
		this.owner = owner;
		this.transactions = new ArrayList<Transaction>();
		this.accountNumber = new AccountAddress(owner.personNumber.get("bank"), owner.personNumber.get("customer"), AccountAddress.format(nthAccount));
		FileManager.createTransactionstxt(this);
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
			
			transactions.add(new Transaction(date, start, diff, end, typee));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public String toString(){
		String retVal = ("Checking:" + this.accountNumber.toString() + "," + this.printBal() + "," + this.pin);
		return retVal;
	}
}
