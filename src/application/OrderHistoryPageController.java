package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderHistoryPageController implements Initializable {
	
	@FXML
	private Label title;
	@FXML
	private Text history;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String user=Model.getCurrentUser();
		title.setText(user+"'s Previous Orders");
		history.setStyle("-fx-font-family: monospace");
		
		try {
			Scanner x=new Scanner(new File(user+"OrderHistory.txt"));
			StringBuilder input=new StringBuilder();
			while(x.hasNextLine()){
				input.append(x.nextLine()+"\n");
			}
			x.close();
			String in=input.toString();
			if(in.equals("")){
				history.setText("No Order History");
			}
			else{
				history.setText(in);
			}
			
		} catch (FileNotFoundException e) {
			history.setText("No Order History");
		}
		
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

	
	public void clearHistory() throws IOException{
		Model.clearPreviousOrders(Model.getCurrentUser());
		history.setText("No Order History");
		
	}
	
}
