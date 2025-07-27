
public class Microwave extends Product{
	private String sensors; //sensors
	private int powerComsumption; //power of microwave
	private int size; //size of microwave
	
	//constructor
	public Microwave(String productName, double price, int quantityAvailable, int itemNum, 
			String sensors, int powerComsumption, int size) {
		super (productName, price, quantityAvailable, itemNum);
		this.sensors = sensors;
		this.powerComsumption = powerComsumption;
		this.size = size;
	}

	//getters and setters
	public String getSensors() {
		return sensors;
	}

	public void setSensors(String sensors) {
		this.sensors = sensors;
	}

	public int getPowerComsumption() {
		return powerComsumption;
	}

	public void setPowerComsumption(int powerComsumption) {
		this.powerComsumption = powerComsumption;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	//calculate value of stock
	public double calculateStockValue() {
		return super.TotalInventoryValue();
	}
	
	@Override
	public String toString() {
	        return "Item number         : " + getItemNum()
	             + "\nProduct name        : " + getProductName()
	             + "\nSensor              : " + getSensors()
	             + "\nPower(in Watt)      : " + getPowerComsumption()
	             + "\nSize (in Litres)    : " + getSize()
	             + "\nQuantity available  : " + getQuantityAvailable()
	             + "\nPrice (RM)          : " + getPrice()
	             + "\nInventory value (RM): " + calculateStockValue()
	             + "\nProduct status      : " + (isStatusProduct() ? "Active" : "Not Active");
	    }
	
	
	
}
