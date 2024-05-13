package gui.windows;

import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.TextfieldConstraint;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.interfaces.IController;

public class DepartmentFormController implements IController {
    private Department entity;

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
    }

    @FXML
    private void onBtnSalvarAction() {
	System.out.println("Btn salvar click!");
    }

    @FXML
    private void onBtnCancelarAction() {
	System.out.println("Btn cancelar click!");
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
}
