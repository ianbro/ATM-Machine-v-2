package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RootGUI{

	public void show(Stage primaryStage){
		primaryStage.setScene(this.getMainScene());
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Ian's Bank ATM");
		primaryStage.show();
	}
	
	public Scene getMainScene(){
		Scene scene = new Scene(getRootGUI(), getHostScreenWidth(), getHostScreenHeight());
		return scene;
	}
	
	public Parent getRootGUI(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("designFiles/MainGUI.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public static int getHostScreenWidth(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	
	public static int getHostScreenHeight(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
