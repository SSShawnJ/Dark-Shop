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
 * Description:This Audio class is inherited from Item class
 * */


public class Audio extends Item{
	//Constructor
	public Audio(int No,String name,int p,String authorname,int quantity){
		sNo=No;
		Name=name;
		price=p;
		authorName=authorname;
		Quantity=quantity;
	}
	
	//Getter and setter
	
	@Override
	public int getSNo() {
		return sNo;
	}

	@Override
	public void setSNo(int sNo) {
		this.sNo=sNo;
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
	public int getPrice() {
		
		return this.price;
	}
	@Override
	public void setPrice(int price) {
		this.price=price;
		
	}
	@Override
	public int getQuantity() {
		
		return this.Quantity;
	}
	@Override
	public void setQuantity(int q) {
		this.Quantity=q;
		
	}
	@Override
	public String getType() {
		
		return "Audio";
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String artistName) {
		this.authorName = artistName;
	}
	
	public String getInfo(){//Returns sNo, Name, Artistname, etc in a string
		return sNo+","+Name+","+authorName+","+price+","+Quantity; 
	}
	
	
}
