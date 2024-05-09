package gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertController {
	private static Alert alert;
	
	private AlertController() {
	}

	public static void setType(AlertType type) {
		if (alert == null)
			alert = new Alert(type);
		else {
			alert.setAlertType(type);
		}
	}

	public static void showAlert(String title, String content) {
		showAlert(title, null, content);
	}
	
	public static void showAlert(String title, String header, String content) {
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		
		showAlert();
	}
	
	public static void showAlert() {
		alert.show();
	}
}
