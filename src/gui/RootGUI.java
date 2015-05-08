package gui;

import main.ATM_Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	
	public static BorderPane getRootGUI(){
		BorderPane root = null;
		try {
			root = FXMLLoader.load(RootGUI.class.getResource("designFiles/MainGUI.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		showLogin(root);
		return root;
	}
	
	public static void closeLogin(BorderPane root) {
		AnchorPane a = (AnchorPane)root.getChildren().get(1);
		ScrollPane s = (ScrollPane)a.getChildren().get(0);
		AnchorPane display = (AnchorPane)s.getContent();
		display.getChildren().remove(0);
	}

	public static void showLogin(BorderPane root) {
		AnchorPane a = (AnchorPane)root.getChildren().get(1);
		ScrollPane s = (ScrollPane)a.getChildren().get(0);
		AnchorPane display = (AnchorPane)s.getContent();
		Node loginPane = LoginPane.getDesign();
		loginPane.setId("loginPane");
		loginPane.setLayoutX(545);
		loginPane.setLayoutY(225);
		s.setFitToHeight(true);
		s.setFitToWidth(true);
		display.getChildren().add(loginPane);
	}
	
	public static int getHostScreenWidth(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	
	public static int getHostScreenHeight(){
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
