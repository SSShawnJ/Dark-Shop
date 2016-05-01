package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Queue;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import store.Item;

public class ShoppingCartPageController implements Initializable {
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
		
		 Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory = //
	                new Callback<TableColumn<Item, String>, TableCell<Item, String>>()
	                {
	                    @Override
	                    public TableCell<Item,String> call( final TableColumn<Item, String> param )
	                    {
	                        final TableCell<Item, String> cell = new TableCell<Item, String>()
	                        {

	                        	HBox hBox;
	                        	ChoiceBox<Integer> choiceBox;
	                            Button button;

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
	                                	hBox=new HBox(5);
	                                	button = new Button( "Delete" );
	                                	choiceBox=new ChoiceBox<Integer>();
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
	                                	
	                                    button.setOnAction( e ->{
	                                    	
	                                    	String thisItem = getTableView().getItems().get(getIndex()).getName();
	                                    	Model.getShoppingCart().removeItem(thisItem,choiceBox.getValue());
	                                    	table.getItems().clear();
	                                    	table.setItems(Model.getShoppingCart().getObservableItems());
	                                    	

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
	       
	
	   
		table.setItems(Model.getShoppingCart().getObservableItems());
		
	}
	
	
	public void goToCheckOut(ActionEvent e)throws IOException{
		Model.getShoppingCart().updateCartFile();
		
		boolean allInStock=true;
		Iterable<Item> inCart=Model.getShoppingCart().getItems();
		String ErrorMessage="";
		
		if(((Queue<Item>)inCart).size()==0){
			allInStock=false;
			ErrorMessage="Sorry,Shopping Cart is empty.";
		}
		else{
			HashMap<String,Item> inStore=Model.getAllItems();
			
			for(Item i:inCart){
				int quantityInStore=inStore.get(i.Name).getQuantity();
				if(i.Quantity>quantityInStore){
					allInStock=false;
					ErrorMessage+="Sorry!There is only "+quantityInStore+" "+i.getName()+"left in store.\n";
				}	
			}
		}
		
		if(allInStock){
			
			Stage newStage=new Stage();
			newStage.setTitle("Shawn's Shop");
			Pane root=FXMLLoader.load(getClass().getResource("CheckOutPage.fxml"));
			Scene scene=new Scene(root);
			newStage.setScene(scene);
			newStage.show();
			((Node) e.getSource()).getScene().getWindow().hide();
		}
		else{
			AlertBox.display("Lack in storage", ErrorMessage,"OK");
		}
	}
	
	
	@FXML
	public void goBack(ActionEvent e) throws IOException {
		
		Model.getShoppingCart().updateCartFile();
		
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









