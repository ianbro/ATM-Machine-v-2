package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class MainGUIController implements Initializable{

	public AnchorPane head;
	public static Button btnActivity;
	public static Button btnTransfer;
	public static Button btnSettings;
	public static Button btnLogout;
	public static AnchorPane mainDisplay;
	public static ScrollPane mainScrollPane;
	public static Parent loginPane;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void activityPressed(){
		System.out.println("activity");
	}
	
	public void transferPressed(){
		System.out.println("transfer");
	}
	
	public void settingsPressed(){
		System.out.println("settings");
	}
	
	public void logoutPressed(){
		System.out.println("logout");
	}
	
	public void showLoginPane(){
		loginPane = LoginPane.getDesign();
		mainDisplay.getChildren().add(loginPane);
	}
}
