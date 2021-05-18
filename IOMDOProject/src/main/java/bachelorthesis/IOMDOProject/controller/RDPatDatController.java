package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import bachelorthesis.IOMDOProject.Main;
import bachelorthesis.IOMDOProject.model.Counter;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

/**
 * 
 * @author neues4
 *
 */
public class RDPatDatController {


	//für Mac
	OntologyEditor ontEdit = new OntologyEditor("/Users/stefanie/Desktop/IOMO_27.owl");
	//Windows
	//OntologyEditor ontEdit = new OntologyEditor("src\\main\\resources\\bachelorthesis\\IOMDOProject\\IOMO_28.owl");
	// Diagnosis
	ObservableList<String> diagnosisList = FXCollections.observableArrayList(ontEdit.getAllDiagnosis().keySet());

	// Surgeries
	ObservableList<String> surgeryList = FXCollections.observableArrayList(ontEdit.getAllSurgeries().keySet());

	// Surgeons
	ObservableList<String> surgeonList = FXCollections.observableArrayList("Abu Isa", "Bervini", "Fichtner", "Finkenstädt", "Krähenbühl", "Lutz",
			"Murek", "Pollo", "Raabe", "Schär", "Schläppi", "Schucht", "Seidel", "Ulrich", "Vulcu");

	// ISIS Devices
	ObservableList<String> deviceList = FXCollections.observableArrayList("Einstein", "Mieze", "Napoleon", "Rosalinde");

	// Assistants
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
	private TextField dateOfSurgeryTF;

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
	public void savePatientNext(ActionEvent event) throws IOException {

		Integer patNum = patNumber.getValue();
		String patLabel = "Patient".concat(patNum.toString());

		String pat = ontEdit.createNewPatient(patLabel); 

		ontEdit.addPropertiesToPatient(pat, caseNrTF.getText(), pidTF.getText(), fidTF.getText(), firstNameTF.getText(), surnameTF.getText(), birthdayTF.getText());

		String surgery= ontEdit.createSurgery(ontEdit.getAllSurgeries().get(surgeryCB.getSelectionModel().getSelectedItem()), "Surgery");

		ontEdit.addPropertiesToSurgery(surgery, dateOfSurgeryTF.getText(), surgeonCB.getSelectionModel().getSelectedItem(), assistantCB.getSelectionModel().getSelectedItem(), deviceCB.getSelectionModel().getSelectedItem());

		String diagnosis = ontEdit.createDiagnosis(ontEdit.getAllDiagnosis().get(diagnosisCB.getSelectionModel().getSelectedItem()), "Diagnosis");

		String iomDocument = ontEdit.createNewIOMDocument("Document");

		ontEdit.addStatement(pat, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285", iomDocument);
		ontEdit.addStatement(iomDocument, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282", surgery);
		ontEdit.addStatement(iomDocument, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282", diagnosis);


		//ontEdit.addStatement(ontEdit.createNewPatient(patLabel), "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282", ontEdit.createDiagnosis(diagnosisCB.getSelectionModel().getSelectedItem()));


		System.out.println(ontEdit.getAllDiagnosis().get(diagnosisCB.getSelectionModel().getSelectedItem()));
		System.out.println(surgeryCB.getSelectionModel().getSelectedItem());


		ontEdit.saveNewOWLFile(); 	
		patNumber.increment();
	}

}
