
public class TV extends Product {

	private String screenType;
	private String resolution;
	private double displaySize;
	
	//constructor
	public TV (String productName, double price, int quantityAvailable, int itemNum,
			String screenType, String resolution, double displaySize) {
		super(productName, price, quantityAvailable, itemNum);
		this.screenType = screenType;
		this.resolution = resolution;
		this.displaySize = displaySize;
	}

	//getters and setters
	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public double getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(double displaySize) {
		this.displaySize = displaySize;
	}
	
	//calculate the value of stock
	public double calculateStockValue() {
		return super.TotalInventoryValue();
	}
	
	@Override
	public String toString() {
	        return "Item number         : " + getItemNum()
	             + "\nProduct name        : " + getProductName()
	             + "\nScreen Type         : " + getScreenType()
	             + "\nResolution          : " + getResolution()
	             + "\nDisplay Size        : " + getDisplaySize()
	             + "\nQuantity available  : " + getQuantityAvailable()
	             + "\nPrice (RM)          : " + getPrice()
	             + "\nInventory value (RM): " + calculateStockValue()
	             + "\nProduct status      : " + (isStatusProduct() ? "Active" : "Not Active");
	    }

}

