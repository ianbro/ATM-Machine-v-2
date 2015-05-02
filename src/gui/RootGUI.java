package gui;

import main.ATM_Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RootGUI{

	public static void show(Stage primaryStage){
		primaryStage.setScene(getMainScene());
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Ian's Bank ATM");
		primaryStage.show();
	}
	
	public static Scene getMainScene(){
		Scene scene = new Scene(ATM_Main.root, getHostScreenWidth(), getHostScreenHeight());
		return scene;
	}
	
	public static Parent getRootGUI(){
		Parent root = null;
		try {
			root = FXMLLoader.load(RootGUI.class.getResource("designFiles/MainGUI.fxml"));
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
