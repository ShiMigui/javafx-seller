package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import gui.utils.AlertBuilder;
import gui.utils.ConfirmationBuilder;
import gui.utils.MainViewManager;
import gui.utils.WindowManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.interfaces.IController;
import model.interfaces.IDataChangeListener;
import model.interfaces.IEntityFormController;
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

    @FXML
    private TableColumn<Department, Department> tbColumnRemove;

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

    @FXML
    private void onBtnNewAction() {
	loadFormWindow(new Department());
    }

    private void loadFormWindow(Department dep) {
	try {
	    wm.windowModal("/gui/windows/DepartmentFormView.fxml", "New departamento");
	    Stage window = wm.getWindow();

	    IEntityFormController<Department> controller = wm.getController();
	    controller.setReference(dep);
	    controller.setStage(window);
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
	addRemoveButtons();
    }

    private void setTbObs(List<Department> all) {
	obs = FXCollections.observableArrayList(all);
	tb.setItems(obs);
    }

    @Override
    public void dataChanged() {
	updateTb();
    }

    private void removeEntity(Department obj) {
	try {
	    Optional<ButtonType> op = ConfirmationBuilder.getInstance().title("Remove a department")
		    .content("Você deseja ralmente deletar o departamento \"" + obj.getName() + "\"?").showAndWait();

	    if (op.get() == ButtonType.OK)
		service.remove(obj);
	    updateTb();
	} catch (RuntimeException e) {
	    AlertBuilder.type(AlertType.ERROR).title("Error removing object").content(e.getMessage()).show();
	}
    }

    private void addButtonToTableCell(TableColumn<Department, Department> column, String buttonText,
	    Consumer<Department> action) {
	column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	column.setCellFactory(param -> new TableCell<Department, Department>() {
	    private final Button button = new Button(buttonText);

	    @Override
	    protected void updateItem(Department obj, boolean empty) {
		super.updateItem(obj, empty);
		if (obj == null || empty) {
		    setGraphic(null);
		    return;
		}
		setGraphic(button);
		button.setOnAction(event -> action.accept(obj));
	    }
	});
    }

    private void addEditButtons() {
	addButtonToTableCell(tbColumnEdit, "edit", this::loadFormWindow);
    }

    private void addRemoveButtons() {
	addButtonToTableCell(tbColumnRemove, "remove", this::removeEntity);
    }

}
