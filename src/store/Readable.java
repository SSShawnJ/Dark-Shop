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
 * Description:This readable class is inherited from Item class
 * */


public class Readable extends Item {
	

	//Constructor
	public Readable(int No,String name,int p,String authorname,int quantity){
		sNo=No;
		Name=name;
		price=p;
		authorName=authorname;
		Quantity=quantity;
	}
	
	@Override
	public int getSNo() {
		return sNo;
	}

	@Override
	public void setSNo(int sNo) {
		this.sNo=sNo;
	}
	
	public String getInfo(){
		return sNo+","+Name+","+authorName+","+price+","+Quantity;//Returns sNo, Name, Author
		                                             //name, etc in a string
	}
	
	@Override public void setPrice(int price){
		this.price=price;
		
	}
	
	@Override public int getPrice(){
		return price;
		
	}

	public String getType(){
		return "Readable";
	}

	
	@Override
	public String getName() {
		
		return this.Name;
	}
	@Override
	public void setName(String name) {
		this.Name=name;	
	}
	@Override
	public int getsNO() {
		
		return this.sNo;
	}
	@Override
	public void setsNO(int num) {
		this.sNo=num;
	}
	@Override
	public void setQuantity(int q) {
		this.Quantity=q;
		
	}
	
	public int getQuantity(){
		return Quantity;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
