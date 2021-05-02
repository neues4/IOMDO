package bachelorthesis.IOMDOProject.controller;

import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.Counter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * 
 * @author neues4
 *
 */
public class RDPatDatController {

	OntologyEditor ontEdit = new OntologyEditor("/Users/stefanie/Desktop/IOMO_18.owl");

	// Diagnosen (noch nicht vollständig)
	ObservableList<String> diagnosisList = FXCollections.observableArrayList("Akustikusneurinom", "Aneurysma", "AV Fistel");
	
	//Operationen (noch nicht vollständig)
	ObservableList<String> surgeryList = FXCollections.observableArrayList("CEA", "Aufrichtung", "Clipping", "Clipping und Coiling");
	
	// Operateure (vollständig)
	ObservableList<String> surgeonList = FXCollections.observableArrayList("Abu Isa", "Bervini", "Fichtner", "Finkenstädt", "Krähenbühl", "Lutz",
			"Murek", "Pollo", "Raabe", "Schär", "Schläppi", "Schucht", "Seidel", "Ulrich", "Vulcu");
	
	// ISIS Geräte (vollständig)
	ObservableList<String> deviceList = FXCollections.observableArrayList("Einstein", "Mieze", "Napoleon", "Rosalinde");
	
	// Assistenten (vollständig)
	ObservableList<String> assistantList = FXCollections.observableArrayList("Consuegra", "Finkenstädt", "Goldberg", "Häni", "Hlavica", "Jesse", 
			"Lutz", "Mija", "Montalbetti", "Müller", "Nowacki", "Osmanagic", "Redeker", "Ropelato", "Schütz", "Tashi", "Zubak");

	@FXML
	private TextField caseNrTF;

	@FXML
	private TextField surnameTF;

	@FXML
	private TextField pidTF;

	@FXML
	private TextField firstNameTF;

	@FXML
	private TextField fidTF;

	@FXML
	private TextField birthdayTF;

	@FXML
	private ComboBox<String> diagnosisCB;

	@FXML
	private ComboBox<String> surgeryCB;

	@FXML
	private ComboBox<String> surgeonCB;

	@FXML
	private ComboBox<String> deviceCB;

	@FXML
	private ComboBox<String> assistantCB;

	@FXML
	private Button nextBtn;

	Counter patNumber = new Counter(1);

	@FXML
	public void initialize() {
		diagnosisCB.setItems(diagnosisList);
		surgeryCB.setItems(surgeryList);
		surgeonCB.setItems(surgeonList);
		deviceCB.setItems(deviceList);
		assistantCB.setItems(assistantList);
	}

	@FXML
	public void savePatientNext(ActionEvent event) {
		//OntologyEditor ontEdit = new OntologyEditor("/Users/stefanie/Documents/maven.1619428611109/IOMDOProject/myModel.owl");
		Integer patNum = patNumber.getValue();
		String patLabel = "Patient ".concat(patNum.toString());
		ontEdit.createNewPatient(patLabel);
		ontEdit.addFirstName(firstNameTF.getText());
		ontEdit.addSurname(surnameTF.getText());
		ontEdit.addPID(pidTF.getText());
		ontEdit.addFID(fidTF.getText());
		ontEdit.addBirthday(birthdayTF.getText());
		//ontEdit.addDiagnosis(diagnosisCB.getSelectionModel().getSelectedItem());
		ontEdit.saveNewOWLFile(); 	
		patNumber.increment();
	}

}
