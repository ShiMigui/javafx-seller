package gui.windows;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.utils.AlertBuilder;
import gui.utils.TextfieldConstraint;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.interfaces.IDataChangeListener;
import model.interfaces.IEntityFormController;
import model.services.DepartmentService;

public class DepartmentFormController implements IEntityFormController<Department> {
    private Stage thisPage;
    private Department entity;
    private DepartmentService service;
    private List<IDataChangeListener> listeners = new ArrayList<>();

    @FXML
    private TextField txfId;

    @FXML
    private TextField txfName;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lbl;

    public void initialize(URL url, ResourceBundle rs) {
	TextfieldConstraint.getInstance(txfId).asIntegerText();
	TextfieldConstraint.getInstance(txfName).maxLength(255);
	this.service = DepartmentService.getInstance();

	btnSalvar.setDefaultButton(true);
    }

    private void updateForm() {
	if (entity == null)
	    throw new NullPointerException("Entity cannot be null!");

	txfId.setText(entity.getId() == null ? "" : String.valueOf(entity.getId()));
	txfName.setText(entity.getName());
    }

    @FXML
    private void onBtnSalvarAction() {
	try {
	    if (txfName.getText() == null || txfName.getText().trim().equals("")) {
		txfName.requestFocus();
		throw new ValidationException("Name cannot be empty.");
	    }

	    entity.setName(txfName.getText());
	    service.insertOrUpdate(entity);

	    listeners.forEach((i) -> i.dataChanged());
	    thisPage.close();
	} catch (RuntimeException e) {
	    lbl.setText(e.getMessage());
	    AlertBuilder.type(AlertType.ERROR).title("Error saving object").content(e.getMessage());
	}
    }

    @FXML
    private void onBtnCancelarAction() {
	thisPage.close();
    }

    @Override
    public void setReference(Department dep) {
	if (dep == null)
	    throw new NullPointerException("Department was null!");
	this.entity = dep;
	updateForm();
    }

    @Override
    public void setStage(Stage thisPage) {
	this.thisPage = thisPage;
    }

    @Override
    public void subscribeListener(IDataChangeListener listener) {
	listeners.add(listener);
    }
}
