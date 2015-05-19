package gui;

import java.net.URL;
import java.util.ResourceBundle;

import main.ATM_Main;
import bank.Account;
import bank.Transaction;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountHistoryController implements Initializable{

	private Button btnTransaction;
	private TableView<Transaction> transactionView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	@FXML private void makeTransactionPressed(){
		//show dep/withdrawal menu
		System.out.println("make transaction");
	}

}
