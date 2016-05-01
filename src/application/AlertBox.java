package application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void display(String title,String message,String buttonMessage){
		Stage window=new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		Label label1=new Label(message);
		
		Button closeButton=new Button(buttonMessage);
		
		closeButton.setOnAction(e ->window.close());
		closeButton.setPadding(new Insets(10,10,10,10));
		
		VBox layout=new VBox(10);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(label1,closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(layout);
		
		window.setScene(scene);
		window.showAndWait();
		
		
	}
	
}
