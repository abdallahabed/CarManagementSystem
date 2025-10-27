package structphase2;

import javafx.scene.control.Button;

public class Orders {
	private String status;
	private Car car;
	private Customer customer;
	private Button acceptBT = new Button("accept");
	private Button declineBT = new Button("Decline");

	public Orders(String status, Car car, Customer customer) {
		this.status = status;
		this.car = car;
		this.customer = customer;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModel() {
		return car.getModel();
	}

	public int getYear() {
		return car.getYear();
	}

	public String getBrand() {
		return car.getBrand().getBrand();
	}

	public String getColor() {
		return car.getColor();
	}

	public double getPrice() {
		return car.getPrice();
	}

	public String getCustomerName() {
		return customer.getName();
	}

	public int getCustomerNo() {
		return customer.getMobileNo();
	}

	public Button getAcceptBT() {
		return acceptBT;
	}

	public void setAcceptBT(Button acceptBT) {
		this.acceptBT = acceptBT;
	}

	public Button getDeclineBT() {
		return declineBT;
	}

	public void setDeclineBT(Button declineBT) {
		this.declineBT = declineBT;
	}

	public void acceptAction() {
		acceptBT.setOnAction(e -> {
			setStatus("Finishid");

			StartClass.stackList.push(car);
		});
	}

	public void declineAction() {
		declineBT.setOnAction(e -> {
			setStatus("inProcess");
			StartClass.queueList.enqueue(this);
		});

	}

}
