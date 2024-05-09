package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			ScrollPane parent = new FXMLLoader(getClass().getResource("/gui/IndexView.fxml")).load();

			parent.setFitToHeight(true);
			parent.setFitToWidth(true);

			stage.setScene(new Scene(parent));
			stage.setTitle("Index - Seller");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
