package application;

import java.io.IOException;
import java.net.URL;
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

public class SignUpPageController implements Initializable {
	
	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	@FXML
	private PasswordField confirmPassText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}

	
	
	
	public void signUp(ActionEvent e)throws IOException{
		String name=usernameText.getText();
		String password=passwordText.getText();
		String repassword=confirmPassText.getText();
		
		if(name.equals("")){
			usernameText.clear();
			usernameText.setPromptText("Invalid Username");
			usernameText.setStyle("-fx-prompt-text-fill:red");
			
			confirmPassText.setPromptText("Confirm password");
			confirmPassText.setStyle("-fx-prompt-text-fill:grey");
		}
		else{
			if(Model.getUsers().containsKey(name)){
				usernameText.clear();
				usernameText.setPromptText("Username exists.");
				usernameText.setStyle("-fx-prompt-text-fill:red");
				
				confirmPassText.setPromptText("Confirm password");
				confirmPassText.setStyle("-fx-prompt-text-fill:grey");
			}
			//user name is valid
			else{
				if(password.equals(repassword)){
					Model.getUsers().put(name, password);
					Model.updateUsersFile();
					AlertBox.display("Sign Up Success", "Congratulation!", "Cheers");
					showInitialPage(e);
				}
				else{
					usernameText.setPromptText("New Username");
					usernameText.setStyle("-fx-prompt-text-fill:grey");
					
					confirmPassText.clear();
					confirmPassText.setPromptText("Passwords do not match");
					confirmPassText.setStyle("-fx-prompt-text-fill:red");
				}
			}
		
		}	
	}
	
	
	
	public void back(ActionEvent e) throws IOException{
		showInitialPage(e);
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
