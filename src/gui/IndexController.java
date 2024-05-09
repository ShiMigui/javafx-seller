package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.utils.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class IndexController implements Initializable{
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
		System.out.println("Department menu item click!");
		ViewController.loadView("/gui/DepartmentView.fxml", "Departments");
	}
	
	@FXML
	public void onMitAboutAction() {
		System.out.println("About menu item click!");
		ViewController.loadView("/gui/AboutView.fxml", "About");
	}
}
