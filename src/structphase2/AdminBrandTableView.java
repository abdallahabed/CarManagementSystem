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

public class AdminBrandTableView extends Stage {
	BorderPane bp = new BorderPane();
	TableView<Brand> tableView = new TableView<>();
	Button addBT = new Button("add");
	Button deleteBT = new Button("delete");
	Button searchBT = new Button("search");
	TextField BrandTF = new TextField("Brand");
	HBox hBox = new HBox(10, addBT, deleteBT);
	TextField searchTF = new TextField("Brand");
	HBox searchHB = new HBox(10, searchTF, searchBT);
	VBox vBox = new VBox(10, BrandTF, hBox);

	public AdminBrandTableView() {
		tableView.setEditable(true);
		TableColumn<Brand, String> locationCol = new TableColumn<>("Brand");
		locationCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		locationCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Brand, String>>() {
			@Override
			public void handle(CellEditEvent<Brand, String> e) {
				Brand brand = e.getRowValue();
				// update location data ...
				brand.setBrand(e.getNewValue());
				tableView.getItems().remove(brand);
				StartClass.linked.sortList();
				int index = StartClass.linked.search(brand);
				tableView.getItems().add(index, brand);

			}
		});
		TableColumn<Brand, String> viewCol = new TableColumn<>("view");
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBT"));

		searchBT.setOnAction(e -> {
			if (searchTF.getText().isEmpty()) {
				this.hide();
				new AdminBrandTableView();
			}
			for (int i = 0; i < StartClass.linked.getCount(); i++) {
				Brand brand = StartClass.linked.get(i);
				if (brand.getBrand().contains(searchTF.getText()) == false) {
					// remove all data that does not have the searched name on it .
					tableView.getItems().removeAll(StartClass.linked.get(i));
				}
			}
		});

		for (int i = 0; i < StartClass.linked.getCount(); i++) {
			tableView.getItems().add(StartClass.linked.get(i));
		}

		deleteBT.setOnAction(e -> {
			// tableView.getSelectionModel().getSelectedItem() return object location.
			StartClass.linked.remove(tableView.getSelectionModel().getSelectedItem());// remove it from saved data
			tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());// remove it from table view
		});

		addBT.setOnAction(e -> {
			Brand brand = new Brand(BrandTF.getText());
			if (StartClass.linked.search(brand.getBrand()) == -1) {
				StartClass.linked.addLast(brand);
				StartClass.linked.sortList();
				// after using sort the index of it will change
				int index = StartClass.linked.search(brand.getBrand());
				// to know where to put it in the table
				tableView.getItems().add(index, brand);
				BrandTF.setText("brand");
			} else {
				System.out.println("brand already exist");
			}
			StartClass.linked.sortList();
		});

		bp.setTop(searchHB);
		bp.setAlignment(searchTF, Pos.CENTER);
		tableView.getColumns().add(locationCol);
		tableView.getColumns().add(viewCol);
		bp.setCenter(tableView);
		vBox.setAlignment(Pos.CENTER);
		bp.setAlignment(vBox, Pos.CENTER);

		bp.setBottom(vBox);
		Scene scene = new Scene(bp, 450, 450);
		this.setScene(scene);
		this.show();
	}

}
