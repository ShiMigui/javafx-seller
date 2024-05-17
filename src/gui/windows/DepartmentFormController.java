package gui.windows;

import java.net.URL;
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
import model.interfaces.IController;
import model.services.DepartmentService;

public class DepartmentFormController implements IController {
    private Department entity;
    private DepartmentService service;

    private Stage thisPage;

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
    }

    @FXML
    private void onBtnSalvarAction() {
	try {
	    entity.setName(txfName.getText());
	    service.insertOrUpdate(entity);
	    thisPage.close();
	} catch (RuntimeException e) {
	    lbl.setText("Ocorreu um erro ao salvar: " + e.getMessage());
	    AlertBuilder.type(AlertType.ERROR).title("Error saving object").content(e.getMessage());
	}
    }

    @FXML
    private void onBtnCancelarAction() {
	thisPage.close();
    }

    public void setDepartment(Department dep) {
	this.entity = dep;
	updateForm();
    }

    private void updateForm() {
	if (entity == null)
	    throw new NullPointerException("Entity cannot be null!");

	txfId.setText(entity.getId() == null ? "" : String.valueOf(entity.getId()));
	txfName.setText(entity.getName());
    }

    public void setThisPage(Stage thisPage) {
	this.thisPage = thisPage;
    }
}
