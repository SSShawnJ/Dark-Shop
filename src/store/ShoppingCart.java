package store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ShoppingCart {

	private ObservableMap<String,Item> cart;
	
	

	public ShoppingCart(String user){
		cart=FXCollections.observableHashMap();
		 try{//add Items to the Cart
     		String userCart="Cart_"+user+".txt";
     		File Cartfile=new File(userCart);
     		Scanner in =new Scanner(Cartfile);
     		while(in.hasNextLine()){
     			String[] thisItem=in.nextLine().split(",");
     			if(cart.containsKey(thisItem[1])){
     				cart.get(thisItem[1]).Quantity+=Integer.parseInt(thisItem[4]);
     			}
     			else{
     				//sNo,name,author,price,quantity,type
     				if(thisItem[5].equals("Book"))
     					cart.put(thisItem[1], new Book(Integer.parseInt(thisItem[0]),thisItem[1],thisItem[2],
     							Integer.parseInt(thisItem[3]),Integer.parseInt(thisItem[4]),thisItem[5]));
     				else if(thisItem[5].equals("eBook"))
     					cart.put(thisItem[1], new eBook(Integer.parseInt(thisItem[0]),thisItem[1],thisItem[2],
     							Integer.parseInt(thisItem[3]),Integer.parseInt(thisItem[4]),thisItem[5]));
     				else if(thisItem[5].equals("CD"))
     					cart.put(thisItem[1], new CD(Integer.parseInt(thisItem[0]),thisItem[1],thisItem[2],
     							Integer.parseInt(thisItem[3]),Integer.parseInt(thisItem[4]),thisItem[5]));
     				else if(thisItem[5].equals("MP3"))
     					cart.put(thisItem[1], new eBook(Integer.parseInt(thisItem[0]),thisItem[1],thisItem[2],
     							Integer.parseInt(thisItem[3]),Integer.parseInt(thisItem[4]),thisItem[5]));
     				
     			}
     			Model.updateNumberInCart(1);
     		}
     		in.close();
     		
     		}catch (FileNotFoundException exception){
     			
     		}   
	}
	
	public void addtoCart(int sNo,String name,String author,int price,int quantity,String type){
		if(quantity>0){
		
			if(cart.containsKey(name)){
				cart.get(name).Quantity+=quantity;
				
			}
			else{
				if(type.equals("Book"))
					cart.put(name,new Book(sNo,name,author,price,quantity,type));
				else if(type.equals("eBook"))
					cart.put(name,new eBook(sNo,name,author,price,quantity,type));
				else if(type.equals("CD"))
					cart.put(name,new CD(sNo,name,author,price,quantity,type));
				else if(type.equals("MP3"))
					cart.put(name,new MP3(sNo,name,author,price,quantity,type));
				
				Model.updateNumberInCart(1);
			}
		}
	}
	
	
	public void removeItem(String itemName,int q){
		if(cart.get(itemName).getQuantity()>q)
			cart.get(itemName).Quantity-=q;
		else{
			cart.remove(itemName);
			Model.updateNumberInCart((-1));
		}
	}
	
	public void updateCartFile() throws FileNotFoundException{
		String userCart="Cart_"+Model.getCurrentUser()+".txt";
		File Cartfile=new File(userCart);
		PrintWriter o= new PrintWriter(Cartfile);
		Iterator<String> keys=cart.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			Item thisItem=cart.get(key);
			o.println(thisItem.sNo+","+thisItem.getName()+","+thisItem.authorName+","+thisItem.getPrice()+","+
			          thisItem.getQuantity()+","+thisItem.Type);
		}
		o.close();
	}
	
	public Iterable<Item> getItems(){
		Queue<Item> queue=new LinkedList<Item>();
		Iterator<String> keys=cart.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			Item thisItem=cart.get(key);
			queue.add(thisItem);
		}
		return queue;
	}
	
	
	public ObservableList<Item> getObservableItems(){
		ObservableList<Item> items=FXCollections.observableArrayList();
		Iterator<String> keys=cart.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			Item thisItem=cart.get(key);
			items.add(thisItem);
		}
		return items;
	}
	
	public ObservableMap<String, Item> getCart() {
		return cart;
	}

	
	
}







