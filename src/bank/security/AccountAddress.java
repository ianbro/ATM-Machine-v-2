package bank.security;

import java.util.ArrayList;

import main.ATM_Main;
//import bank.Account;
//import bank.Checking;
//import bank.Customer;

public class AccountAddress {

	private ArrayList<String> value;
	
	public AccountAddress(String bank){
		this.value = new ArrayList<String>();
		this.value.add(bank);
	}
	public AccountAddress(String bank, String person){
		this.value = new ArrayList<String>();
		this.value.add(bank);
		this.value.add(person);
	}
	
	public AccountAddress(String bank, String owner, String account){
		this.value = new ArrayList<String>();
		this.value.add(bank);
		this.value.add(owner);
		this.value.add(account);
	}
	
	public String get(String part){
		try{
			switch(part){
			case "bank":
				return this.value.get(0);
			case "customer":
				return this.value.get(1);
			case "account":
				return this.value.get(2);
			default:
				return "---";
			}
		}
		catch (Exception e) {
			return "eee";
		}
	}
	
	public static String format(int n){
		String toAdd;
		if(n < 10){
			toAdd = "00"+n;
		}
		else if(n >= 10 && n < 100){
			toAdd = "0" + n;
		}
		else{
			toAdd = String.valueOf(n);
		}
		return toAdd;
	}
	
	public boolean equalsAccount(AccountAddress other){
		if(this.get("bank").equals(other.get("bank"))){
			if(this.get("customer").equals(other.get("customer"))){
				if(this.get("account").equals(other.get("account"))){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean equalsCustomer(AccountAddress other){
		if(this.get("bank").equals(other.get("bank"))){
			if(this.get("customer").equals(other.get("customer"))){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean equalsBank(AccountAddress other){
		if(this.get("bank").equals(other.get("bank"))){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String toString(){
		return (this.value.get(0)+"."+this.value.get(1)+"."+this.value.get(2));
	}
	
	public String personNumToString(){
		return (this.value.get(0)+"."+this.value.get(1));
	}
}
