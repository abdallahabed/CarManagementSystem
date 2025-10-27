package structphase2;

public class Car {

	private String model;
	private Brand brand = new Brand(null);
	private int year;
	private String color;
	private double price;

	public Car(String model, String brand, int year, String color, double price) {
		this.model = model;
		this.brand.setBrand(brand);
		this.year = year;
		this.color = color;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int compareTo(Car o) {
		if (this.getYear() >= (o.getYear()))
			return 1;
		else if (this.getYear() < (o.getYear()))
			return -1;

		return 0;
	}

}
