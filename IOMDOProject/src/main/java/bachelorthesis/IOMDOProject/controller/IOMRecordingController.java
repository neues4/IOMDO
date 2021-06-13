package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import bachelorthesis.IOMDOProject.model.Counter;
import bachelorthesis.IOMDOProject.model.EntryMap;
import bachelorthesis.IOMDOProject.model.OntClassMap;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.PatientSurgeryData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Controller for the recording durinig Intraoperative Neuromonitoring
 * @author romap1, neues4
 *
 */
public class IOMRecordingController {

	//This class is split in 3 Parts for a better Overview: Part1: Patient/Surgery Data, Part2: Baselines, Part3: IOM actual Recording

	//Variables Patient/Surgery Data-----------------------------------

	@FXML
	private TextField caseNrTF, surnameTF, pidTF , firstNameTF, fidTF, birthdayTF, dateOfSurgeryTF;

	@FXML
	private ComboBox<String> diagnosisCB, surgeryCB, surgeonCB, deviceCB,  assistantCB;

	@FXML
	private Button nextBtn;

	//-----------------------------------Variables Patient/Surgery Data


	//Variables Baselines ---------------------------------------------

	@FXML
	private Accordion accordion;

	@FXML
	private GridPane tesMepGridpane;

	@FXML
	private ComboBox<String> cbTes1, cbTes2, cbTes3, cbTes4, cbTes5, cbTes6, cbTes7, cbTes8, cbTes9, cbTes10, cbTes11, cbTes12, cbTes13, cbTes14, cbTes15, cbTes16;

	@FXML
	private ComboBox<String> cbDcs1, cbDcs2, cbDcs3, cbDcs4, cbDcs5, cbDcs6, cbDcs7, cbDcs8, cbDcs9, cbDcs10;

	@FXML
	private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14, tf15, tf16, tf17, tf18, tf19, tf20, tf21, tf22, tf23, tf24,
	tf25, tf26, tf27, tf28, tf29, tf30, tf31, tf32, tf33, tf34, tf35, tf36, tf37, tf38, tf39, tf40, tf41, tf42, tf43, tf44, tf45, tf46, tf47, tf48;

	@FXML
	private TextField tfDcs1, tfDcs2, tfDcs3, tfDcs4, tfDcs5, tfDcs6, tfDcs7, tfDcs8, tfDcs9, tfDcs10, tfDcs11, tfDcs12, tfDcs13, tfDcs14, tfDcs15, tfDcs16, tfDcs17, tfDcs18, tfDcs19, tfDcs20,
	tfDcs21, tfDcs22, tfDcs23, tfDcs24, tfDcs25, tfDcs26, tfDcs27, tfDcs28, tfDcs29, tfDcs30, tfDcs31, tfDcs32, tfDcs33, tfDcs34, tfDcs35, tfDcs36, tfDcs37, tfDcs38, tfDcs39, tfDcs40;

	@FXML
	private ComboBox<String> cbVepL, cbVepR;

	@FXML
	private ComboBox<String> cbBrL, cbLarL, cbBcrL, cbBrR, cbLarR, cbBcrR;


	@FXML
	private TextField tfMedLN, tfMedRN, tfTibLN, tfTibRN, tfMedLP, tfMedRP, tfTibLP, tfTibRP, tfMedLA, tfMedRA, tfTibLA, tfTibRA;;


	@FXML
	private Button btnSaveTesMep, btnSaveSsep, btnSaveDcsMep, resetTESMEPBtn, resetDCSMEPBtn;

	@FXML
	private TitledPane SSEPPane, TESMEPPane, DCSMEPPane, AEPPane, VEPPane, reflexPane;

	//Counter for the Rows in the GridPane. Row 0 is Empty and Row 1 already has Nodes. Therefore the counter starts at 2.

	// a list for the muscles chosen in tes mep baseline
	private ObservableList<String> tesMepMuscleChoice = FXCollections.observableArrayList();

	// a list for the muscles chosen in dcs mep baseline
	private ObservableList<String> dcsMepMuscleChoice = FXCollections.observableArrayList();


	//Löschen? romap1
	// a map for the comboboxes in tes mep
	private Map<Integer, ComboBox<String>> cbTesMap = new HashMap<Integer, ComboBox<String>>();

	// a map for the comboboxes in dcs mep
	private Map<Integer, ComboBox<String>> cbDcsMap = new HashMap<Integer, ComboBox<String>>();




	//-------------------------------------------------------Variables Baselines

	//Variables IOM actual Recording-------------------------------------------------
	
	private OntClassMap ontClassMap = new OntClassMap();
	
	@FXML
	private Button addRowBtn, save;

	@FXML
	private RowConstraints row2;

	@FXML
	private GridPane infoGrid;

	@FXML
	private SplitPane splitpane;

	@FXML
	private TextField timeStartTF, commentIOMStart, outcomeCommentTF;

	@FXML
	private ComboBox<String> categoryIOMStart, outcomeCB;

	//löschen romap1
	//@FXML
	//private ComboBox<String> entryIOMStart;

	@FXML
	private Label patientNameLbl, birthdayLbl,diagnoseLbl, surgeryLbl, dateLbl ;

	// a map for the tf in sep baselines
	private Map<String, Node> nodeMapSepBaselines = new HashMap<String, Node>();

	// a map for the cb and tf in tes baselines
	private Map<String, Node> nodeMapTesBaselines = new HashMap<String, Node>();

	// a map for the cb and tf in dcs baselines
	private Map<String, Node> nodeMapDcsBaselines = new HashMap<String, Node>();

	private int row = 2;

	private static final String NS = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/";

	private Map<String, Node> nodeList = new HashMap<String, Node>();

	// new instance of Ontology Editor
	private OntologyEditor ontEdit = OntologyEditor.getInstance();	


	// create the list for the first dropdown
	private ObservableList<String> categoryList = FXCollections.observableArrayList(ontEdit.getAllEntitiesToBeShown().keySet());

	// create all the lists for the second dropdown of the IOM Documentation
	private ObservableList<String> vepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000403").keySet());
	private ObservableList<String> mappingMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000373").keySet());
	private ObservableList<String> technicalIssuesList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000154").keySet());
	//private ObservableList<String> dwaveMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000369").keySet());
	//private ObservableList<String> tesMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000378").keySet());
	//private ObservableList<String> dcsMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000238").keySet());
	//private ObservableList<String> vepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000244").keySet());
	//private ObservableList<String> sepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000242").keySet());
	private ObservableList<String> aepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000240").keySet());
	//private ObservableList<String> cbtMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000371").keySet());

	private ObservableList<String> reflexFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000234").keySet());
	private ObservableList<String> eegFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000231").keySet());
	private ObservableList<String> anesthesyProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000159").keySet());
	private ObservableList<String> mappingFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000376").keySet());
	//private ObservableList<String> iomProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000057").keySet());
	private ObservableList<String> sepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000401").keySet());
	private ObservableList<String> aepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000402").keySet());
	private ObservableList<String> dwaveFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000387").keySet());
	private ObservableList<String> emgFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000370").keySet());
	private ObservableList<String> mepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000400").keySet());
	private ObservableList<String> actionList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://medicis/spm.owl/OntoSPM#manipulating_action_by_human").keySet());
	private ObservableList<String> gridPositioningList = FXCollections.observableArrayList(ontEdit.getSubclasses(NS  + "IOMO_0000064").keySet());
	//private ObservableList<String> surgeryProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000058").keySet());
	//Es gibt noch keine Lösung wie man Unterkategorien von Unterkategorie abfragt. romap1
	private ObservableList<String> surgeryProcessList = FXCollections.observableArrayList(Arrays.asList("Dura zu", "Duraplastik", "Exstirpation", "Knochendeckel einsetzen", "Kortikotomie", 
			"Knochendeckel entfernen", "Kortikotomie Erweiterung", "Kurze Pause", "Naht", "Resektion", "Teilresektion", "Schnitt", 
			"Dura öffnung", "Hämostase", "Lokalisation", "Zugang", "Navigation"));
	//---------------------------------------------------Variables IOM actual Recording





	public void initialize() {
		// Patient data start
		Collections.sort(diagnosisList);
		Collections.sort(surgeryList);
		diagnosisCB.setItems(diagnosisList);
		surgeryCB.setItems(surgeryList);
		surgeonCB.setItems(surgeonList);
		deviceCB.setItems(deviceList);
		assistantCB.setItems(assistantList);
		// Patient data end

		//IOM Documentation start
		Collections.sort(categoryList);
		//
		//categoryIOMStart.setItems(categoryList);
		//entryIOMStart.setItems(entryList);
		//IOM Documentation end

		// Baseline start

		Collections.sort(muscleList);
		cbTes1.setItems(muscleList);
		cbTes2.setItems(muscleList);
		cbTes3.setItems(muscleList);
		cbTes4.setItems(muscleList);
		cbTes5.setItems(muscleList);
		cbTes6.setItems(muscleList);
		cbTes7.setItems(muscleList);
		cbTes8.setItems(muscleList);
		cbTes9.setItems(muscleList);
		cbTes10.setItems(muscleList);
		cbTes11.setItems(muscleList);
		cbTes12.setItems(muscleList);
		cbTes13.setItems(muscleList);
		cbTes14.setItems(muscleList);
		cbTes15.setItems(muscleList);
		cbTes16.setItems(muscleList);

		cbDcs1.setItems(muscleList);
		cbDcs2.setItems(muscleList);
		cbDcs3.setItems(muscleList);
		cbDcs4.setItems(muscleList);
		cbDcs5.setItems(muscleList);
		cbDcs6.setItems(muscleList);
		cbDcs7.setItems(muscleList);
		cbDcs8.setItems(muscleList);
		cbDcs9.setItems(muscleList);
		cbDcs10.setItems(muscleList);


		//changes color of the Text of the TitlePane when at least one of the contained Textfields has an input.
		tfMedLN.textProperty().addListener((Observable) -> {
			if (tfMedLN.getText() != "") {
				SSEPPane.setTextFill(Color.GREEN);}
			else {
				SSEPPane.setTextFill(Color.BLACK);}
		});
		tfMedRN.textProperty().addListener((Observable) -> {
			if (tfMedLN.getText() != "") {
				SSEPPane.setTextFill(Color.GREEN);}
			else {
				SSEPPane.setTextFill(Color.BLACK);}
		});
		tfTibLN.textProperty().addListener((Observable) -> {
			if (tfMedLN.getText() != "") {
				SSEPPane.setTextFill(Color.GREEN);}
			else {
				SSEPPane.setTextFill(Color.BLACK);}
		});
		tfTibRN.textProperty().addListener((Observable) -> {
			if (tfMedLN.getText() != "") {
				SSEPPane.setTextFill(Color.GREEN);}
			else {
				SSEPPane.setTextFill(Color.BLACK);}
		});
		//AEPPAne



		//, TESMEPPane, DCSMEPPane, AEPPane, VEPPane, reflexPane;


		// Baseline end

		//Actual Patient Recording Initialize-----------------------
		
		
		
		categoryIOMStart.getSelectionModel().select("IOM Start");
		//Undercategory from "change of AEP, SSEP, MEP, VEP" added. 
		String change_of_aep = "IOMO_0000429";
		String change_of_ssep = "IOMO_0000417";
		String change_of_vep = "IOMO_0000134";
		String change_of_mep = "IOMO_0000405";
		aepFindingList.addAll(ontEdit.getSubclasses(NS + change_of_aep).keySet());
		sepFindingList.addAll(ontEdit.getSubclasses(NS + change_of_ssep).keySet());
		vepFindingList.addAll(ontEdit.getSubclasses(NS + change_of_vep).keySet());
		mepFindingList.addAll(ontEdit.getSubclasses(NS + change_of_mep).keySet());
		
		
		outcomeCB.setItems(ontEdit.getPostoperativeDisposition());
		
		//-----------------------Actual Patient Recording Initialize End

	}






	// Patient Data Start---------------------------------------------------------------

	Counter patNumber = new Counter(1);

	// Diagnosis
	ObservableList<String> diagnosisList = FXCollections.observableArrayList(ontEdit.getAllDiagnosis().keySet());
	// Surgeries
	ObservableList<String> surgeryList = FXCollections.observableArrayList(ontEdit.getAllSurgeries().keySet());
	// Surgeons
	ObservableList<String> surgeonList = FXCollections.observableArrayList("Abu Isa", "Bervini", "Fichtner", "Finkenstädt", "Krähenbühl", "Lutz",
			"Murek", "Pollo", "Raabe", "Schär", "Schläppi", "Schucht", "Seidel", "Ulrich", "Vulcu");
	// Devices
	ObservableList<String> deviceList = FXCollections.observableArrayList("Einstein", "Mieze", "Napoleon", "Rosalinde");
	// Assistants
	ObservableList<String> assistantList = FXCollections.observableArrayList("Consuegra", "Finkenstädt", "Goldberg", "Häni", "Hlavica", "Jesse", 
			"Lutz", "Mija", "Montalbetti", "Müller", "Nowacki", "Osmanagic", "Redeker", "Ropelato", "Schütz", "Tashi", "Zubak");

	/*
	 * a method to save a new patient instance to the ontology, his diagnosis and his surgery with all the dataproperties
	 */

	public void savePatient (String documentUri) {
		
		
		ontEdit.addPatient( caseNrTF.getText(), pidTF.getText(), fidTF.getText(), firstNameTF.getText(), surnameTF.getText(), birthdayTF.getText(), documentUri );
		String surgery= surgeryCB.getSelectionModel().getSelectedItem();
		ontEdit.addSurgery(ontClassMap.getUriFromLabel(surgery), surgery , dateOfSurgeryTF.getText(), surgeonCB.getSelectionModel().getSelectedItem(), assistantCB.getSelectionModel().getSelectedItem(), deviceCB.getSelectionModel().getSelectedItem(), documentUri);
		String diagnosis= diagnosisCB.getSelectionModel().getSelectedItem();
		ontEdit.addDiagnosis(ontClassMap.getUriFromLabel(diagnosis), diagnosis, documentUri);
		/*
		Integer patNum = patNumber.getValue();
		String patLabel = "Patient".concat(patNum.toString());

		String pat = ontEdit.createNewPatient(patLabel); 

		ontEdit.addPatientProperties(pat, caseNrTF.getText(), pidTF.getText(), fidTF.getText(), firstNameTF.getText(), surnameTF.getText(), birthdayTF.getText());

		String surgery= ontEdit.createNewIndividual(ontEdit.getAllSurgeries().get(surgeryCB.getSelectionModel().getSelectedItem()), "Surgery");

		ontEdit.addSurgeryProperties(surgery, dateOfSurgeryTF.getText(), surgeonCB.getSelectionModel().getSelectedItem(), assistantCB.getSelectionModel().getSelectedItem(), deviceCB.getSelectionModel().getSelectedItem());

		String diagnosis = ontEdit.createNewIndividual(ontEdit.getAllDiagnosis().get(diagnosisCB.getSelectionModel().getSelectedItem()), "Diagnosis");

		String iomDocument = ontEdit.createNewIOMDocument("Document");

		ontEdit.addStatement(pat, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285", iomDocument);
		ontEdit.addStatement(iomDocument, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282", surgery);
		ontEdit.addStatement(iomDocument, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282", diagnosis);

		System.out.println(ontEdit.getAllDiagnosis().get(diagnosisCB.getSelectionModel().getSelectedItem()));
		System.out.println(surgeryCB.getSelectionModel().getSelectedItem());

		ontEdit.saveNewOWLFile(); 	
		patNumber.increment();
		*/
	}

	// -------------------------------------------------------------Patient Data End






	// Baseline Start-----------------------------------------------------------------------------------------
	// put all the labels of the muscles in a list
	ObservableList<String> muscleList = FXCollections.observableArrayList(ontEdit.getAllMuscles().keySet());

	//put all the possibilities for veo in a List
	ObservableList<String> vepAndReflexList = FXCollections.observableArrayList("vorhanden", "mässig", "schlecht", "");

	/**
	 * a method to read all ms values in the text boxes of sep baseline and create instances of nerve, ms and data item
	 * FEHLT NOCH VERBINDUNG ZUM IOM PROTOKOLL!
	 */
	public void getSepBaselineValues() {
		nodeMapSepBaselines.put("21", tfMedLN); nodeMapSepBaselines.put("22", tfMedLP); nodeMapSepBaselines.put("23", tfMedLA);
		nodeMapSepBaselines.put("31", tfMedRN); nodeMapSepBaselines.put("32", tfMedRP); nodeMapSepBaselines.put("33", tfMedRA);
		nodeMapSepBaselines.put("41", tfTibLN); nodeMapSepBaselines.put("42", tfTibLP); nodeMapSepBaselines.put("43", tfTibLA);
		nodeMapSepBaselines.put("51", tfTibRN); nodeMapSepBaselines.put("52", tfTibRP); nodeMapSepBaselines.put("53", tfTibRA);

		// create a new sep baseline measurement
		String sepBaselineMeasurement = ontEdit.createNewIndividual(ontEdit.getBaselines().get("SEP Baseline Messung"), "SepBaselineMeasurement");
		String nerve = null;
		Counter msCounter = new Counter(1);
		// go through all the rows of the grid
		int rowsInSep = nodeMapSepBaselines.size()/3;
		for(int i= 2; i <= rowsInSep + 1; i++) {
			// add the nerves to the rows
			switch(i) {
			case 2:
				nerve = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65328", "nerve1");
				break;
			case 3:
				nerve = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65327", "nerve2");
				break;
			case 4:
				nerve = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80064", "nerve3");
				break;
			case 5:
				nerve = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80063", "nerve4");
				break;
			}
			// see if textfield is filled out, read the value, create new instances in the ontology
			if(getTextField(nodeMapSepBaselines.get(i +"" + 1)).getText() != "") {
				String ms1 = ontEdit.createNewMilisecond("ms".concat(msCounter.toString()));
				String msValue1 = getTextField(  nodeMapSepBaselines.get( i  +"" + 1)).getText();
				createSepBaselineStatement(ms1, msValue1, sepBaselineMeasurement, nerve);
				msCounter.increment();
			}
			// see if textfield is filled out, read the value, create new instances in the ontology
			if(getTextField(nodeMapSepBaselines.get(i +"" + 2)).getText() != "") {
				String ms2 = ontEdit.createNewMilisecond("ms".concat(msCounter.toString()));
				String msValue2 = getTextField(  nodeMapSepBaselines.get( i  +"" + 2)).getText();
				createSepBaselineStatement(ms2, msValue2, sepBaselineMeasurement, nerve);
				msCounter.increment();
			}
			// see if textfield is filled out, read the value, create new instances in the ontology
			if(getTextField(nodeMapSepBaselines.get(i +"" + 3)).getText() != "") {
				String ms3 = ontEdit.createNewMilisecond("ms".concat(msCounter.toString()));
				String msValue3 = getTextField(  nodeMapSepBaselines.get( i  +"" + 3)).getText();
				createSepBaselineStatement(ms3, msValue3, sepBaselineMeasurement, nerve);
				msCounter.increment();
			}
		}
	}

	/**
	 * method to create a new sep baseline statement
	 * @param ms the miliseconds individual
	 * @param msValue the value of the miliseconds
	 * @param sepBaselineMeasurement the sep baseline measurement individual
	 * @param nerve the nerve individual
	 */
	public void createSepBaselineStatement(String ms, String msValue, String sepBaselineMeasurement, String nerve) {
		ontEdit.addPropertiesToMilisecond(ms, msValue);
		ontEdit.addStatement(sepBaselineMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", ms);
		ontEdit.addStatement(ms, "http://purl.obolibrary.org/obo/IAO_0000136", nerve);
	}


	/**
	 * a method to read all mA values in the text boxes of tes baseline and create instances of muscle, mA and data item
	 * FEHLT NOCH VERBINDUNG ZUM IOM PROTOKOLL!
	 */
	public void getTesBaselineValues(String document) {
		// put all the nodes of the TES grid in a map (Rownumber + Columnnumber)
		nodeMapTesBaselines.put("20", cbTes1); nodeMapTesBaselines.put("21", tf1); nodeMapTesBaselines.put("22", tf17); nodeMapTesBaselines.put("23", tf33);
		nodeMapTesBaselines.put("30", cbTes2); nodeMapTesBaselines.put("31", tf2); nodeMapTesBaselines.put("32", tf18); nodeMapTesBaselines.put("33", tf34);
		nodeMapTesBaselines.put("40", cbTes3); nodeMapTesBaselines.put("41", tf3); nodeMapTesBaselines.put("42", tf19); nodeMapTesBaselines.put("43", tf35);
		nodeMapTesBaselines.put("50", cbTes4); nodeMapTesBaselines.put("51", tf4); nodeMapTesBaselines.put("52", tf20); nodeMapTesBaselines.put("53", tf36);
		nodeMapTesBaselines.put("60", cbTes5); nodeMapTesBaselines.put("61", tf5); nodeMapTesBaselines.put("62", tf21); nodeMapTesBaselines.put("63", tf37);
		nodeMapTesBaselines.put("70", cbTes6); nodeMapTesBaselines.put("71", tf6); nodeMapTesBaselines.put("72", tf22); nodeMapTesBaselines.put("73", tf38);
		nodeMapTesBaselines.put("80", cbTes7); nodeMapTesBaselines.put("81", tf7); nodeMapTesBaselines.put("82", tf23); nodeMapTesBaselines.put("83", tf39);
		nodeMapTesBaselines.put("90", cbTes8); nodeMapTesBaselines.put("91", tf8); nodeMapTesBaselines.put("92", tf24); nodeMapTesBaselines.put("93", tf40);
		nodeMapTesBaselines.put("100", cbTes9); nodeMapTesBaselines.put("101", tf9); nodeMapTesBaselines.put("102", tf25); nodeMapTesBaselines.put("103", tf41);
		nodeMapTesBaselines.put("110", cbTes10); nodeMapTesBaselines.put("111", tf10); nodeMapTesBaselines.put("112", tf26); nodeMapTesBaselines.put("113", tf42);
		nodeMapTesBaselines.put("120", cbTes11); nodeMapTesBaselines.put("121", tf11); nodeMapTesBaselines.put("122", tf27); nodeMapTesBaselines.put("123", tf43);
		nodeMapTesBaselines.put("130", cbTes12); nodeMapTesBaselines.put("131", tf12); nodeMapTesBaselines.put("132", tf28); nodeMapTesBaselines.put("133", tf44);
		nodeMapTesBaselines.put("140", cbTes13); nodeMapTesBaselines.put("141", tf13); nodeMapTesBaselines.put("142", tf29); nodeMapTesBaselines.put("143", tf45);
		nodeMapTesBaselines.put("150", cbTes14); nodeMapTesBaselines.put("151", tf14); nodeMapTesBaselines.put("152", tf30); nodeMapTesBaselines.put("153", tf46);
		nodeMapTesBaselines.put("160", cbTes15); nodeMapTesBaselines.put("161", tf15); nodeMapTesBaselines.put("162", tf31); nodeMapTesBaselines.put("163", tf47);
		nodeMapTesBaselines.put("170", cbTes16); nodeMapTesBaselines.put("171", tf16); nodeMapTesBaselines.put("172", tf32); nodeMapTesBaselines.put("173", tf48);

		// create a new tes baseline measurement
		String tesMepBaselineMeasurement = ontEdit.createNewIndividual(ontEdit.getBaselines().get("TES MEP Baseline Messung"), "TesMepBaselineMessung");
		Counter muscleCounter = new Counter(1);
		Counter mATesBaselineCounter = new Counter(1);
		// go through all the rows of the grid
		int rowsInTes = nodeMapTesBaselines.size()/4;
		for(int i= 2; i <= rowsInTes + 1; i++) {
			// see if muscle is chosen, read the value
			if (getComboBox(nodeMapTesBaselines.get( i  +"" + 0)).getSelectionModel().getSelectedItem() != null) {
				// get the chosen muscle out of the combobox
				String muscleChosen = getComboBox(nodeMapTesBaselines.get(i +"" + 0)).getSelectionModel().getSelectedItem();
				// create a new instance for muscle
				String muscle = ontEdit.createNewIndividual(ontEdit.getAllMuscles().get(muscleChosen), "MuskelTesBaseline".concat(muscleCounter.toString()));
				muscleCounter.increment();
				// see if first text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapTesBaselines.get( i  +"" + 1)).getText() != "") {
					String maValue = getTextField(nodeMapTesBaselines.get( i  +"" + 1)).getText();
					String mA = ontEdit.createNewMiliampere("mATesBaseline".concat(mATesBaselineCounter.toString()));
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle, document);
					mATesBaselineCounter.increment();
				}
				// see if the second text field is filled out, read the value, create new instances in ontology
				if(getTextField(nodeMapTesBaselines.get( i  +"" + 2)).getText() != "") {
					String maValue = getTextField(nodeMapTesBaselines.get( i  +"" + 2)).getText();
					String mA = ontEdit.createNewMiliampere("mATesBasline".concat(mATesBaselineCounter.toString()));
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle, document);
					mATesBaselineCounter.increment();
				}
				// see if the third text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapTesBaselines.get( i  +"" + 3)).getText() != "") {
					String maValue = getTextField(nodeMapTesBaselines.get( i  +"" + 3)).getText();
					String mA = ontEdit.createNewMiliampere("mATesBasline".concat(mATesBaselineCounter.toString()));
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle, document);
					mATesBaselineCounter.increment();
				}
			}
		}
	}

	/**
	 * method to create a new tes mep baseline statement
	 * @param mA the miliampere individual
	 * @param maValue the value of the miliampere
	 * @param tesMepBaselineMeasurement the tes mep baseline measurement individual
	 * @param muscle the muscle individual
	 */
	public void createTesBaselineStatement(String mA, String maValue, String tesMepBaselineMeasurement, String muscle, String document) {
		ontEdit.addPropertiesToMiliampere(mA, maValue);
		ontEdit.addStatement(tesMepBaselineMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		ontEdit.addStatement(mA, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000468", muscle);
		ontEdit.addStatement(document, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285", tesMepBaselineMeasurement);
	}


	/**
	 * a method to read all mA values in the text boxes of dcs baseline and create instances of muscle, mA and data item
	 * FEHLT NOCH VERBINDUNG ZUM IOM PROTOKOLL!
	 */
	public void getDcsBaselineValues(String document) {
		nodeMapDcsBaselines.put("20", cbDcs1); nodeMapDcsBaselines.put("21", tfDcs1); nodeMapDcsBaselines.put("22", tfDcs11); nodeMapDcsBaselines.put("23", tfDcs21); nodeMapDcsBaselines.put("24", tfDcs31);
		nodeMapDcsBaselines.put("30", cbDcs2); nodeMapDcsBaselines.put("31", tfDcs2); nodeMapDcsBaselines.put("32", tfDcs12); nodeMapDcsBaselines.put("33", tfDcs22); nodeMapDcsBaselines.put("34", tfDcs32);
		nodeMapDcsBaselines.put("40", cbDcs3); nodeMapDcsBaselines.put("41", tfDcs3); nodeMapDcsBaselines.put("42", tfDcs13); nodeMapDcsBaselines.put("43", tfDcs23); nodeMapDcsBaselines.put("44", tfDcs33);
		nodeMapDcsBaselines.put("50", cbDcs4); nodeMapDcsBaselines.put("51", tfDcs4); nodeMapDcsBaselines.put("52", tfDcs14); nodeMapDcsBaselines.put("53", tfDcs24); nodeMapDcsBaselines.put("54", tfDcs34);
		nodeMapDcsBaselines.put("60", cbDcs5); nodeMapDcsBaselines.put("61", tfDcs5); nodeMapDcsBaselines.put("62", tfDcs15); nodeMapDcsBaselines.put("63", tfDcs25); nodeMapDcsBaselines.put("64", tfDcs35);
		nodeMapDcsBaselines.put("70", cbDcs6); nodeMapDcsBaselines.put("71", tfDcs6); nodeMapDcsBaselines.put("72", tfDcs16); nodeMapDcsBaselines.put("73", tfDcs26); nodeMapDcsBaselines.put("74", tfDcs36);
		nodeMapDcsBaselines.put("80", cbDcs7); nodeMapDcsBaselines.put("81", tfDcs7); nodeMapDcsBaselines.put("82", tfDcs17); nodeMapDcsBaselines.put("83", tfDcs27); nodeMapDcsBaselines.put("84", tfDcs37);
		nodeMapDcsBaselines.put("90", cbDcs8); nodeMapDcsBaselines.put("91", tfDcs8); nodeMapDcsBaselines.put("92", tfDcs18); nodeMapDcsBaselines.put("93", tfDcs28); nodeMapDcsBaselines.put("94", tfDcs38);
		nodeMapDcsBaselines.put("100", cbDcs9); nodeMapDcsBaselines.put("101", tfDcs9); nodeMapDcsBaselines.put("102", tfDcs19); nodeMapDcsBaselines.put("103", tfDcs29); nodeMapDcsBaselines.put("104", tfDcs39);
		nodeMapDcsBaselines.put("110", cbDcs10); nodeMapDcsBaselines.put("111", tfDcs10); nodeMapDcsBaselines.put("112", tfDcs20); nodeMapDcsBaselines.put("113", tfDcs30); nodeMapDcsBaselines.put("114", tfDcs40);

		// create a new tes baseline measurement
		String dcsMepBaselineMeasurement = ontEdit.createNewIndividual(ontEdit.getBaselines().get("DCS MEP Baseline Messung"), "DcsMepBaselineMessung");
		Counter muscleCounter = new Counter(1);
		Counter mADcsBaselineCounter = new Counter(1);
		// go through all the rows of the grid
		int rowsInDcs = nodeMapDcsBaselines.size()/5;
		for(int i= 2; i <= rowsInDcs + 1; i++) {
			// get the chosen muscle out of the combobox
			if (getComboBox(nodeMapDcsBaselines.get( i  +"" + 0)).getSelectionModel().getSelectedItem() != null) {
				String muscleChosen = getComboBox(nodeMapDcsBaselines.get(i +"" + 0)).getSelectionModel().getSelectedItem();
				String muscle = ontEdit.createNewIndividual(ontEdit.getAllMuscles().get(muscleChosen), "muskelDscBaseline".concat(muscleCounter.toString()));
				muscleCounter.increment();
				// see if first text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapDcsBaselines.get( i  +"" + 1)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 1)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle, document);
					mADcsBaselineCounter.increment();
				}
				// see if second text field is filled out, read the value, create new instances in ontology
				if(getTextField(nodeMapDcsBaselines.get( i  +"" + 2)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 2)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle, document);
					mADcsBaselineCounter.increment();
				}
				// see if third text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapDcsBaselines.get( i  +"" + 3)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 3)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle, document);
					mADcsBaselineCounter.increment();

				}
				// see if fourth text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapDcsBaselines.get( i  +"" + 4)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 4)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle, document);
					mADcsBaselineCounter.increment();
				}
			}
		}
	}
	/**
	 * method to create a new dcs mep baseline statement
	 * @param mA
	 * @param maValue
	 * @param dcsMepBaselineMeasurement
	 * @param muscle
	 */
	public void createDcsBaselineStatement(String mA, String maValue, String dcsMepBaselineMeasurement, String muscle, String document) {
		ontEdit.addPropertiesToMiliampere(mA, maValue);
		ontEdit.addStatement(dcsMepBaselineMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		ontEdit.addStatement(mA, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000468", muscle);
		ontEdit.addStatement(document, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285", dcsMepBaselineMeasurement);
	}

	/**
	 * Resets all selected Muscels in the Comboxes.
	 * @param event
	 */
	@FXML
	public void resetTESMEP(ActionEvent event) {
		cbTes1.getSelectionModel().clearSelection();cbTes2.getSelectionModel().clearSelection();
		cbTes3.getSelectionModel().clearSelection();cbTes4.getSelectionModel().clearSelection();
		cbTes5.getSelectionModel().clearSelection();cbTes6.getSelectionModel().clearSelection();
		cbTes7.getSelectionModel().clearSelection();cbTes8.getSelectionModel().clearSelection();
		cbTes9.getSelectionModel().clearSelection();cbTes10.getSelectionModel().clearSelection();
		cbTes11.getSelectionModel().clearSelection();cbTes12.getSelectionModel().clearSelection();
		cbTes13.getSelectionModel().clearSelection();cbTes14.getSelectionModel().clearSelection();
		cbTes15.getSelectionModel().clearSelection();cbTes16.getSelectionModel().clearSelection();
		TESMEPPane.setTextFill(Color.BLACK);
		tesMepMuscleChoice.remove(0, tesMepMuscleChoice.size());
		//ToDo !! remove TES MEP Measurement from categoryList

	}
	/**
	 * Checks if Combobox has a muscle selected. If a muscle is selected it will be displayed in the categories for the IOM Documentation.
	 * @param event
	 */
	@FXML
	private void cbTes1isUsed(ActionEvent event) {
		String selectedItem = cbTes1.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(0, selectedItem);
			//categoryList.addAll(ontEdit.getAllMeasurementsWithValues().keySet());	
			categoryList.add("TES MEP Messung");
		}
	}
	@FXML
	public void cbTes2isUsed(ActionEvent event) {
		String selectedItem = cbTes2.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(1, selectedItem);}
	}
	@FXML
	public void cbTes3isUsed(ActionEvent event) {
		String selectedItem = cbTes3.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(2, selectedItem);}
	}
	@FXML
	public void cbTes4isUsed(ActionEvent event) {
		String selectedItem = cbTes4.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(3, selectedItem);}
	}
	@FXML
	public void cbTes5isUsed(ActionEvent event) {
		String selectedItem = cbTes5.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(4, selectedItem);}
	}
	@FXML
	public void cbTes6isUsed(ActionEvent event) {
		String selectedItem = cbTes6.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(5, selectedItem);
		}
	}
	@FXML
	public void cbTes7isUsed(ActionEvent event) {
		String selectedItem = cbTes7.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(6, selectedItem);}
	}
	@FXML
	public void cbTes8isUsed(ActionEvent event) {
		String selectedItem = cbTes8.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(7, selectedItem);}
	}
	@FXML
	public void cbTes9isUsed(ActionEvent event) {
		String selectedItem = cbTes9.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(8, selectedItem);}
	}
	@FXML
	public void cbTes10isUsed(ActionEvent event) {
		String selectedItem = cbTes10.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(9, selectedItem);}
	}
	@FXML
	public void cbTes11isUsed(ActionEvent event) {
		String selectedItem = cbTes11.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(10, selectedItem);}
	}
	@FXML
	public void cbTes12isUsed(ActionEvent event) {
		String selectedItem = cbTes12.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(11, selectedItem);}
	}
	@FXML
	public void cbTes13isUsed(ActionEvent event) {
		String selectedItem = cbTes13.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(12, selectedItem);}
	}
	@FXML
	public void cbTes14isUsed(ActionEvent event) {
		String selectedItem = cbTes14.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(13, selectedItem);}
	}
	@FXML
	public void cbTes15isUsed(ActionEvent event) {
		String selectedItem = cbTes15.getSelectionModel().getSelectedItem();
		if (selectedItem == null){//skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(14, selectedItem);}
	}
	@FXML
	public void cbTes16isUsed(ActionEvent event) {
		String selectedItem = cbTes16.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);
			tesMepMuscleChoice.add(15, selectedItem);}
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	public void resetDCSMEP(ActionEvent event) {
		cbDcs1.getSelectionModel().clearSelection();cbDcs2.getSelectionModel().clearSelection();
		cbDcs3.getSelectionModel().clearSelection();cbDcs4.getSelectionModel().clearSelection();
		cbDcs5.getSelectionModel().clearSelection();cbDcs6.getSelectionModel().clearSelection();
		cbDcs7.getSelectionModel().clearSelection();cbDcs8.getSelectionModel().clearSelection();
		cbDcs9.getSelectionModel().clearSelection();cbDcs10.getSelectionModel().clearSelection();
		DCSMEPPane.setTextFill(Color.BLACK);
		dcsMepMuscleChoice.remove(0, dcsMepMuscleChoice.size());	
	}
	@FXML
	public void cbDcs1isUsed(ActionEvent event) {
		String selectedItem = cbDcs1.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(0, selectedItem);
			//categoryList.addAll(ontEdit.getAllMeasurementsWithValues().keySet());
			categoryList.add("DCS MEP Messung");}
	}
	public void cbDcs2isUsed(ActionEvent event) {
		String selectedItem = cbDcs2.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(1, selectedItem);}	
	}
	public void cbDcs3isUsed(ActionEvent event) {
		String selectedItem = cbDcs3.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(2, selectedItem);}
	}
	public void cbDcs4isUsed(ActionEvent event) {
		String selectedItem = cbDcs4.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(3, selectedItem);}
	}
	public void cbDcs5isUsed(ActionEvent event) {
		String selectedItem = cbDcs5.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(4, selectedItem);}
	}
	public void cbDcs6isUsed(ActionEvent event) {
		String selectedItem = cbDcs6.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(5, selectedItem);}
	}
	public void cbDcs7isUsed(ActionEvent event) {
		String selectedItem = cbDcs7.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(6, selectedItem);}
	}
	public void cbDcs8isUsed(ActionEvent event) {
		String selectedItem = cbDcs8.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(7, selectedItem);}
	}
	public void cbDcs9isUsed(ActionEvent event) {
		String selectedItem = cbDcs9.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(8, selectedItem);}
	}
	public void cbDcs10isUsed(ActionEvent event) {
		String selectedItem = cbDcs10.getSelectionModel().getSelectedItem();
		if (selectedItem == null){  //skip
		}
		else if (!selectedItem.isBlank()) {
			DCSMEPPane.setTextFill(Color.GREEN);
			dcsMepMuscleChoice.add(9, selectedItem);}
	}


	public void getAepBaselineValues() {

	}

	public void getVepBaselineValues() {
		if (cbVepL.getSelectionModel().getSelectedItem() != "") {
			String vepL = cbVepL.getSelectionModel().getSelectedItem();
		}
		if (cbVepR.getSelectionModel().getSelectedItem() != "") {
			String vepR = cbVepR.getSelectionModel().getSelectedItem();
		}
	}

	public void getReflexBaselineValues() {

		//cbBrL, cbLarL, cbBcrL, cbBrR, cbLarR, cbBcrR;

	}

	//-------------------Baseline End





	//IOM Recording Start-----------------------------------------------



	//private ObservableList<String> entryList = FXCollections.observableArrayList("entry1", "entry2");








	/**
	 * Adds a new Set of Nodes in the next empty Row of the GridPane.
	 * @param event
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public  void addRow(ActionEvent event) throws IOException, URISyntaxException {
		//create nessecary nodes
		TextField timeTF = new TextField();	
		timeTF.setPromptText("10:00");
		Tooltip tp = new Tooltip();
		tp.setText("this is a test");
		//timeTF.setTooltip(tp);
		ComboBox<String> categoryCB = new ComboBox<String>();
		categoryCB.setItems(categoryList);



		//categoryCB.setMaxHeight(Control.USE_COMPUTED_SIZE);

		ComboBox<String> entryCB = new ComboBox<String>();


		TextField valueTF = new TextField();
		TextField commentTF = new TextField();
		Button deleteBtn = new Button();

		//Textfield for value is not visible by default
		valueTF.setDisable(true);
		valueTF.setVisible(false);

		//Adds new Rows with predeterment selection of Muscle or Nerves according to the filled Baselines. Affects the Selection of "TES MEP Messung", 
		//"DCS MEP Messung" and "AEP Messung". 
		//The Textfield for value input is only visible when a category with measurement is selected. 
		categoryCB.setOnAction ((selectedItem) -> {
			if(categoryCB.getSelectionModel().getSelectedItem().contains("Messung")) {
				if(categoryCB.getSelectionModel().getSelectedItem().equals("TES MEP Messung")) {
					int size = tesMepMuscleChoice.size();

					for(int i= 1; i< size; i++) {
						//existing Combobox is preselected with the first String of the MEP Muscle List
						entryCB.getSelectionModel().select(tesMepMuscleChoice.get(0));
						//New Combobox with the matching String of the MEP Muscle List
						ComboBox<String> addicionalCB = new ComboBox<String>();
						addicionalCB.setItems(tesMepMuscleChoice);
						addicionalCB.getSelectionModel().select(tesMepMuscleChoice.get(i));

						categoryCB.setDisable(true);

						TextField AddidionalValue = new TextField();
						//Adding new Noddes to the NodeList, timeTF, CategoryCB and commentTF act as Dummys to not break the consistency of the List.
						nodeList.put(row + "1", timeTF);
						nodeList.put(row + "2", categoryCB);
						nodeList.put(row + "3", addicionalCB) ;
						nodeList.put(row + "4", AddidionalValue);
						nodeList.put(row + "5", commentTF);
						//nodeList.put(row + "2", new TextField());
						infoGrid.add(addicionalCB, 3, row);
						infoGrid.add(AddidionalValue, 4, row);
						row++;
						infoGrid.getChildren().remove(addRowBtn);
						infoGrid.add(addRowBtn, 1, row);
					}
					//Delete Button deletes the Row it is in and all additional Rows that are specifically created for the Measurement Input. 
					deleteBtn.addEventHandler(ActionEvent.ACTION,
							new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent event) {
							int index = GridPane.getRowIndex(deleteBtn);
							System.out.println(index);
							for(int i= index; i< (index + size); i++) {
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 1));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 2));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 3));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 4));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 5));
								//System.out.println(index + ""+ 1);
								//System.out.println(nodeList.get(index + ""+ 3).toString());
							}
							infoGrid.getChildren().remove(deleteBtn); 
							infoGrid.getChildren().remove(timeTF);
							infoGrid.getChildren().remove(categoryCB);
							infoGrid.getChildren().remove(entryCB);
							infoGrid.getChildren().remove(valueTF);
							infoGrid.getChildren().remove(commentTF);
							row--;
							infoGrid.getChildren().remove(addRowBtn);
							infoGrid.add(addRowBtn, 1, row);
						}
					});
				}if(categoryCB.getSelectionModel().getSelectedItem().equals("DCS MEP Messung")) {
					int size = dcsMepMuscleChoice.size();
					for(int i= 1; i< size; i++) {
						//existing Combobox is preselected with the first String of the MEP Muscle List
						entryCB.getSelectionModel().select(dcsMepMuscleChoice.get(0));
						//New Combobox with the matching String of the MEP Muscle List
						ComboBox<String> addicionalCB = new ComboBox<String>();
						addicionalCB.setItems(dcsMepMuscleChoice);
						addicionalCB.getSelectionModel().select(dcsMepMuscleChoice.get(i));

						categoryCB.setDisable(true);



						TextField AddidionalValue = new TextField();
						//Adding new Noddes to the NodeList, timeTF, CategoryCB and commentTF act as Dummys to not break the consistency of the List.
						nodeList.put(row + "1", timeTF);
						nodeList.put(row + "2", categoryCB);
						nodeList.put(row + "3", addicionalCB) ;
						nodeList.put(row + "4", AddidionalValue);
						nodeList.put(row + "5", commentTF);
						//nodeList.put(row + "2", new TextField());
						infoGrid.add(addicionalCB, 3, row);
						infoGrid.add(AddidionalValue, 4, row);
						row++;
						infoGrid.getChildren().remove(addRowBtn);
						infoGrid.add(addRowBtn, 1, row);
					}
					//Delete Button deletes the Row it is in and all additional Rows that are specifically created for the Measurement Input. 
					deleteBtn.addEventHandler(ActionEvent.ACTION,
							new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent event) {
							int index = GridPane.getRowIndex(deleteBtn);
							System.out.println(index);
							for(int i= index; i< (index + size); i++) {
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 1));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 2));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 3));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 4));
								infoGrid.getChildren().remove(nodeList.get(i + ""+ 5));
								//System.out.println(index + ""+ 1);
								//System.out.println(nodeList.get(index + ""+ 3).toString());
							}
							infoGrid.getChildren().remove(deleteBtn); 
							infoGrid.getChildren().remove(timeTF);
							infoGrid.getChildren().remove(categoryCB);
							infoGrid.getChildren().remove(entryCB);
							infoGrid.getChildren().remove(valueTF);
							infoGrid.getChildren().remove(commentTF);
							row--;
							infoGrid.getChildren().remove(addRowBtn);
							infoGrid.add(addRowBtn, 1, row);
						}
					});

				}if(categoryCB.getSelectionModel().getSelectedItem().equals("AEP Messung")) {

				}
				//Texfield for Valueis accessible for the user
				valueTF.setDisable(false);
				valueTF.setVisible(true);

			}else {
				//Texfield for Value is not accessible for the user because its not a Measurement Category
				valueTF.setDisable(true);
				valueTF.setVisible(false);
			}
		});


		//add nodes to HashMap, Key is ROW + Columnnumber. eg. Key = 21 for Node in ROW 2, Columne 1. 
		nodeList.put(row + "1", timeTF );
		nodeList.put(row + "2", categoryCB );
		nodeList.put(row + "3", entryCB );
		nodeList.put(row + "4", valueTF );
		nodeList.put(row + "5", commentTF );

		// register an action event for the dynamically created category combobox
		categoryCB.addEventHandler(ActionEvent.ACTION, 
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				showItem(categoryCB, entryCB);
			}
		});

		//add bin graphic for Delete Button
		ImageView view = new ImageView(Main.class.getResource("173-bin.png").toExternalForm());
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(1.0);
		view.setEffect(colorAdjust);
		deleteBtn.setGraphic(view);

		//add delete event on delete Button
		deleteBtn.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {

				// alert to ask if the user is sure to delete
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Eintrag löschen?");
				alert.setHeaderText("Eintrag wirklich löschen?");

				// Set the available buttons for the alert
				ButtonType btnYes = new ButtonType("Ja");
				ButtonType btnNo = new ButtonType("Nein");

				alert.getButtonTypes().setAll(btnYes, btnNo);

				// This allows you to get the response back from the user
				Optional<ButtonType> result = alert.showAndWait();

				// delete if the user chooses yes, don't delete if otherwise
				if (result.isPresent()) {
					int i = GridPane.getRowIndex(deleteBtn);
					System.out.println(i);
					infoGrid.getChildren().remove(nodeList.get(i + ""+ 1));
					System.out.println(i + ""+ 1);
					System.out.println(nodeList.get(i + ""+ 1).toString());
					infoGrid.getChildren().remove(deleteBtn); 
					infoGrid.getChildren().remove(timeTF);
					infoGrid.getChildren().remove(categoryCB);
					infoGrid.getChildren().remove(entryCB);
					infoGrid.getChildren().remove(valueTF);
					infoGrid.getChildren().remove(commentTF);
					row--;
				}
				if (result.get() == btnYes) {
				} else if (result.get() == btnNo) {
					alert.close();
				}
			}
		});

		//Add nodes to Grid
		infoGrid.add(timeTF, 1, row);
		infoGrid.add(categoryCB , 2, row); 
		infoGrid.add(entryCB, 3, row);
		infoGrid.add(valueTF, 4, row);
		infoGrid.add(commentTF, 5, row);
		infoGrid.add(deleteBtn, 6, row);
		//removes Add button from row to place it a row further below
		infoGrid.getChildren().remove(addRowBtn);
		row++;
		infoGrid.add(addRowBtn, 1, row);

	}





	/**
	 * 
	 * @param event
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public  void save(ActionEvent event) throws IOException, InterruptedException {
		
		 String documentUri = ontEdit.createNewIOMDocument("IOMDocumentTest");
		// Patient Data start
		savePatient(documentUri);
		//getSepBaselineValues();
		//getTesBaselineValues();
		//getDcsBaselineValues();
		//getAepBaselineValues();
		//getVepBaselineValues();
		//getReflexBaselineValues();
		// Patient Data end
		
		// SAVE BASELINES START
		
		getDcsBaselineValues(documentUri);
		getTesBaselineValues(documentUri);
		// SAVE BASELINES END

		//IOM actual Recording------------------------------------------------

		

		
		
		//Only a Kategory with Meassurement will be saved into the Ontology.  new Baseline not included! Basline measurment can be removed 
		String category1 =categoryIOMStart.getSelectionModel().getSelectedItem();
		String category1Uri = ontClassMap.getUriFromLabel(category1);
		
		ontEdit.addFindings(category1Uri, "IOM Start", timeStartTF.getText(),commentIOMStart.getText(), documentUri);
		//Fürs speichen, für usability test ausgeklammert
		int rowsToRead = nodeList.size()/4;
		for(int i= 2; i < rowsToRead + 1; i++) {
			System.out.println(i+ "" + 1);
			String time = getTextField(  nodeList.get( i  +"" + 1)).getText();
			
			String category = getComboBox(nodeList.get( i  +"" + 2)).getSelectionModel().getSelectedItem();
			String entry = getComboBox(nodeList.get( i  +"" + 3)).getSelectionModel().getSelectedItem();
			String categoryUri = ontClassMap.getUriFromLabel(category);
			String comment = getTextField(nodeList.get( i  +"" + 5)).getText();

			if (category.equalsIgnoreCase("sonstiges") || category.equalsIgnoreCase("IOM Ende")) {
			ontEdit.addFindings(categoryUri,  category, time , comment, documentUri);
			}
			else if (category.equalsIgnoreCase("Operationsprozess") || category.equalsIgnoreCase("Anästhesie Prozess") 
					|| category.equalsIgnoreCase("Technische Probleme") ) {
				ontEdit.addProcessObservationDatum(ontClassMap.getUriFromLabel(entry), entry, time, comment, documentUri);
			}
			else if(category.equalsIgnoreCase("intraoperative Disposition")) {
				//fehlt noch
			}
			else if (category.equals("Mapping Messung")){
				//unterkategorie und wert
				ontEdit.addMeasurement( ontClassMap.getUriFromLabel(entry), entry, time, comment, documentUri, getTextField(nodeList.get(i  + "" + 4)).getText());
			} 
			else if (category.equals("CBT Messung")) {
				//kategory und wert
				ontEdit.addMeasurement(categoryUri, category, time, comment, documentUri, getTextField(nodeList.get(i  + "" + 4)).getText());
			}
			else if(category.contains("MEP Messung")) {
				int numberOfMuscles = 0;
				if(category.equals("TES MEP Messung")) {
					numberOfMuscles = tesMepMuscleChoice.size();
				}if(category.equals("DCS MEP Messung")) {
					numberOfMuscles = dcsMepMuscleChoice.size();
				}
					String entryUri = ontClassMap.getUriFromLabel(entry);
					List<String> muscleUriList = new ArrayList<String>();
					List<String> muscleLabelList = new ArrayList<String>();
					List<String> valueList = new ArrayList<String>();
					for(int z= 0; z < numberOfMuscles; z++) {
						String entryTemp = getComboBox(nodeList.get( ((i+z)  +"" + 3))).getSelectionModel().getSelectedItem();
						String valueTemp = getTextField(nodeList.get((i+z)  + "" + 4)).getText();
						String muscleUri = ontClassMap.getUriFromLabel(entryTemp);
						muscleUriList.add(muscleUri);
						muscleLabelList.add(entryTemp);
						valueList.add(valueTemp);
					}	
					ontEdit.addMeasurement(categoryUri, category, time, comment, documentUri, valueList, muscleUriList, muscleLabelList);
					//System.out.println("lines to skip: " + numberOfMuscles );
					i = i + (numberOfMuscles -1);	
		}else {
			ontEdit.addFindings(ontClassMap.getUriFromLabel(entry), entry, time, comment, documentUri);
		}
		}
		
		//speichern der intraoperativen Dispositionen
		String outcome = outcomeCB.getSelectionModel().getSelectedItem();
		String outcomeComment = outcomeCommentTF.getText();
		//addDisposition Methode is missing!!
		if(outcome != null) {
		ontEdit.addDisposition(ontClassMap.getUriFromLabel(outcome), outcome, outcomeComment, documentUri);
		}
		
		 
		// a new alert to inform the user that the data was saved successfully
		//Alert alert = new Alert(Alert.AlertType.INFORMATION);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Gespeichert");
		alert.setContentText("Daten erfolgreich abgespeichert!");
		alert.show();
		//alert.showAndWait();

		//alles zurücksetzen
		//documenturi auf null oder nächste id?
		//String documentUri = "";
		//caseNrTF.setText("");
		//fidTF.setText("");
		//pidTF.setText("");
		//surnameTF.setText("");
		//firstNameTF.setText("");
		//timeStartTF.setText("");
		//birthdayTF.setText("");

		//diagnosisCB.getSelectionModel().clearSelection();
		//surgeryCB.getSelectionModel().clearSelection();
		//deviceCB.getSelectionModel().clearSelection();
		//dateOfSurgeryTF.setText("");
		//surgeonCB.getSelectionModel().clearSelection();
		//assistantCB.getSelectionModel().clearSelection();


		//commentIOMStart.setText("");
		//int rowsToRead1 = nodeList.size()/4;
		//for(int i= 2; i <= rowsToRead1 + 1; i++) {
			//getTextField(  nodeList.get( i  +"" + 1)).setText("");;
			//getComboBox(nodeList.get( i  +"" + 2)).getSelectionModel().clearSelection();;
			//getComboBox(nodeList.get( i  +"" + 3)).getSelectionModel().clearSelection();

			//TextField valueTF = getTextField(  nodeList.get( i  +"" + 4));
			//if(valueTF != null) {
			//	getTextField(  nodeList.get( i  +"" + 4)).setText("");;
			//}

			//getTextField(nodeList.get( i  +"" + 5)).setText("");
			//IOM Documentation end
		//}


		/*
			if(category1.contains(I18n.getString("rec.measurement"))) {
				//uri fehlt noch!
				ontEdit.addStatement(document, NS + has_data_item , category);
				//ontEdit.addTimestampToEntity(category, time);
				String value = getTextField(  nodeList.get( i  +"" + 4)).getText();


				//Add Value to measurement needs to be added!
			}else {
				String entry = getComboBox(nodeList.get( i  +"" + 3)).getSelectionModel().getSelectedItem();
				String entryUri = map.getUri(entry);
				ontEdit.addStatement(document, NS + has_data_item , entryUri);
				//ontEdit.addTimestampToEntity(entryUri, time);
			}




			//if(!comment.isEmpty()) {
				//save comment
			//}

		}

		//ontEdit.saveNewOWLFile();

		//String has_document = "0000285";

		/*
	

			//IOM Documentation end

		}

		 */

	}

	/*
	public String getTextFromNode(Node node) {
		if (node.getClass().cast(node).getClass().equals(tf.getClass())) {
			TextField test = (TextField) node.getClass().cast(node);
			return test.getText();
		}

		return "";
	}
	 */

	/**
	 * a method to show the right item in the second combobox of the IOM Documentation
	 */
	public void showItem(ComboBox<String> category, ComboBox<String> entry) {
		String item = category.getSelectionModel().getSelectedItem();
		switch (item) {
		case "VEP Beobachtung":
			entry.setVisible(true);
			Collections.sort(vepFindingList);
			entry.setItems(vepFindingList);
			break;
		case "Mapping Messung":
			entry.setVisible(true);
			Collections.sort(mappingMeasurementList);
			entry.setItems(mappingMeasurementList);
			break;
		case "Technische Probleme":
			entry.setVisible(true);
			Collections.sort(technicalIssuesList);
			entry.setItems(technicalIssuesList);
			break;
			//case "D-Welle Messung":
			//entry.setItems(dwaveMeasurementList);
			//break;
		case "TES MEP Messung":
			entry.setVisible(true);
			Collections.sort(tesMepMuscleChoice);
			entry.setItems(tesMepMuscleChoice);
			break;
		case "DCS MEP Messung":
			entry.setVisible(true);
			Collections.sort(dcsMepMuscleChoice);
			entry.setItems(dcsMepMuscleChoice);
			break;
			/*
		case "VEP Messung":
			entry.setItems(vepMeasurementList);
		case "SEP Messung":
			entry.setItems(sepMeasurementList);
			break;
			 */
		case "AEP Messung":
			entry.setVisible(true);
			Collections.sort(aepMeasurementList);
			entry.setItems(aepMeasurementList);
			break;
		case "CBT Messung":
			entry.setVisible(false);
			//entry.setItems(cbtMeasurementList); gibts nicht
			break;
		case "Operationsprozess":
			entry.setVisible(true);
			Collections.sort(surgeryProcessList);
			entry.setItems(surgeryProcessList);
			break;
		case "Reflex Beobachtung":
			entry.setVisible(true);
			Collections.sort(reflexFindingList);
			entry.setItems(reflexFindingList);
			break;
		case "EEG Beobachtung":
			entry.setVisible(true);
			Collections.sort(eegFindingList);
			entry.setItems(eegFindingList);
			break;
		case "Anästhesie Prozess":
			entry.setVisible(true);
			Collections.sort(anesthesyProcessList);
			entry.setItems(anesthesyProcessList);
			break;
		case "Mapping Beobachtung":
			entry.setVisible(true);
			Collections.sort(mappingFindingList);
			entry.setItems(mappingFindingList);
			break;
			//case "IOM Prozess":
			//entry.setItems(iomProcessList);
			//break;
		case "SEP Beobachtung":
			entry.setVisible(true);
			Collections.sort(sepFindingList);
			entry.setItems(sepFindingList);
			break;
			case "AEP Beobachtung":
			entry.setVisible(true);
			Collections.sort(aepFindingList);
			entry.setItems(aepFindingList);
			break;
		case "D-Welle Beobachtung":
			entry.setVisible(true);
			Collections.sort(dwaveFindingList);
			entry.setItems(dwaveFindingList);
			break;
		case "EMG Beobachtung":
			entry.setVisible(true);
			Collections.sort(emgFindingList);
			entry.setItems(emgFindingList);
			break;
		case "MEP Beobachtung":
			entry.setVisible(true);
			Collections.sort(mepFindingList);
			entry.setItems(mepFindingList);
			break;
			/*
		case "Aktion":
			entry.setVisible(true);
			Collections.sort(actionList);
			entry.setItems(actionList);
			break;
			 */
		case "Grid Positionierung":
			entry.setVisible(true);
			Collections.sort(gridPositioningList);
			entry.setItems(gridPositioningList);
			break;
		case "IOM Ende":
			entry.setVisible(false);
			break;
		case "sonstiges":
			entry.setVisible(false);
			break;
		case " ":
			break;
		}	
	}




	@SuppressWarnings("exports")
	public TextField getTextField(Node node) {
		return (TextField) node.getClass().cast(node);
	}


	@SuppressWarnings("unchecked")
	public ComboBox<String> getComboBox(Node node) {
		return (ComboBox<String>) node.getClass().cast(node);
	}

	//----------------------------------------------IOM Documentation End



}
