package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.utils.AlertBuilder;
import gui.utils.MainViewManager;
import gui.utils.WindowManager;
import gui.windows.DepartmentFormController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.interfaces.IController;
import model.interfaces.IDataChangeListener;
import model.services.DepartmentService;

public class DepartmentController implements IController, IDataChangeListener {
    private DepartmentService service;
    private WindowManager wm = new WindowManager();

    @FXML
    private Button btnNew;

    @FXML
    private TableView<Department> tb;

    private ObservableList<Department> obs;

    @FXML
    private TableColumn<Department, Integer> tbColumnId;

    @FXML
    private TableColumn<Department, String> tbColumnName;

    @FXML
    private TableColumn<Department, Department> tbColumnEdit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	try {
	    this.service = DepartmentService.getInstance();

	    tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	    tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

	    tb.prefHeightProperty().bind(MainViewManager.getStage().heightProperty());

	    updateTb();
	} catch (RuntimeException e) {
	    AlertBuilder.type(AlertType.ERROR).title("Erro ao tentar carregar recurso").content(e.getMessage());
	}
    }

    private void addEditButtons() {
	tbColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	tbColumnEdit.setCellFactory(param -> new TableCell<Department, Department>() {
	    private final Button button = new Button("edit");

	    @Override
	    protected void updateItem(Department obj, boolean empty) {
		super.updateItem(obj, empty);
		if (obj == null) {
		    setGraphic(null);
		    return;
		}
		setGraphic(button);
		button.setOnAction(event -> loadFormWindow(obj));
	    }
	});
    }

    @FXML
    public void onBtnNewAction() {
	loadFormWindow(new Department());
    }

    private void loadFormWindow(Department dep) {
	try {
	    wm.windowModal("/gui/windows/DepartmentFormView.fxml", "New departamento");
	    Stage window = wm.getWindow();

	    DepartmentFormController controller = wm.getController();
	    controller.setDepartment(dep);
	    controller.setThisPage(window);
	    controller.subscribeListener(this);

	    window.showAndWait();
	} catch (IOException e) {
	    AlertBuilder.type(AlertType.ERROR).title("Erro ao tentar carregar recurso").content(e.getMessage());
	}
    }

    public void updateTb() {
	if (service == null)
	    throw new NullPointerException("Service cannot be null");
	setTbObs(service.findAll());
	addEditButtons();
    }

    private void setTbObs(List<Department> all) {
	obs = FXCollections.observableArrayList(all);
	tb.setItems(obs);
    }

    @Override
    public void dataChanged() {
	updateTb();
    }
}
