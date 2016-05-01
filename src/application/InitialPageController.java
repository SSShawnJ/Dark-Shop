package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitialPageController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void logIn(ActionEvent event) throws IOException{
		showLoginPage(event);
	}
	
	public void signUp(ActionEvent event) throws IOException{
		showSignUpPage(event);
		
	}
	
	public void exit(ActionEvent event) throws IOException{
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	
	
	
	
	
	
	
	//private method
	
	private void showLoginPage(ActionEvent event) throws IOException{
		Parent Root=FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
		Scene newscene=new Scene(Root);
		Stage newWindow=new Stage();
		newWindow.setTitle("Login");
		newWindow.setScene(newscene);
		newWindow.show();
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	private void showSignUpPage(ActionEvent event) throws IOException{
		Parent Root=FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
		Scene newscene=new Scene(Root);
		Stage newWindow=new Stage();
		newWindow.setTitle("Sign Up");
		newWindow.setScene(newscene);
		newWindow.show();
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
}
