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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerHomePageController implements Initializable{

	@FXML
	private Label username;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	

	public void InitializeUsername(String name){
		username.setText("Welcome Dear "+name);
	}
	
	
	public void shoppingByCategary(ActionEvent e)throws IOException{
		showItemSelectPage(e);
	}
	
	
	public void signOut(ActionEvent e) throws IOException{
		username.setText("");
		Model.getShoppingCart().updateCartFile();
		Model.updateItemsFile();
		showInitialPage(e);
	}
	

	public void goToCart(ActionEvent e) throws IOException{
		showShoppingCart(e);
	}
	
	public void goToHistory(ActionEvent e)throws IOException{
		showHistory(e);
	}
	
	
	
	/*****************************************************
	 * private method
	 *****************************************************/
	
	
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
	
	
	private void showItemSelectPage(ActionEvent e) throws IOException{
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane ItemSelectRoot=FXMLLoader.load(getClass().getResource("ItemSelectPage.fxml"));
		Scene scene=new Scene(ItemSelectRoot);
		window.setScene(scene);
		window.show();
		
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	private void showShoppingCart(ActionEvent e) throws IOException{
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane ShoppingCartRoot=FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
		Scene scene=new Scene(ShoppingCartRoot);
		window.setScene(scene);
		window.show();
		
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
	private void showHistory(ActionEvent e)throws IOException{
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane HistoryRoot=FXMLLoader.load(getClass().getResource("OrderHistoryPage.fxml"));
		Scene scene=new Scene(HistoryRoot);
		window.setScene(scene);
		window.show();
		
		((Node)e.getSource()).getScene().getWindow().hide();
	}
}








