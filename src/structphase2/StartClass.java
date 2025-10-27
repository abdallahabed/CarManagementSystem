package structphase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartClass extends Stage {
	Button startBT = new Button("start");
	Button loadBT = new Button("load");
	Button saveBT = new Button("save");
	Button loadOrdersBT = new Button("load orders");
	Button ordersBT = new Button("show orders");
	Button saveOrdersBT = new Button("save orders");
	static DoubleLinkedList<Brand> linked = new DoubleLinkedList<>();
	static StackList stackList = new StackList();
	static QueueList queueList = new QueueList();
	HBox hBox = new HBox(10, startBT, loadBT, saveBT, ordersBT, loadOrdersBT, saveOrdersBT);

	public StartClass() {

		BorderPane bP = new BorderPane();
		Label mainL = new Label("\t\t\tWelcome to " + "\n" + "Car Agencey System: Comp 242 Project 2");
		mainL.setFont(new Font("Verdana", 15));
		bP.setAlignment(mainL, Pos.CENTER);
		bP.setTop(mainL);
		ImageView imageView = new ImageView("C:\\Users\\ahmad\\eclipse-workspace\\structphase2\\cars.jpg");
		imageView.setFitWidth(250);
		imageView.setFitHeight(250);
		saveOrdersBT.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File load = fileChooser.showOpenDialog(null);
			load = new File(load.getAbsolutePath());
			writeOrders(load);
		});
		loadBT.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			File load = fileChooser.showOpenDialog(null);
			load = new File(load.getAbsolutePath());
			readFromFile(load);

		});

		imageView.setRotate(35);
		hBox.setAlignment(Pos.CENTER);
		bP.setCenter(imageView);
		bP.setBottom(hBox);
		startBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		loadBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");

		saveBT.setStyle(" -fx-background-color: white;-fx-font-size: 12px;" + "    -fx-font-weight: bold;"
				+ "    -fx-text-fill: #333333;"
				+ "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		startBT.setOnAction(e -> {
//			// sort the data before showing it ...
			linked.sortList();
			new AdminBrandTableView();
		});
		saveBT.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			File save = fileChooser.showOpenDialog(null);
			save = new File(save.getAbsolutePath());
			writeInFile(save);
		});

		loadOrdersBT.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File save = fileChooser.showOpenDialog(null);
			save = new File(save.getAbsolutePath());
			readOrders(save);
			System.out.println(stackList.size() + "  " + queueList.size());
		});
		ordersBT.setOnAction(e -> {
			new OrdersTableView();
		});
		Scene scene = new Scene(bP, 450, 450);
		bP.setStyle("-fx-background-color: gray;");
		this.setTitle("honor list");
		this.setScene(scene);
		this.show();

	}

	public void readFromFile(File file) {

		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			String model;
			String brand;
			int year;
			double price;
			String color;
			line = br.readLine();// to skip first row (name ,age .....
			String[] tkz = line.split(",");
			LinkedList<Car> list = new LinkedList<>();
			while ((line = br.readLine()) != null) {
				brand = tkz[0];
				model = tkz[1];
				year = Integer.parseInt(tkz[2].trim());
				color = tkz[3];
				price = Double.parseDouble(tkz[4].substring(0, 4));
				Car car = new Car(model, brand, year, color, price);
				if (StartClass.linked.search(brand) == -1) {
					StartClass.linked.addFisrt(new Brand(brand, car));
				} else if (StartClass.linked.search(brand) != -1) {
					StartClass.linked.get(StartClass.linked.search(brand)).setCar(car);
					;
				}
				tkz = line.split(",");
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	public void lastSoldCars() {
//
//		for (int i = 0; i < 10; i++) {
//			if (stackList.size() > 0) {
//				Car car = (Car) stackList.pop();
//				System.out.println(car.getBrand().getBrand() + " " + car.getModel());
//			}
//		}
//	}

	public void writeOrders(File file) {
		try {
//			LinkedList<Car> list;

			FileWriter fWriter = new FileWriter(file);
			BufferedWriter Bw = new BufferedWriter(fWriter);
			Bw.write("CustomerName,");
			Bw.write("CustomerMobile,");
			Bw.write("Brand");
			Bw.write("Model");
			Bw.write("year,");
			Bw.write("color,");
			Bw.write("price");
			Bw.write("orderStatus");
			Bw.newLine();

			for (int i = 0; i < queueList.size(); i++) {
				Orders order = queueList.dequeue();
				Bw.write(order.getCustomerName());
				Bw.write(order.getCustomerNo());

				Bw.write(order.getBrand() + ",");
				Bw.write(order.getModel() + ",");
				Bw.write(order.getYear() + ",");
				Bw.write(order.getColor() + ",");
				Bw.write(order.getPrice() + "K,");
				Bw.write(order.getStatus());

				Bw.newLine();

			}
			Bw.close();
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeInFile(File file) {
		try {
			LinkedList<Car> list;
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter Bw = new BufferedWriter(fWriter);
			Bw.write("Brand,");
			Bw.write("model,");
			Bw.write("year,");
			Bw.write("color,");
			Bw.write("price");
			Bw.newLine();
			for (int i = 0; i < StartClass.linked.getCount(); i++) {
				list = StartClass.linked.get(i).getList();
				for (int j = 0; j < list.getCount(); j++) {
					Bw.write(linked.get(i).getBrand() + ",");
					Bw.write(((Car) list.get(j)).getModel() + ",");
					Bw.write(((Car) list.get(j)).getYear() + ",");
					Bw.write(((Car) list.get(j)).getColor() + ",");
					Bw.write(((Car) list.get(j)).getPrice() + "K,");
					Bw.newLine();

				}

			}
			Bw.close();
			fWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readOrders(File file) {

		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();

//			CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, OrderDate, OrderStatus
			String CustomerName;
			int CustomerMobile;
			String model;
			String brand;
			int year;
			double price;
			String color;
			Date OrderDate;
			String OrderStatus;
			line = br.readLine();// to skip first row (customerName ,mobileNo .....
			String[] tkz = line.split(",");
			LinkedList<Orders> list = new LinkedList<>();
			while ((line = br.readLine()) != null) {
				CustomerName = tkz[0];
				CustomerMobile = Integer.parseInt(tkz[1].trim());
				brand = tkz[2];
				model = tkz[3];
				year = Integer.parseInt(tkz[4].trim());
				color = tkz[5];
				price = Double.parseDouble(tkz[6].substring(0, 4));
				OrderDate = new SimpleDateFormat("dd/MM/yyyy").parse(tkz[7]);
				OrderStatus = tkz[8].trim();
				Car car = new Car(model, brand, year, color, price);
				Orders orders = new Orders(OrderStatus, car, new Customer(CustomerName, CustomerMobile));

				if (OrderStatus.equals("Finished")) {
					stackList.push(car);
				} else if (OrderStatus.equals("InProcess")) {
					queueList.enqueue(orders);
				}
				tkz = line.split(",");
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
