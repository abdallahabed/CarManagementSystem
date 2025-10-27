package structphase2;

import javafx.scene.control.Button;

public class Brand {
	private String brand;
	private LinkedList<Car> list = new LinkedList<>();
	 Button viewBT = new Button("view");

	public Brand(String brand) {
		this.brand = brand;

		brandActin();
	}

	public Brand(String brand, Car car) {

		this.brand = brand;
		list.addLast(car);
		brandActin();

	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCar(Car car) {
		list.addLast(car);
	}

	public Button getViewBT() {
		return viewBT;
	}

	public void setViewBT(Button viewBT) {
		this.viewBT = viewBT;
	}

	public LinkedList<Car> getList() {
		return list;
	}

	public void setList(LinkedList<Car> linkedList) {
		this.list = linkedList;
	}

	public void brandActin() {
		viewBT.setOnAction(e -> {
			getList().sortList();
			if (Main.flag == false)
				new CarsInfo(this);
			else {
				new AdminCarsInfo(this);
			}
		});

	}

	public int compareTo(Brand o) {
		if (this.getBrand().compareTo(o.getBrand()) >= 1) {
			return 1;

		}

		else if (this.getBrand().compareTo(o.getBrand()) < 1) {
			return -1;

		}

		return 0;
	}
}