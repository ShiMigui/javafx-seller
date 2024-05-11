package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.utils.AlertController;
import gui.utils.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentController implements Initializable {
    private DepartmentService service;

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
	try {
	    tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	    tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

	    tb.prefHeightProperty().bind(ViewController.getStage().heightProperty());

	    setDepartmentService(DepartmentService.getInstance());
	    updateTb();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    public void onBtnNewAction() {
	try {
	    ViewController.createModalWindow("/gui/DepartmentFormView.fxml", "Novo departamento!", "novo_departamento",
		    ViewController.getStage());
	} catch (IOException e) {
	    AlertController.setType(AlertType.ERROR);
	    AlertController.showAlert("Erro ao tentar carregar recurso!", e.getMessage());
	}
    }

    public void updateTb() {
	if (service == null) {
	    throw new IllegalStateException("Service cannot be null");
	}
	setTbObs(service.findAll());
    }

    private void setTbObs(List<Department> all) {
	obs = FXCollections.observableArrayList(all);
	tb.setItems(obs);
    }

    private void setDepartmentService(DepartmentService service) {
	this.service = service;
    }
}
