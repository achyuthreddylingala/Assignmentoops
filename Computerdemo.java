package javaactivityoops;

import java.util.Objects;

public class Computerdemo {
	private String brand;
	private String model;
	private long SN;
	private double price;
	private static int numberOfComputers = 0;

	public Computerdemo(String brand, String model, long SN, double price) {
		this.brand = brand;
		this.model = model;
		this.SN = SN;
		this.price = price;
		numberOfComputers++;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getSN() {
		return SN;
	}

	public void setSN(long SN) {
		this.SN = SN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static int findNumberOfCreatedComputers() {
		return numberOfComputers;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Computer computer = (Computer) obj;
		return SN == computer.SN && Double.compare(computer.price, price) == 0 && Objects.equals(brand, computer.brand)
				&& Objects.equals(model, computer.model);
	}

	@Override
	public String toString() {
		return "Computer{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + ", SN=" + SN + ", price=" + price
				+ '}';
	}
}
