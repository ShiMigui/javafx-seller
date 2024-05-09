package gui.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewController {
	private static Stage stage;

	public static void loadView(String relative, String name) {
		try {
			Pane content = getResource(relative);
			
			VBox main = (VBox) ((ScrollPane) getScene().getRoot()).getContent();
			Node menu = main.getChildren().get(0);

			main.getChildren().clear();
			main.getChildren().add(menu);
			main.getChildren().addAll(content.getChildren());

			setTitleStage(name);
		} catch (IOException e) {
			AlertController.setType(AlertType.ERROR);
			AlertController.showAlert("Error loading resource", e.getMessage());
			e.printStackTrace();
		}
	}

	public static <N extends Region> N getResource(String relative) throws IOException {
		return new FXMLLoader(ViewController.class.getResource(relative)).load();
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getScene() {
		return stage.getScene();
	}

	public static void setTitleStage(String name) {
		stage.setTitle(name);
	}

	public static synchronized void setStage(Stage value) {
		if (stage != null)
			throw new RuntimeException("There is already a open stage!");

		stage = value;
		if (getScene() == null) {
			try {
				ScrollPane parent = getResource("/gui/IndexView.fxml");
				parent.setFitToHeight(true);
				parent.setFitToWidth(true);

				stage.setScene(new Scene(parent));
				stage.show();

				setTitleStage("Bem-vindo(a)");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
