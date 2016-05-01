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
 * Description:This MP3 class is inherited from Item class and Audio class and stores the information for the MP3
 * */


public class MP3 extends Audio {
	
	//Constructor
	public MP3(int No,String name,String authorname,int p,int quantity,String type ){
		super(No,name,p,authorname,quantity);
		Type=type;
		
	}
	
	@Override public int getPrice(){// override and only call the parent��s constructor to get the base price.
		return price;
	}
	
	    
	@Override  public String getInfo(){//Based on the value of Type(CD or MP3) print the list of Items
	    	return sNo+","+Name+","+authorName+","+price+","+Quantity+","+Type;
		    }
	
	@Override public String getType(){
		return Type;
	}

}
