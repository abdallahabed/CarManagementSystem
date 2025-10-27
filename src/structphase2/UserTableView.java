package structphase2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserTableView extends Stage {

	BorderPane bp = new BorderPane();
	TableView<Brand> tableView = new TableView<>();
	Button searchBT = new Button("search");
	TextField searchTF = new TextField("Brand");
	HBox searchHB = new HBox(10, searchTF, searchBT);

	public UserTableView() {
		TableColumn<Brand, String> brandCol = new TableColumn<>("Brand");
		brandCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		TableColumn<Brand, String> viewCol = new TableColumn<>("view");
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBT"));
		
		searchBT.setOnAction(e -> {
			if (searchTF.getText().isEmpty()) {
				this.hide();
				new UserTableView();
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

		bp.setTop(searchHB);
		bp.setAlignment(searchTF, Pos.CENTER);
		tableView.getColumns().add(brandCol);
		tableView.getColumns().add(viewCol);
		bp.setCenter(tableView);
		Scene scene = new Scene(bp, 450, 450);
		this.setScene(scene);
		this.show();
	}

}
