package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

	public TextField userNameField;
	public PasswordField passwordField;
	public Button btnOK;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	public void okPressed(){
		String name = userNameField.getText();
		String password = userNameField.getText();
		System.out.println(name + ", " + password);
	}
}
