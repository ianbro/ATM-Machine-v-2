package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainGUIController implements Initializable{

	public AnchorPane head;
	public Button btnActivity;
	public Button btnTransfer;
	public Button btnSettings;
	public Button btnLogout;
	public AnchorPane mainDisplay;
	public ScrollPane mainScrollPane;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
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
}
