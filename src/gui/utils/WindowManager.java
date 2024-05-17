package gui.utils;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.interfaces.IController;

public class WindowManager {
    private Stage window;
    private FXMLLoader loader;

    public void window(String relative, String name, Stage owner, Consumer<Stage> config) throws IOException {
	loader = getLoader(relative);

	Scene scene = new Scene(loader.load());
	window = new Stage();

	window.setTitle(name);
	window.setScene(scene);
	window.initOwner(owner);

	config.accept(window);
    }

    public void windowModal(String relative, String name) throws IOException {
	window(relative, name, MainViewManager.getStage(), (stage) -> {
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	});
    }

    public static <P extends Region> P getResource(String relative) throws IOException {
	return FXMLLoader.load(WindowManager.class.getResource(relative));
    }

    public static FXMLLoader getLoader(String relative) {
	return new FXMLLoader(WindowManager.class.getResource(relative));
    }

    public <C extends IController> C getController() {
	return loader.getController();
    }

    public Stage getWindow() {
	return window;
    }
}
