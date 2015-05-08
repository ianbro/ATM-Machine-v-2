package gui;

import java.net.URL;
import java.util.ResourceBundle;

import bank.Bank;
import bank.Customer;
import main.ATM_Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginController implements Initializable{

	public TextField userNameField;
	public PasswordField passwordField;
	public Button btnOK;
	public AnchorPane frame;
	public Label message;
	
	public void okPressed(){
		String userName = userNameField.getText();
		String password = passwordField.getText();
		login(userName, password);
		try{
			System.out.println(ATM_Main.activeBank.activeCustomer);
			if(ATM_Main.activeBank.activeCustomer != null){
				RootGUI.closeLogin((BorderPane)ATM_Main.root);
			} else {
				userNameField.clear();
				passwordField.clear();
				message.setText("Sorry, that is not the correct password.");
				message.setVisible(true);
			}
		} catch (Exception e){
			System.out.println("wrong login");
			userNameField.setText(null);
			passwordField.setText(null);
			message.setText("Sorry, could not find account: " + userName);
			message.setVisible(true);
		}
		//show banks background
	}
	
	public boolean login(String userName, String password){
		try{
			System.out.println(ATM_Main.banks);
			Customer c = ATM_Main.searchUserAllBanks(userName);
			Bank b = c.parentBank;
			ATM_Main.login(c.parentBank.getName());
			c.parentBank.login(userName, password);
			if(ATM_Main.activeBank.activeCustomer != null){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			userNameField.setText(null);
			passwordField.setText(null);
			message.setText("Sorry, could not find account: " + userName);
			message.setVisible(true);
			return false;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
