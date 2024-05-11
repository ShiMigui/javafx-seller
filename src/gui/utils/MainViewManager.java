package gui.utils;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class MainViewManager {
    private static Stage stage;
    private static VBox stageBox;
    private static Node stageMenu;

    private MainViewManager() {
    }

    public static void loadViewInIndex(String relative, String name) throws IOException {
	Pane content = WindowManager.getResource(relative);

	stageBox.getChildren().clear();
	stageBox.getChildren().add(stageMenu);
	stageBox.getChildren().addAll(content.getChildren());

	stage.setTitle(name);
    }

    public static Stage getStage() {
	return stage;
    }

    public static Scene getScene() {
	return stage.getScene();
    }

    public static void setStage(Stage st) throws IOException {
	if (stage != null)
	    throw new RuntimeException("There is already a open stage!");

	stage = st;
	if (st.getScene() == null) {
	    ScrollPane pane = WindowManager.getResource("/gui/IndexView.fxml");
	    pane.setFitToHeight(true);
	    pane.setFitToWidth(true);
	    stageBox = (VBox) pane.getContent();
	    stageMenu = stageBox.getChildren().get(0);

	    st.setScene(new Scene(pane));
	    st.setTitle("Bem-vindo(a)");
	    st.show();
	}
    }
}
