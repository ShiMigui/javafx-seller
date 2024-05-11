package gui.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class WindowManager {
    private WindowManager() {
    }

    public static Stage createWindow(String relative, String name, Stage owner) throws IOException {
	Parent parent = getResource(relative);
	Scene scene = new Scene(parent);
	Stage stage = new Stage();

	stage.setTitle(name);
	stage.setScene(scene);
	stage.initOwner(owner);

	return stage;
    }

    public static Stage createModalWindow(String relative, String name, Stage owner) throws IOException {
	Stage stage = createWindow(relative, name, owner);

	stage.setResizable(false);
	stage.initModality(Modality.WINDOW_MODAL);

	return stage;
    }

    public static <P extends Region> P getResource(String relative) throws IOException {
	return FXMLLoader.load(WindowManager.class.getResource(relative));
    }
}
