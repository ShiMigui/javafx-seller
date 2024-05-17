package gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBuilder {
    protected Alert alert;
    protected static AlertBuilder builder;

    private AlertBuilder() {
    }

    public static AlertBuilder type(AlertType type) {
	if (builder == null)
	    builder = new AlertBuilder();
	
	if (builder.alert == null)
	    builder.alert = new Alert(type);
	else
	    builder.alert.setAlertType(type);
	
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
