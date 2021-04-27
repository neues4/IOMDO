package bachelorthesis.IOMDOProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecordDocumentPatientDataController {

	@FXML
	private Button nextBtn;
	
	@FXML
	private TextField caseNrTF;
	
	@FXML
	private TextField surnameTF;
	
	

	public void savePatientnext(ActionEvent event) {
		System.out.println(surnameTF.getText().toString());
	}
	
	
}
