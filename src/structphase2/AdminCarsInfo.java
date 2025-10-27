package structphase2;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AdminCarsInfo extends Stage {
	BorderPane bPane = new BorderPane();
	Button addBT = new Button("add");
	Button deleteBT = new Button("delete");
	Button nextBT = new Button("Next");
	Button privousBT = new Button("previos");
	TextField modelTF = new TextField("Model");
	TextField yearTF = new TextField("year");
	TextField colorTF = new TextField("color");
	TextField priceTF = new TextField("price");
	Button searchBT = new Button("search");
	HBox textFiledHB = new HBox(10, modelTF, yearTF, colorTF, priceTF, searchBT);

	HBox buttonsHB = new HBox(10, addBT, deleteBT);
	VBox vBox = new VBox(10, textFiledHB, buttonsHB);

	public AdminCarsInfo(Brand l) {

		l.getList().sortList();

		Label brandL = new Label(l.getBrand());
		VBox topVBox = new VBox(10, brandL);
		bPane.setTop(topVBox);
		topVBox.setAlignment(Pos.CENTER);
		brandL.setFont(new Font("Verdana", 15));
		TableView<Car> tableView = new TableView<>();
		tableView.setEditable(true);
		TableColumn<Car, String> modelCol = new TableColumn<>("model");
		modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
		modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
		modelCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car, String>>() {

			@Override
			public void handle(CellEditEvent<Car, String> e) {
				Car car = e.getRowValue();
				car.setModel(e.getNewValue());
			}
		});

		TableColumn<Car, Integer> yearCol = new TableColumn<>("year");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
		yearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		yearCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car, Integer>>() {

			@Override
			public void handle(CellEditEvent<Car, Integer> e) {
				Car car = e.getRowValue();
				car.setYear(e.getNewValue());
//				tableView.getItems().remove(car);
//				l.getList().sortList();
//				int index = l.getList().search(car);
//				tableView.getItems().add(index, car);
			}

		});
		TableColumn<Car, String> colorCol = new TableColumn<>("color");
		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
		colorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		colorCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car, String>>() {

			@Override
			public void handle(CellEditEvent<Car, String> e) {
				Car car = e.getRowValue();
				car.setColor(e.getNewValue());

			}
		});
		TableColumn<Car, Double> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		priceCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Car, Double>>() {

			@Override
			public void handle(CellEditEvent<Car, Double> e) {
				Car car = e.getRowValue();
				car.setPrice(e.getNewValue());
			}
		});
		tableView.getColumns().addAll(modelCol, yearCol, colorCol, priceCol);
		bPane.setLeft(privousBT);
		bPane.setAlignment(privousBT, Pos.CENTER);
		bPane.setRight(nextBT);
		bPane.setAlignment(nextBT, Pos.CENTER);

		deleteBT.setOnAction(e -> {
			// tableView.getSelectionModel().getSelectedItem() return object location.
			l.getList().remove(tableView.getSelectionModel().getSelectedItem());// remove it from saved data
			tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());// remove it from table view

		});

		addBT.setOnAction(e -> {

			String model = modelTF.getText();
			int year = Integer.parseInt(yearTF.getText());
			String color = colorTF.getText();
			double price = Double.parseDouble(priceTF.getText());
			Car car = new Car(model, l.getBrand(), year, color, price);
			l.getList().addLast(car);
//				l.getList().sortList();
			// to know where to put it in the table
			int index = l.getList().search(car);

			tableView.getItems().add(index, car);
			modelTF.setText("");
			yearTF.setText("");
			colorTF.setText("");
			priceTF.setText("");

		});
		nextBT.setOnAction(e -> {
			Brand brand;
			// that mean you reached the end so return to the first one.
			if (StartClass.linked.search(l) == StartClass.linked.getCount() - 1)
				brand = StartClass.linked.get(0);
			else
				brand = StartClass.linked.get(StartClass.linked.search(l) + 1);

			this.hide();
			new AdminCarsInfo(brand);
		});
		privousBT.setOnAction(e -> {
			Brand brand;
			// that mean you are at the biggening so return to the last one.
			if (StartClass.linked.search(l) == 0)
				brand = StartClass.linked.get(StartClass.linked.getCount() - 1);
			else
				brand = StartClass.linked.get(StartClass.linked.search(l) - 1);

			this.hide();
			new AdminCarsInfo(brand);
		});

		searchBT.setOnAction(e -> {
			LinkedList<Car> list = l.getList();
//			if (searchTF.getText().isEmpty()) {
//				this.hide();
//				new AdminCarsInfo(l);
//			}
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

		for (int i = 0; i < l.getList().getCount(); i++) {

			tableView.getItems().add((Car) l.getList().get(i));
		}
		bPane.setCenter(tableView);
		bPane.setBottom(vBox);
		Scene scene = new Scene(bPane, 700, 700);
		this.setScene(scene);
		this.show();

	}

}
