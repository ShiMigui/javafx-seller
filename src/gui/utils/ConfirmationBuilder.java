package gui.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ConfirmationBuilder {
    protected Alert alert;
    protected static ConfirmationBuilder builder;

    private ConfirmationBuilder() {
    }

    public static ConfirmationBuilder getInstance() {
	if (builder == null)
	    builder = new ConfirmationBuilder();

	if (builder.alert == null)
	    builder.alert = new Alert(AlertType.CONFIRMATION);

	return builder;
    }

    public ConfirmationBuilder title(String title) {
	alert.setTitle(title);
	return this;
    }

    public ConfirmationBuilder content(String content) {
	alert.setContentText(content);
	return this;
    }

    public Optional<ButtonType> showAndWait() {
	return alert.showAndWait();
    }
}
