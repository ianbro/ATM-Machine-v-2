package gui;

import java.net.URL;
import java.util.ResourceBundle;

import bank.security.AccountAddress;
import main.ATM_Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class MainGUIController implements Initializable{

	@FXML public AnchorPane head;
	@FXML public Button btnActivity;
	@FXML public Button btnTransfer;
	@FXML public Button btnSettings;
	@FXML public Button btnLogout;
	@FXML public AnchorPane mainDisplay;
	@FXML public ScrollPane mainScrollPane;
	@FXML public Node loginPane;
	
	public void activityPressed(){
		if(ATM_Main.activeBank != null){
			System.out.println("activity");
			showAccountHistoryPane();
			AccountHistoryPane.showTransactions(ATM_Main.searchAccountByAddress(new AccountAddress("001", "001", "001")));
		}
		else {
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setText("Please login first");
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setVisible(true);
		}
	}
	
	public void transferPressed(){
		if(ATM_Main.activeBank != null){
			System.out.println("transfer");
		}
		else {
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setText("Please login first");
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setVisible(true);
		}
	}
	
	public void settingsPressed(){
		if(ATM_Main.activeBank != null){	
			System.out.println("settings");
		}
		else {
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setText("Please login first");
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setVisible(true);
		}
	}
	
	public void logoutPressed(){
		if(ATM_Main.activeBank != null){
			System.out.println("logout");
			ATM_Main.activeBank.activeCustomer = null;
			ATM_Main.activeBank = null;
			RootGUI.showLogin((BorderPane)ATM_Main.root);
		}
		else {
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setText("Please login first");
			((Label)(((AnchorPane)mainDisplay.getChildren().get(0)).getChildren().get(2))).setVisible(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void showAccountHistoryPane(){
		AnchorPane historyPane = (AnchorPane) AccountHistoryPane.getDesign();
		historyPane.setTranslateX(370);
		historyPane.setTranslateY(200);
		mainDisplay.getChildren().add(historyPane);
	}
}
