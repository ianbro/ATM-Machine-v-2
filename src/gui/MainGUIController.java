package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

public class MainGUIController implements Initializable{

	public AnchorPane head;
	public Button btnActivity;
	public Button btnTransfer;
	public Button btnSettings;
	public Button btnLogout;
	public AnchorPane mainDisplay;
	public ScrollPane mainScrollPane;
	public Node loginPane;
	
	
	
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
		loginPane.setLayoutX(545);
		loginPane.setLayoutY(225);
		loginPane.setId("loginPane");
		mainScrollPane.setFitToWidth(true);
		mainScrollPane.setFitToHeight(true);
		mainDisplay.getChildren().add(loginPane);
		this.setBackgroundIansBank();
	}
	
	private void setBackgroundIansBank(){
		this.mainDisplay.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("designFiles/images/iansBankBackground.png")), null, null, null, null)));
	}
}
