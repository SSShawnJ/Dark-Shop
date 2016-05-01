package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import store.Item;

public class AudiosPageController implements Initializable{
	@FXML
	private TableView<Item> table;
	@FXML
	private TableColumn<Item,Integer> noColumn;
	@FXML
	private TableColumn<Item,String> nameColumn;
	@FXML
	private TableColumn<Item,String> authorColumn;
	@FXML
	private TableColumn<Item,String> priceColumn;
	@FXML
	private TableColumn<Item,String> quantityColumn;
	@FXML
	private TableColumn<Item,String> typeColumn;
	@FXML
	private TableColumn<Item,String> buttonAction;
	@FXML 
	private Circle circle;
	@FXML
	private Label number;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		noColumn.setCellValueFactory(new PropertyValueFactory<Item,Integer>("sNo"));
		noColumn.setStyle("-fx-alignment:CENTER");
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("Name") );
		
		authorColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("authorName") );
		authorColumn.setStyle("-fx-alignment:CENTER");
		
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("price") );
		priceColumn.setStyle("-fx-alignment:CENTER");
		
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("Quantity") );
		quantityColumn.setStyle("-fx-alignment:CENTER");
		
		typeColumn.setCellValueFactory(new PropertyValueFactory<Item,String>("Type") );
		typeColumn.setStyle("-fx-alignment:CENTER");
		
		number.textProperty().bind(Model.getNumberInCart().asString());
		
		 Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory = //
	                new Callback<TableColumn<Item, String>, TableCell<Item, String>>()
	                {
	                    @Override
	                    public TableCell<Item,String> call( final TableColumn<Item, String> param )
	                    {
	                        final TableCell<Item, String> cell = new TableCell<Item, String>()
	                        {

	                        	final HBox hBox=new HBox(5);
	                        	final ChoiceBox<Integer> choiceBox=new ChoiceBox<Integer>();
	                            final Button button = new Button( "Add to Cart" );

	                            @Override
	                            public void updateItem( String item, boolean empty )
	                            {
	                                super.updateItem( item, empty );
	                                if ( empty )
	                                {
	                                    setGraphic( null );
	                                    setText( null );
	                                }
	                                else
	                                {
	                                	int thisItemQ = getTableView().getItems().get( getIndex() ).getQuantity();
	    	                        	if(thisItemQ<1)
	    	                        		choiceBox.getItems().add(0);
	    	                        	else{
	    	                        		for(int i=1;i<=thisItemQ;i++){
	    	                        			if(i>10) break;
	    	                        			choiceBox.getItems().add(i);	
	    	                        		}
	    	                        	}
	    	                        	choiceBox.getSelectionModel().select(0);
	    	                        	choiceBox.setMinWidth(55);
	                                	
	                                    button.setOnAction( e ->
	                                            {
	                                            	Item thisItem = getTableView().getItems().get(getIndex());
	    	                                    	Model.getShoppingCart().addtoCart(thisItem.sNo, thisItem.Name, thisItem.authorName ,
	    	                                    									thisItem.price, choiceBox.getValue(), thisItem.Type);
	                                    } );
	                                  
	                                    button.setPadding(new Insets(5,5,5,5));
	                                    button.alignmentProperty().setValue(Pos.CENTER);
	                                    hBox.getChildren().addAll(choiceBox,button);
	                                    hBox.setAlignment(Pos.CENTER);
	                                    setGraphic(hBox);
	                                    setText( null );
	                                }
	                            }
	                        };
	                        return cell;
	                    }
	                };
	       
	    buttonAction.setCellFactory(cellFactory);
	    buttonAction.setStyle("-fx-alignment:CENTER");
		
		table.setItems(Model.getAudios());
		
	}
	
	public void goToCart(ActionEvent e) throws IOException{
		showShoppingCart(e);
	}
	
	
	public void goBack(ActionEvent e) throws IOException{
		Model.getShoppingCart().updateCartFile();
		
		
			Stage window=new Stage();
			window.setTitle("Shawn's Shop");
			
			Pane ItemSelectRoot=FXMLLoader.load(getClass().getResource("ItemSelectPage.fxml"));
			Scene scene=new Scene(ItemSelectRoot);
			window.setScene(scene);
			window.show();
			
			((Node)e.getSource()).getScene().getWindow().hide();
		}
	
	
	private void showShoppingCart(ActionEvent e) throws IOException{
		Model.getShoppingCart().updateCartFile();
		
		Stage window=new Stage();
		window.setTitle("Shawn's Shop");
		
		Pane ShoppingCartRoot=FXMLLoader.load(getClass().getResource("ShoppingCartPage.fxml"));
		Scene scene=new Scene(ShoppingCartRoot);
		window.setScene(scene);
		window.show();
		
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
}
