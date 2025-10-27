package structphase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	static Button adminBT = new Button("Admin");
	static Button UserBT = new Button("User");
	Button loadButton = new Button("load");
	HBox hBox = new HBox(10, adminBT, UserBT);
	static boolean flag = false;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bP = new BorderPane();
		Label mainL = new Label(
				"\t\t\tWelcome to " + "\n" + "Car Agencey System: Comp 242 Project 2" + "\n" + "\t\tSelect user type:");
		mainL.setFont(new Font("Verdana", 15));
		bP.setAlignment(mainL, Pos.CENTER);
		bP.setTop(mainL);
		hBox.setAlignment(Pos.CENTER);
		bP.setAlignment(hBox, Pos.CENTER);
		bP.setCenter(hBox);
		bP.setBottom(loadButton);
		Scene scene = new Scene(bP, 450, 450);
		bP.setStyle("-fx-background-color: white;");
		adminBT.setOnAction(e -> {
			flag = true;
			new StartClass();
		});
		UserBT.setOnAction(e -> {
			flag = false;
			new UserTableView();
		});
		loadButton.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			File load = fileChooser.showOpenDialog(null);
			load = new File(load.getAbsolutePath());
			readFromFile(load);

		});

		stage.setTitle("honor list");
		stage.setScene(scene);
		stage.show();

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
			while ((line = br.readLine()) != null) {
				brand = tkz[0];
				model = tkz[1];
				year = Integer.valueOf(tkz[2].trim());
				color = tkz[3];
				price = Double.parseDouble(tkz[4].substring(0, 4)) * 1000;
				Car car = new Car(model, brand, year, color, price);
				if (StartClass.linked.search(brand) == -1) {
					StartClass.linked.addFisrt(new Brand(brand, car));
				} else if (StartClass.linked.search(brand) != -1) {
					StartClass.linked.get(StartClass.linked.search(brand)).setCar(car);
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

}
