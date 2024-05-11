package gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBuilder {
    private Alert alert;

    private AlertBuilder() {
    }

    public static AlertBuilder type(AlertType type) {
	AlertBuilder builder = new AlertBuilder();
	builder.alert = new Alert(type);
	return builder;
    }

    public AlertBuilder title(String title) {
	alert.setTitle(title);
	return this;
    }

    public AlertBuilder content(String content) {
	alert.setContentText(content);
	return this;
    }

    public void show() {
	alert.show();
    }
}
