package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}


	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	

	public void back(ActionEvent e) throws IOException{
		showInitialPage(e);
	}
	
	public void login(ActionEvent e) throws IOException{
		String name=usernameText.getText();
		String password=passwordText.getText();
		
		if(Model.isValidUser(name, password)){
			usernameText.setPromptText("Username");
			usernameText.setStyle("-fx-prompt-text-fill:grey");
			passwordText.setPromptText("Password");
			passwordText.setStyle("-fx-prompt-text-fill:grey");
			
			Stage newStage=new Stage();
			newStage.setTitle("Shawn's Shop");
			FXMLLoader loader=new FXMLLoader();
			
			if(name.equals("Shawn")){
				Pane root=loader.load(getClass().getResourceAsStream("AdminPage.fxml"));
				
				
				Scene scene=new Scene(root);
				newStage.setScene(scene);
				newStage.show();
				((Node) e.getSource()).getScene().getWindow().hide();
				
			}
			else{
				Pane root=loader.load(getClass().getResourceAsStream("CustomerHomePage.fxml"));
				CustomerHomePageController  homePageController=(CustomerHomePageController) loader.getController();
				homePageController.InitializeUsername(name);
				Model.setCurrentUser(name);
				Model.initializeCart();
				
				
				Scene scene=new Scene(root);
				newStage.setScene(scene);
				newStage.show();
				((Node) e.getSource()).getScene().getWindow().hide();
			
			}
			
		}
		else{
			HashMap<String,String> users=Model.getUsers();
			
			if(users.containsKey(name) && !users.get(name).equals(password)){
				usernameText.setText(name);
				usernameText.setPromptText("Username");
				usernameText.setStyle("-fx-prompt-text-fill:grey");
				
				passwordText.clear();
				passwordText.setPromptText("Wrong password");
				passwordText.setStyle("-fx-prompt-text-fill:red");
			}
			else if(!users.containsKey(name)){
				usernameText.clear();
				usernameText.setPromptText("Wrong Username");
				usernameText.setStyle("-fx-prompt-text-fill:red");
				
				
				passwordText.setText(password);
				passwordText.setPromptText("Password");
				passwordText.setStyle("-fx-prompt-text-fill:grey");
			}
			
			
		}
	}
	
	
	
	//private method
	
	private void showInitialPage(ActionEvent e) throws IOException{
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		//initial page
		Pane IntitialPageRoot=FXMLLoader.load(getClass().getResource("InitialPage.fxml"));
		Scene IntitialPageScene=new Scene(IntitialPageRoot);
		window.setScene(IntitialPageScene);
		window.show();
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	
	
}








