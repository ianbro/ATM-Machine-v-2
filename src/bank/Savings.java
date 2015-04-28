package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public String toString(){
		String retVal = ("Savings:" + this.accountNumber.toString() + "," + this.printBal() + "," + this.pin);
		return retVal;
	}
}
