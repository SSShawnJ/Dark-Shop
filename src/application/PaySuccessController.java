package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaySuccessController {

	
	
	public void goToHome(ActionEvent e) throws IOException{
		Stage newStage=new Stage();
		newStage.setTitle("Shawn's Shop");
		FXMLLoader loader=new FXMLLoader();
		Pane root=loader.load(getClass().getResourceAsStream("CustomerHomePage.fxml"));
		CustomerHomePageController  homePageController=(CustomerHomePageController) loader.getController();
		homePageController.InitializeUsername(Model.getCurrentUser());
		
		Scene scene=new Scene(root);
		newStage.setScene(scene);
		newStage.show();
		((Node) e.getSource()).getScene().getWindow().hide();
	}
}
