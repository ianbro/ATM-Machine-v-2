package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginPane extends Parent{
	
	public static Parent getDesign(){
		Parent root = null;
		try {
			root = FXMLLoader.load(LoginPane.class.getResource("designFiles/LoginGUI.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return root;
	}
}
