package modul;

public class productdata {

	
	private  String id;
	private String name;
	private String type;
	private String status;
	private int stock;
	private double price;
	private  String image;
	private int quantity=0;
	private int idO;
	public int getIdO() {
		return idO;
	}

	public void setIdO(int idO) {
		this.idO = idO;
	}

	public productdata(String id, String name,String type,String status, int stock, double price, String image) {
		this.id = id;
		this.name = name;
		this.type=type;
		this.stock = stock;
		this.price = price;
		this.image = image;
		this.status=status;
	}
	
	public productdata(String id, String name, double price, String image) { 
		this.id = id;
		this.name = name;
		this.price=price;
		this.image=image;

		 
	}
	public productdata( int idO,String name,int quantity,double price) { 
		this.idO=idO;
		this.name = name;
		this.price=price;
		
		this.quantity=quantity;
	

	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public   String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public  String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
