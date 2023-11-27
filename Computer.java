package javaactivityoops;

public class Computer {

	public String brand;
	public String model;
	public String serialNumber;
	public double price;
	public long SN;

	public Computer(String brand, String model, String serialNumber, double price) {
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public void displayComputerInfo(int computerNumber) {
		System.out.println("Computer # " + computerNumber);
		System.out.println("Brand: " + brand);
		System.out.println("Model: " + model);
		System.out.println("SN: " + serialNumber);
		System.out.println("Price: $" + price);
	}

	public void updateBrand(String newBrand) {
		this.brand = newBrand;
	}

	public void updateModel(String newModel) {
		this.model = newModel;
	}

	public void updateSerialNumber(String newSerialNumber) {
		this.serialNumber = newSerialNumber;
	}

	public void updatePrice(double newPrice) {
		this.price = newPrice;
	}

}
