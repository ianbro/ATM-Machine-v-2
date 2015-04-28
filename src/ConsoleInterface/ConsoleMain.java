package ConsoleInterface;

import java.io.IOException;
import java.util.Scanner;

import bank.Bank;
import bank.Customer;
import main.ATM_Main;

public abstract class ConsoleMain{

	static Scanner kbReader;
	public static Bank activeBank;
	public static Customer activeCustomer;
	public static boolean loggedIn = false;
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec("cmd.exe /c start");
		while(true){ //must press "P" to shut down machine
			ATM_Main.readBanks();
			loggedInLoop();
			ATM_Main.printBanks();
			System.out.println("");
		}
	}
	
	public static void loggedInLoop(){
		if(Console.promptLogin()){
			loggedIn = true;
			do { //must press y to exit account
				activeBank = ATM_Main.activeBank;
				activeCustomer = ATM_Main.activeBank.activeCustomer;
				Console.showMainMenu();
			} while(loggedIn);
		}
	}
}
