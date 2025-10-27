package structphase2;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OrdersTableView extends Stage {
	BorderPane bp = new BorderPane();
	TableView<Orders> tableView = new TableView<>();

	public OrdersTableView() {

		TableColumn<Orders, String> customerNameCol = new TableColumn<>("Name");
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
		TableColumn<Orders, String> customerNoCol = new TableColumn<>("No");
		customerNoCol.setCellValueFactory(new PropertyValueFactory<>("CustomerNo"));

		TableColumn<Orders, String> modelCol = new TableColumn<>("model");
		modelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));

		TableColumn<Orders, String> BrandCol = new TableColumn<>("brand");
		BrandCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));

		TableColumn<Orders, Integer> yearCol = new TableColumn<>("year");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("Year"));

		TableColumn<Orders, String> colorCol = new TableColumn<>("Color");
		colorCol.setCellValueFactory(new PropertyValueFactory<>("Color"));

		TableColumn<Orders, Double> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

		TableColumn<Orders, String> acceptCol = new TableColumn<>("accept");
		acceptCol.setCellValueFactory(new PropertyValueFactory<>("acceptBT"));

		TableColumn<Orders, String> decelineCol = new TableColumn<>("decline");
		decelineCol.setCellValueFactory(new PropertyValueFactory<>("declineBT"));

		for (int i = 0; i < StartClass.queueList.size(); i++) {
			tableView.getItems().add(StartClass.queueList.dequeue());
		}

		tableView.getColumns().add(customerNameCol);
		tableView.getColumns().add(customerNoCol);
		tableView.getColumns().add(modelCol);
		tableView.getColumns().add(BrandCol);
		tableView.getColumns().add(yearCol);
		tableView.getColumns().add(colorCol);
		tableView.getColumns().add(priceCol);
		tableView.getColumns().add(acceptCol);
		tableView.getColumns().add(decelineCol);
		bp.setCenter(tableView);
		Scene scene = new Scene(bp, 700, 700);
		this.setScene(scene);
		this.show();

	}

}
