package gui.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewController {
    private static Stage index;
    private static VBox indexBox;
    private static Node indexMenu;

    public static void loadViewInIndex(String relative, String name) throws IOException {
	Pane content = getResource(relative);

	indexBox.getChildren().clear();
	indexBox.getChildren().add(indexMenu);
	indexBox.getChildren().addAll(content.getChildren());

	index.setTitle(name);
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

    private static <P extends Region> P getResource(String relative) throws IOException {
	return FXMLLoader.load(ViewController.class.getResource(relative));
    }

    public static Stage getStage() {
	return index;
    }

    public static Scene getScene() {
	return index.getScene();
    }

    public static void setStage(Stage st) throws IOException {
	if (index != null)
	    throw new RuntimeException("There is already a open stage!");

	index = st;
	if (st.getScene() == null) {
	    ScrollPane pane = getResource("/gui/IndexView.fxml");
	    pane.setFitToHeight(true);
	    pane.setFitToWidth(true);
	    indexBox = (VBox) pane.getContent();
	    indexMenu = indexBox.getChildren().get(0);

	    st.setScene(new Scene(pane));
	    st.setTitle("Bem-vindo(a)");
	    st.show();
	}
    }
}
