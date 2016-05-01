package store;
/* Name:Ziyi Jin
 * MacID:jinz27
 * Student number:1402029
 * 
 * Name:Chaoyi Kuang
 * MacID:kuangc
 * Student number:1402031
 * 
 * Name:Jiahao Li
 * MacID:li577
 * Student number:1416646
 * 
 * Description:This CD class is inherited from Item class and Audio class and stores the information for the CD
 * */


public class CD extends Audio{

	//Constructor
	public CD(int No,String name,String authorname,int p,int quantity,String type ){
		super(No,name,p,authorname,quantity);
		Type=type;
		
	}
	
    @Override public int getPrice(){
		return (int)(price);//That's the price before adding  environment tax. 
		//It's better not to add tax now and calculate the Etax and HST together when checking out.
		//it also does not make sense if we add the price before checking out.
	}
    
    
    @Override public String getInfo(){//Based on the value of Type(CD or MP3) print the list of Items
    	return sNo+","+Name+","+authorName+","+price+","+Quantity+","+Type;
	    }
    
    @Override public String getType(){
		return Type;
	}
}
