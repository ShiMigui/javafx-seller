package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;

public class DepartmentController implements Initializable {
	@FXML
	private Button btnNew;

	@FXML
	private TableView<Department> tb;

	@FXML
	private TableColumn<Department, Integer> tbColumnId;

	@FXML
	private TableColumn<Department, String> tbColumnName;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tb.prefHeightProperty().bind(ViewController.getStage().heightProperty());
	}

	@FXML
	public void onBtnNewAction() {
		System.out.println("Btn new click!");
	}
}
