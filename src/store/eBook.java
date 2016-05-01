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
 * Description:This eBook class is inherited from Item class and readable class and stores the information for the eBook
 * */


public class eBook extends Readable {
	
	//Constructor
	public eBook(int No,String name,String authorname,int p,int quantity,String type ){
		super(No,name,p,authorname,quantity);
		Type=type;
		
	}
	
	@Override public int getPrice(){
		return price; // override and only call the parent��s constructor to get the base price.
	}
	
	
	@Override public String  getInfo(){
		return sNo+","+Name+","+authorName+","+price+","+Quantity+","+Type;
	}
	
	@Override public String getType(){
		return Type;
	}

}
