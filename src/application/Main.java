package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent parent = new FXMLLoader(getClass().getResource("/gui/IndexView.fxml")).load();
			
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
