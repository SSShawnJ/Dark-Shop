package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ItemSelectPageController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void goToReadables(ActionEvent e) throws IOException{
		Stage newStage=new Stage();
		newStage.setTitle("Shawn's Shop");
		FXMLLoader loader=new FXMLLoader();
		Pane root=loader.load(getClass().getResourceAsStream("ReadablesPage.fxml"));
		Scene scene=new Scene(root);
		newStage.setScene(scene);
		newStage.show();
		((Node) e.getSource()).getScene().getWindow().hide();
	}
	
	public void goToAudios(ActionEvent e) throws IOException{
		Stage newStage=new Stage();
		newStage.setTitle("Shawn's Shop");
		FXMLLoader loader=new FXMLLoader();
		Pane root=loader.load(getClass().getResourceAsStream("AudiosPage.fxml"));
		Scene scene=new Scene(root);
		newStage.setScene(scene);
		newStage.show();
		((Node) e.getSource()).getScene().getWindow().hide();
	}
	
	public void goBack(ActionEvent e) throws IOException{
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
