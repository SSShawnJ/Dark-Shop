package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import store.Book;
import store.CD;
import store.Item;
import store.MP3;
import store.ShoppingCart;
import store.eBook;

public class Model {

	
	
	private static HashMap<String,String> Users;
	private static ObservableList<Item> Readables =FXCollections.observableArrayList();
	private static ObservableList<Item> Audios =FXCollections.observableArrayList();
	private static String currentUser;
	private static ShoppingCart shoppingCart;
	
	private static IntegerProperty numberInCart=new SimpleIntegerProperty(0);
	
	
	

	
	
	public static void initialize() throws IOException{
		Scanner x;
		//read Users
		x=new Scanner(new File("Users.txt"));
		Users=new HashMap<String,String>();
		while(x.hasNextLine()){
			String[] a=x.nextLine().split(",");
			Users.put(a[0],a[1]);
		}
		x.close();
		
		//get readables and Audios
		getInputReadables();
		getInputAudioProducts();	
	}
	
	public static void initializeCart(){
		shoppingCart=new ShoppingCart(currentUser);
	}
	
	
	public static boolean isValidUser(String username,String password){
		if(username==null) return false;
		if(Users.containsKey(username)){
			if(Users.get(username).equals(password))
				return true;
		}
		return false;
	}
	
	//getters and setters
	public static String getCurrentUser() {
		return currentUser;
	}


	public static void setCurrentUser(String currentUser) {
		Model.currentUser = currentUser;
	}
	
	
	public static IntegerProperty getNumberInCart() {
		return numberInCart;
	}

	public static void setNumberInCart(IntegerProperty numberInCart) {
		Model.numberInCart = numberInCart;
	}
	
	public static void updateNumberInCart(int i){
		numberInCart.set(numberInCart.get()+i);
		
	}
	

	public static HashMap<String,String> getUsers(){return Users;}

	public static ObservableList<Item> getAudios() {
		return Audios;
	}

	public static ObservableList<Item> getReadables() {
		return Readables;
	}

	public static void setAudios(ObservableList<Item> audios) {
		Audios = audios;
	}


	public static void setReadables(ObservableList<Item> readables) {
		Readables = readables;
	}
	
	public static ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public static void setShoppingCart(ShoppingCart shoppingCart) {
		Model.shoppingCart = shoppingCart;
	}
	
	public static HashMap<String,Item> getAllItems(){
		HashMap<String,Item> items=new HashMap<>();
		Iterator<Item> i=Readables.iterator();
		while(i.hasNext()){
			Item x=i.next();
			items.put(x.Name, x);
		}
		i=Audios.iterator();
		while(i.hasNext()){
			Item x=i.next();
			items.put(x.Name, x);
		}
		
		return items;
	}
	
	
	
	public static int calculatePrice(){
		int totalPrice=0;
		for(Item item:shoppingCart.getItems()){
			totalPrice+=item.getQuantity()*item.getPrice();
		}
		return totalPrice;
	}
	
	public static void updateItemsFile() throws IOException{
		File Booksfile=new File("Books.txt");
		File Ebooksfile=new File("Ebooks.txt");
		File CDsfile=new File("CDs.txt");
		File MP3file=new File("MP3.txt");
		PrintWriter output1=new PrintWriter(Booksfile);;
		PrintWriter output2=new PrintWriter(Ebooksfile);;
		Iterator<Item> i=Readables.iterator();
		while(i.hasNext()){
			Item x=i.next();
			if(x.getType().equals("Book")){
				output1.println(x.sNo+","+x.Name+","+x.authorName+","+x.price+","+x.Quantity+","+x.Type);
			}
			else{
				output2.println(x.sNo+","+x.Name+","+x.authorName+","+x.price+","+x.Quantity+","+x.Type);
			}	
		}
		output1.close();
		output2.close();
		
		output1=new PrintWriter(CDsfile);;
		output2=new PrintWriter(MP3file);;
		i=Audios.iterator();
		while(i.hasNext()){
			Item x=i.next();
			if(x.getType().equals("CD")){
				output1.println(x.sNo+","+x.Name+","+x.authorName+","+x.price+","+x.Quantity+","+x.Type);
			}
			else{
				output2.println(x.sNo+","+x.Name+","+x.authorName+","+x.price+","+x.Quantity+","+x.Type);
			}	
		}
		output1.close();
		output2.close();	
	}
	
	
	public static void updatePreviousOrders(String user,String content) throws IOException{
		String fileName=user+"OrderHistory.txt";
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		out.println(content);
		out.println();
		out.println("----------------------------------------------------------------------------------------");
		out.println();
		out.close();
	}
	
	public static void clearPreviousOrders(String user) throws IOException{
		String fileName=user+"OrderHistory.txt";
		PrintWriter out=new PrintWriter(new File(fileName));
		out.print("");
		out.close();
	}
	
	public static void updateUsersFile() throws FileNotFoundException{
		PrintWriter out=new PrintWriter(new File("Users.txt"));
		Iterator<String> users=Users.keySet().iterator();
		while(users.hasNext()){
			String user=users.next();
			out.println(user+","+Users.get(user));
		}
		out.close();
	}
	
	
	/*****************************************************
	 * private method
	 *****************************************************/
	
	
	
	// Fetches all readables from the files and places them in the readables arrayList
			private static void getInputReadables()throws IOException{
				File Booksfile=new File("Books.txt");
				File Ebooksfile=new File("Ebooks.txt");
				Scanner input=new Scanner(Booksfile);
				input.useDelimiter("[\\r\\n,]+");
				while(input.hasNext()){
			       Readables.add(new Book(input.nextInt(),input.next(),input.next(),input.nextInt(),input.nextInt(),input.next()));
				}
		        input.close();
				input=new Scanner(Ebooksfile);
				input.useDelimiter("[\\r\\n,]+");
		        while(input.hasNext()){
				    Readables.add(new eBook(input.nextInt(),input.next(),input.next(),input.nextInt(),input.nextInt(),input.next()));
		        }
		        input.close();
		       
			}
			
			// Fetches all Audio from the files and places them in the AudioProduct arrayList
			private static void getInputAudioProducts()throws IOException{
				File CDsfile=new File("CDs.txt");
				File MP3file=new File("MP3.txt");
				Scanner input=new Scanner(CDsfile);
				input.useDelimiter("[\\r\\n,]+");
				while(input.hasNext()){
			    Audios.add(new CD(input.nextInt(),input.next(),input.next(),input.nextInt(),input.nextInt(),input.next()));
				}
				input.close();
				input=new Scanner(MP3file);
				input.useDelimiter("[\\r\\n,]+");
				while(input.hasNext()){
					Audios.add(new MP3(input.nextInt(),input.next(),input.next(),input.nextInt(),input.nextInt(),input.next()));
				}
				input.close();
			}

			


			
	
}








