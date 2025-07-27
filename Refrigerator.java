
public class Refrigerator extends Product{
	private String doorDesign; //door design
	private String color; //color of refrigerator
	private int capacity; //capacity of refrigerator
	
	//constructor
	public Refrigerator(String productName, double price, int quantityAvailable, int itemNum, 
			String doorDesign, String color, int capacity) {
		super (productName, price, quantityAvailable, itemNum);
		this.doorDesign = doorDesign;
		this.color = color;
		this.capacity = capacity;
	}

	//getters and setters
	public String getDoorDesign() {
		return doorDesign;
	}

	public void setDoorDesign(String doorDesign) {
		this.doorDesign = doorDesign;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	//calculate value of stock
	public double calculateStockValue() {
		return super.TotalInventoryValue();
	}
	
	@Override
	public String toString() {
	        return "Item number         : " + getItemNum()
	             + "\nProduct name        : " + getProductName()
	             + "\nDoor design         : " + getDoorDesign()
	             + "\nColor               : " + getColor()
	             + "\nCapacity (in Litres): " + getCapacity()
	             + "\nQuantity available  : " + getQuantityAvailable()
	             + "\nPrice (RM)          : " + getPrice()
	             + "\nInventory value (RM): " + calculateStockValue()
	             + "\nProduct status      : " + (isStatusProduct() ? "Active" : "Not Active");
	    }
	
	
	
}
