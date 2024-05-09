package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.utils.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;
import model.interfaces.DepartmentJPAService;

public class DepartmentController implements Initializable {
	private DepartmentJPAService service;

	@FXML
	private Button btnNew;

	@FXML
	private TableView<Department> tb;

	private ObservableList<Department> obs;

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
		
		setDepartmentJPAService(new DepartmentJPAService());
		updateTb();
	}

	@FXML
	public void onBtnNewAction() {
		System.out.println("Btn new click!");
	}

	public void updateTb() {
		if (service == null)
			throw new IllegalStateException("Service cannot be null");
		setTbObs(service.findAll());
	}

	private void setTbObs(List<Department> all) {
		obs = FXCollections.observableArrayList(all);
		tb.setItems(obs);
	}

	private void setDepartmentJPAService(DepartmentJPAService service) {
		this.service = service;
	}
}
