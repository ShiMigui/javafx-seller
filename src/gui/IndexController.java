package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.AlertBuilder;
import gui.utils.MainViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;

public class IndexController implements Initializable {
    @FXML
    private MenuItem mitSeller;

    @FXML
    private MenuItem mitDepartment;

    @FXML
    private MenuItem mitAbout;

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
    }

    @FXML
    public void onMitSellerAction() {
	System.out.println("Seller menu item click!");
    }

    @FXML
    public void onMitDepartmentAction() {
	try {
	    MainViewManager.loadViewInIndex("/gui/DepartmentView.fxml", "Departments");
	} catch (IOException e) {
	    AlertBuilder.type(AlertType.ERROR).title("Erro ao tentar carregar recurso").content(e.getMessage());
	}
    }

    @FXML
    public void onMitAboutAction() {
	try {
	    MainViewManager.loadViewInIndex("/gui/AboutView.fxml", "About");
	} catch (IOException e) {
	    AlertBuilder.type(AlertType.ERROR).title("Erro ao tentar carregar recurso").content(e.getMessage());
	}
    }
}
