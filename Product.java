
public abstract class Product {
	private String productName; //product name
	private double price; //product price
	private int quantityAvailable; //quantity available in stock
	private int itemNum; //id of the item
	private boolean statusProduct = true; //status of product
	
	
	public Product(boolean statusProduct) {
		this.statusProduct = statusProduct;
	}
	
	// constructors
	public Product(String productName, double price, int quantityAvailable, int itemNum) {
		this.productName = productName;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.itemNum = itemNum;
		this.statusProduct = true;
	}

	// getters and setters
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantityAvailable () {
		return quantityAvailable;
	}


	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}


	public int getItemNum() {
		return itemNum;
	}


	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}


	public boolean isStatusProduct() {
		return statusProduct;
	}


	public void setStatusProduct(boolean statusProduct) {
		this.statusProduct = statusProduct;
	}
	
	public double TotalInventoryValue() {
		return price * quantityAvailable;
	}
	
	// add stock
	public void addQuantityStock (int itemQuantity) {
		
		if (this.statusProduct) {
			this.quantityAvailable += itemQuantity;
			System.out.println("Successful Add Stock: )");
		}
		else {
			System.out.println("Cannot Add Stock : (");
		}
	}
	
	// deduct stock
	public void deductQuantityStock (int itemQuantity) {
		this.quantityAvailable -= itemQuantity;
		if (this.quantityAvailable < 0) {
			this.quantityAvailable = 0;
		    System.out.println("No Enough Stock : ( ");
		    
		}
		else
			System.out.println("Successful Deduct Stock : )");
	}
		
	@Override
	public String toString() {
		
		    return "Item number           : " + getItemNum() 
		         + "\nProduct name        : " + getProductName()
		         + "\nQuantity available  : " + getQuantityAvailable()
		         + "\nPrice (RM)          : " + getPrice()
		         + "\nInventory value (RM): " + TotalInventoryValue()
		         + "\nProduct status      : " + (isStatusProduct() ? "Active" : "Not Active");
		}
	
	}