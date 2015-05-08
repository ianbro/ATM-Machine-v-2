package main;
	
import gui.RootGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.directory.SearchControls;

import utilities.ModdedDate;
import bank.Account;
import bank.Bank;
import bank.Customer;
import bank.Transaction;
import bank.security.AccountAddress;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class ATM_Main extends Application {
	
	public static ArrayList<Bank> banks = new ArrayList<Bank>();
	public static File banksFile = new File("src/main/banks.txt");
	public static PrintWriter banksWriter;
	public static Scanner banksReader;
	public static Bank activeBank;
	public static Parent root;
	
	@Override
	public void start(Stage primaryStage) {
		readBanks();
		root = RootGUI.getRootGUI();
		RootGUI.show(primaryStage);
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	public static void log(){
		for(Bank b : banks){
			System.out.print(b+"\n\t"+b.customers);
			for(Customer c : b.customers){
				System.out.println("\n\t\t"+c.accounts);
			}
			System.out.println(" ");
		}
	}
	
	public static void createBank(String name) {
		Bank toAdd = new Bank(name, banks.size()+1);
		banks.add(toAdd);
		FileManager.createBankDir(toAdd);
	}
	
	public static Bank searchForBank(String name){
		for(Bank b : banks){
			if(b.getName().equals(name)){
				return b;
			}
		}
		return null;
	}
	
	public static boolean login(String bank){
		activeBank = searchForBank(bank);
		try {
			if(activeBank.equals(null)){
				return false;
			} else{
				return true;
			}
		} catch(NullPointerException n){
			return false;
		}
	}
	
	public static void logout(){
		activeBank.logout();
		activeBank = null;
	}
	
	
	
	
	
	//printing
	public static void printBanks(){
		try {
			banksWriter = new PrintWriter(banksFile);
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(banksFile);
		}
		for(Bank i : banks){
			i.printCustomers();
			banksWriter.println(i);
		}
		banksWriter.close();
		banks = new ArrayList<Bank>();
		activeBank = null;
	}
	
	
	
	
	
	//reading
	public static void readBanks(){
		try {
			banksReader = new Scanner(banksFile).useDelimiter("[<|>|\n|\r]+");
		} catch (FileNotFoundException e) {
			ErrorManager.throwFileNotFoundError(banksFile);
		}
		while(banksReader.hasNext()){
			readNextBank();
		}
		banksReader.close();
	}
	
	public static void readNextBank(){
		String n = banksReader.next();
		Integer nthNum = Integer.valueOf(banksReader.next());
		banks.add(Bank.convertToBank(n, nthNum));
	}
	
	public static Bank get(String address){
		return banks.get(Integer.valueOf(address)-1);
	}
	
	public static Account getAccount(AccountAddress address){
		return get(address.get("bank")).get(address.get("customer")).get(address.get("account"));
	}
	
	public static Account searchAccountByNumber(String b, String c, String a){
		return get(b).get(c).get(a);
	}
	
	public static Account searchAccountByAddress(AccountAddress address){
		return get(address.get("bank")).get(address.get("customer")).get(address.get("account"));
	}
	
	public static Customer searchUserAllBanks(String userName){
		for(Bank b: banks){
			try{
				AccountAddress address = Bank.search(b, userName);
				if(address != null){
					return Bank.getCustomerPointedTo(address);
				}
			} catch (ArrayIndexOutOfBoundsException e){}
		}
		return null;
	}
}
