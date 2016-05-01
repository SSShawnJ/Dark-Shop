package store;



public abstract class Item {
	
	public int sNo;
	public String Name;
	public String authorName;
	public int price;
	public int Quantity;
	public  String Type;
	
	
	public abstract int getSNo();
	public abstract void setSNo(int sNo);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract int getsNO();
	public abstract void setsNO(int num);
	public abstract String getInfo();
	public abstract int getPrice();
	public abstract void setPrice(int price);
	public abstract int getQuantity();
	public abstract void setQuantity(int q);
	public abstract String getType();

}
