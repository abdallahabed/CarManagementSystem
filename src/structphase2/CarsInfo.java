package structphase2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CarsInfo extends Stage {
	BorderPane bPane = new BorderPane();
	Button orderBT = new Button("order");

	Button nextBT = new Button("Next");
	Button privousBT = new Button("previos");
	HBox buttonsHB = new HBox(10, orderBT);
	VBox vBox = new VBox(10, buttonsHB);

	Button searchBT = new Button("search");
//	TextField searchTF = new TextField();
	TextField modelTF = new TextField("Model");
	TextField yearTF = new TextField("year");
	TextField colorTF = new TextField("color");
	TextField priceTF = new TextField("price");
	HBox textFiledHB = new HBox(10, modelTF, yearTF, colorTF, priceTF, searchBT);

	public CarsInfo(Brand l) {

		l.getList().sortList();

		Label brandL = new Label(l.getBrand());
		VBox topVBox = new VBox(10, brandL, textFiledHB);
		bPane.setTop(topVBox);
		textFiledHB.setAlignment(Pos.CENTER);
		topVBox.setAlignment(Pos.CENTER);
		brandL.setFont(new Font("Verdana", 15));
		TableView<Car> tableView = new TableView<>();
		TableColumn<Car, String> modelCol = new TableColumn<>("model");
		modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

		TableColumn<Car, Integer> yearCol = new TableColumn<>("year");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn<Car, String> colorCol = new TableColumn<>("color");
		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));

		TableColumn<Car, Double> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		tableView.getColumns().addAll(modelCol, yearCol, colorCol, priceCol);
		bPane.setLeft(privousBT);
		bPane.setAlignment(privousBT, Pos.CENTER);
		bPane.setRight(nextBT);
		bPane.setAlignment(nextBT, Pos.CENTER);

		nextBT.setOnAction(e -> {
			Brand brand;
			// that mean you reached the end so return to the first one.
			if (StartClass.linked.search(l) == StartClass.linked.getCount() - 1)
				brand = StartClass.linked.get(0);
			else
				brand = StartClass.linked.get(StartClass.linked.search(l) + 1);

			this.hide();
			new CarsInfo(brand);
		});
		privousBT.setOnAction(e -> {
			Brand brand;
			// that mean you are at the biggening so return to the last one.
			if (StartClass.linked.search(l) == 0)
				brand = StartClass.linked.get(StartClass.linked.getCount() - 1);
			else
				brand = StartClass.linked.get(StartClass.linked.search(l) - 1);

			this.hide();
			new CarsInfo(brand);
		});

		searchBT.setOnAction(e -> {
			LinkedList<Car> list = l.getList();
			if (modelTF.getText().isEmpty()) {
				this.hide();
				new CarsInfo(l);
			}
			String year;
			String price;
			for (int i = 0; i < list.getCount(); i++) {
				year = String.valueOf(((Car) list.get(i)).getYear());
				price = String.valueOf(((Car) list.get(i)).getPrice());
				if (((Car) list.get(i)).getModel().contains(modelTF.getText()) == false) {
					if (year.contains(yearTF.getText()) == false) {
						if (price.contains(priceTF.getText()) == false) {
							if (((Car) list.get(i)).getColor().contains(colorTF.getText()) == false) {
								tableView.getItems().remove(list.get(i));
							}
						}
					}
				}
			}
		});
		LinkedList<Car> list = l.getList();
		for (int i = 0; i < list.getCount(); i++) {

			tableView.getItems().add((Car) list.get(i));
		}

		orderBT.setOnAction(e -> {
			// tableView.getSelectionModel().getSelectedItem() return object location.
			// ask if the user want for sure to order the car
			Label namLabel = new Label("customer name :");
			Label noLabel = new Label("customer No :");
			TextField nameTextField = new TextField();
			TextField NoTextField = new TextField();
			Button confirmButton = new Button("confirm");

			GridPane gPane = new GridPane();
			gPane.add(namLabel, 0, 0);
			gPane.add(nameTextField, 1, 0);
			gPane.add(noLabel, 0, 1);
			gPane.add(NoTextField, 1, 1);

			gPane.add(confirmButton, 2, 1);
			Scene scene = new Scene(gPane, 650, 650);
			this.setScene(scene);
			this.show();
			Car car = tableView.getSelectionModel().getSelectedItem();
			confirmButton.setOnAction(ex -> {
				Customer customer = new Customer(nameTextField.getText(), Integer.parseInt(NoTextField.getText()));
				StartClass.queueList.enqueue(new Orders("inProcess", car, customer)); // add it to stack
				l.getList().remove(car);
				tableView.getItems().remove(car);// remove it from table
				this.hide(); // view
			});

		});

		bPane.setCenter(tableView);
		bPane.setBottom(vBox);
		Scene scene = new Scene(bPane, 700, 700);
		this.setScene(scene);
		this.show();

	}

}
