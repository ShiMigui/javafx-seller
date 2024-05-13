package gui.windows;

import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.TextfieldConstraint;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements Initializable {
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
}
