package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable{

	public TextField userNameField;
	public PasswordField passwordField;
	public Button btnOK;
	public AnchorPane frame;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	public void okPressed(){
		String userName = userNameField.getText();
		String password = passwordField.getText();
		System.out.println(userName + ", " + password);
	}
}
