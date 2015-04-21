package bank;

import java.util.ArrayList;

import main.FileManager;
import bank.security.AccountAddress;
import security.Password;

public class Savings extends Account{

	public Savings(Customer owner, double startingAmount, String pin, int nthAccount){
		this.balance = startingAmount;
		this.pin = new Password(pin);
		this.owner = owner;
		this.transactions = new ArrayList<Transaction>();
		this.accountNumber = new AccountAddress(owner.personNumber.get("bank"), owner.personNumber.get("customer"), AccountAddress.format(nthAccount));
		FileManager.createTransactionstxt(this);
	}
	
	public String toString(){
		String retVal = ("Savings:" + this.accountNumber.toString() + "," + this.printBal() + "," + this.pin);
		return retVal;
	}
}
