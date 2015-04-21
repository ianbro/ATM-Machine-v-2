package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUIController {

	public static void showSuperGUI(Stage primaryStage){
		MainGUIController superGUI = new MainGUIController();
		superGUI.createSuperGUI(primaryStage);
	}
	
	public void createSuperGUI(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("designFiles/MainGUI.fxml"));
			Scene scene = new Scene(root,getHostScreenWidth(),getHostScreenHeight());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Ian Bank ATM");
	}
	
	public static int getHostScreenWidth(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	
	public static int getHostScreenHeight(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
