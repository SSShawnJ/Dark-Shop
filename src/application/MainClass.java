package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainClass extends Application {

	Stage window;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		window=primaryStage;
		window.setTitle("Shawn's Shop");
		
		Model.initialize();
		//initial page
		Pane IntitialPageRoot=FXMLLoader.load(getClass().getResource("InitialPage.fxml"));
		Scene IntitialPageScene=new Scene(IntitialPageRoot);
		
		window.setScene(IntitialPageScene);
		window.show();
	}
	
}
