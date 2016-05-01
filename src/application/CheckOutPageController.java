package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import store.Item;

public class CheckOutPageController implements Initializable {
	
	@FXML
	private Text text;
	
	private int itemsPrice;
	private double hst;
	private double totalPrice;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Date date = new Date();
		text.setStyle("-fx-font-family: monospace");
		itemsPrice=Model.calculatePrice();
		hst=itemsPrice*0.13;
		totalPrice=itemsPrice+hst;
		StringBuilder context=new StringBuilder();
		context.append(String.format("%-8s%-26s%-10s%-10s%-12s%-10s%n","S.No","Name of the Book","Author","Price($)","Quantity","Type"));
		for(Item i:Model.getShoppingCart().getItems()){
			context.append(String.format(" %-7s%-27s%-10s%-12s%-9s%-10s%n",i.sNo,i.Name,i.authorName,i.getPrice(),i.getQuantity(),i.getType()));
		}
		context.append("\n");
		context.append(String.format("%59s%d$%n","SubTotal:",itemsPrice));
		context.append(String.format("%54s%d%%):%1.2f$%n","HST(",13,hst));
		context.append(String.format("%73s%n","-----------------------"));
		context.append(String.format("%56s%1.2f$%n","Total:",totalPrice));
		context.append(String.format("%55s %2$tB %2$td, %2$tY","Date:", date));
		text.setText(context.toString());
	}
	
	
	public void gotoShoppingCart(ActionEvent e) throws IOException{
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane ShoppingCartRoot=FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
		Scene scene=new Scene(ShoppingCartRoot);
		window.setScene(scene);
		window.show();
		
		((Node)e.getSource()).getScene().getWindow().hide();
	}

	
	public void paybill(ActionEvent e) throws IOException{
		HashMap<String,Item> inStore=Model.getAllItems();
		Iterable<Item> inCart=Model.getShoppingCart().getItems();

		for(Item i:inCart){
			Item itemInStore=inStore.get(i.Name);
			itemInStore.setQuantity(itemInStore.Quantity-i.Quantity);	
			Model.getShoppingCart().removeItem(i.Name, i.Quantity);
		}
		
		Model.getShoppingCart().updateCartFile();
		Model.updateItemsFile();
		Model.updatePreviousOrders(Model.getCurrentUser(),text.getText());
		
		
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane PaySuccessPageRoot=FXMLLoader.load(getClass().getResource("PaySuccess.fxml"));
		Scene PaySuccessPageScene=new Scene(PaySuccessPageRoot);
		window.setScene(PaySuccessPageScene);
		window.show();
		((Node)e.getSource()).getScene().getWindow().hide();
		
	}
	

}
