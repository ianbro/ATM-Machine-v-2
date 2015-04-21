package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import security.Password;
import main.ErrorManager;
import main.FileManager;
import main.ATM_Main;
import bank.security.AccountAddress;

public class Bank {
	
	public static void openBank(Bank bank){
		
	}
	
	private String name;
	public ArrayList<Customer> customers;
	private AccountAddress bankID;
	private File customerFile;
	public Scanner customerReader;
	public PrintWriter customerWriter;
	public Customer activeCustomer;
	
	public Bank(String name, int nthBank){
		this.name = name;
		bankID = new AccountAddress(AccountAddress.format(nthBank));
		this.customers = new ArrayList<Customer>();
	}
	
	public File openCustomerFile(){
		return new File("src/banks/"+this.bankID.get("bank")+"/customers.txt");
	}
	
	public String toString(){
		return ("<"+this.name +"><"+ this.bankID.get("bank")+">");
	}
	
	public String getName(){
		return this.name;
	}
	
	public String printName(){
		for(int i = 0; i < this.name.length(); i ++){
			if(this.name.charAt(i) == '_'){
				return (this.name.substring(0, i) + " " + this.name.substring(i+1));
			}
		}
		return this.name;
	}
	
	public AccountAddress getID(){
		return this.bankID;
	}
	
	public boolean login(String userName, String password){
		try{
			AccountAddress suposed = search(this, userName);
			if(suposed.get("bank").equals("---")){
				activeCustomer = null;
			}
			else{
				
			}
			Customer retVal = getCustomerPointedTo(suposed);
			if(password.equals(retVal.getPassword().toString())){
				retVal.openCustomerAccountsFile();
				activeCustomer = retVal;
				return true;
			} else{
				activeCustomer = null;
				return false;
			}
		} catch(NullPointerException n){
			activeCustomer = null;
			return false;
		}
	}
	
	public void logout(){
		this.activeCustomer = null;
	}
	
	public static AccountAddress search(Bank bank, String userName){
		for(int i = 0; i < bank.customers.size(); i ++){
			if(bank.customers.get(i).userName.equals(userName)){
				return bank.customers.get(i).personNumber;
			}
		}
		return new AccountAddress("---", "---");
	}
	
	public static Customer getCustomerPointedTo(AccountAddress address){
		for(int i = 0; i < ATM_Main.banks.size(); i ++){
			if(address.get("bank").equals(ATM_Main.banks.get(i).getID().get("bank"))){
				Bank targetB = ATM_Main.banks.get(i);
				for(int j = 0; j < targetB.customers.size(); j++){
					if(address.get("customer").equals(ATM_Main.banks.get(i).customers.get(j).personNumber.get("customer"))){
						return ATM_Main.banks.get(i).customers.get(j);
					}
				}
				return null;
			}
		}
		return null;
	}
	
	public void createNewCustomer(String username, String name, String phone, String address, String pass){
		Customer toAdd = new Customer(this, username, name, phone, address, pass);
		FileManager.createCustomerDir(toAdd);
		toAdd.openCustomerAccountsFile();
		this.customers.add(toAdd);
	}
	
	
	
	
	
	//reading
	public static Bank convertToBank(String name, int nthBank){
		Bank toAdd = new Bank(name, nthBank);
		toAdd.readCustomers();
		return toAdd;
	}
	
	public void readCustomers(){
		customerFile = new File("src/banks/"+this.getID().get("bank")+"/customers.txt");
		try {
			customerReader = new Scanner(customerFile).useDelimiter("[,|\n|\r]+");
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(customerFile);
		}
		
		while(customerReader.hasNext()){
			readNextCustomer();
		}
		customerReader.close();
	}
	
	public void readNextCustomer(){
		ArrayList<String> params = new ArrayList<String>();
		params.add(customerReader.next());
		params.add(customerReader.next());
		params.add(customerReader.next());
		params.add(customerReader.next());
		params.add(customerReader.next());
		this.customers.add(Customer.convertToCustomer(this, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4)));
		params = null;
	}
	
	
	
	
	
	//printing
	public void printCustomers(){
		customerFile = new File("src/banks/"+this.getID().get("bank")+"/customers.txt");
		try {
			customerWriter = new PrintWriter(customerFile);
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(customerFile);
		}
		for(int i = 0; i < customers.size(); i ++){
			customers.get(i).printAccounts();
			customerWriter.println(customers.get(i).toString());
		}
		customerWriter.close();
	}
	
	public Customer get(String address){
		return this.customers.get(Integer.valueOf(address)-1);
	}
}
