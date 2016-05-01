package store;

public class Book extends Readable{
	
	//Constructor
	public Book(int No,String name,String authorname,int p,int quantity,String type ){
		super(No,name,p,authorname,quantity);
		Type=type;
		
	}
	@Override public int getPrice(){
		return (int)(price);//That's the price before adding  environment tax. 
		//It's better not to add tax now and calculate the Etax and HST together when checking out.
		//it also does not make sense if we add the price before checking out.
	}
	
	
	//Based on the value of Type(Book or eBook)
    //print the list of Items
	@Override public String  getInfo(){
		return sNo+","+Name+","+authorName+","+price+","+Quantity+","+Type;
	}
	
	@Override public String getType(){
		return Type;
	}

}
