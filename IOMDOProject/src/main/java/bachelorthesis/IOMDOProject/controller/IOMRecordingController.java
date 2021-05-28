package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import bachelorthesis.IOMDOProject.model.Counter;
import bachelorthesis.IOMDOProject.model.EntryMap;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.PatientSurgeryData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
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
	private Button btnSaveTesMep, btnSaveSsep, btnSaveDcsMep;

	@FXML
	private TitledPane SSEPPane, TESMEPPane, DCSMEPPane, AEPPane, VEPPane, reflexPane;


	//-------------------------------------------------------Variables Baselines

	//Variables IOM actual Recording-------------------------------------------------

	@FXML
	private Button addRowBtn, save;

	@FXML
	private RowConstraints row2;

	@FXML
	private GridPane infoGrid;

	@FXML
	private SplitPane splitpane;

	@FXML
	private TextField timeStartTF, commentIOMStart, value;

	@FXML
	private ComboBox<String> categoryIOMStart;

	@FXML
	private ComboBox<String> entryIOMStart;

	@FXML
	private Label patientNameLbl, birthdayLbl,diagnoseLbl, surgeryLbl, dateLbl ;

	// a map for the tf in sep baselines
	private Map<String, Node> nodeMapSepBaselines = new HashMap<String, Node>();

	// a map for the cb and tf in tes baselines
	private Map<String, Node> nodeMapTesBaselines = new HashMap<String, Node>();

	// a map for the cb and tf in dcs baselines
	private Map<String, Node> nodeMapDcsBaselines = new HashMap<String, Node>();


	//---------------------------------------------------Variables IOM actual Recording



	// new instance of Ontology Editor
	private OntologyEditor ontEdit = OntologyEditor.getInstance();

	public void initialize() {
		// Patient data start
		diagnosisCB.setItems(diagnosisList);
		surgeryCB.setItems(surgeryList);
		surgeonCB.setItems(surgeonList);
		deviceCB.setItems(deviceList);
		assistantCB.setItems(assistantList);
		// Patient data end

		//IOM Documentation start
		categoryIOMStart.setItems(categoryList);
		//entryIOMStart.setItems(entryList);
		//IOM Documentation end

		// Baseline start
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

	public void savePatient () {
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
	public void getTesBaselineValues() {
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
		String tesMepBaselineMeasurement = ontEdit.createNewIndividual(ontEdit.getBaselines().get("TES MEP Baseline Messung"), "TesMepBaselineMeasurement");
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
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle);
					mATesBaselineCounter.increment();
				}
				// see if the second text field is filled out, read the value, create new instances in ontology
				if(getTextField(nodeMapTesBaselines.get( i  +"" + 2)).getText() != "") {
					String maValue = getTextField(nodeMapTesBaselines.get( i  +"" + 2)).getText();
					String mA = ontEdit.createNewMiliampere("mATesBasline".concat(mATesBaselineCounter.toString()));
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle);
					mATesBaselineCounter.increment();
				}
				// see if the third text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapTesBaselines.get( i  +"" + 3)).getText() != "") {
					String maValue = getTextField(nodeMapTesBaselines.get( i  +"" + 3)).getText();
					String mA = ontEdit.createNewMiliampere("mATesBasline".concat(mATesBaselineCounter.toString()));
					createTesBaselineStatement(mA, maValue, tesMepBaselineMeasurement, muscle);
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
	public void createTesBaselineStatement(String mA, String maValue, String tesMepBaselineMeasurement, String muscle) {
		ontEdit.addPropertiesToMiliampere(mA, maValue);
		ontEdit.addStatement(tesMepBaselineMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle);
	}


	/**
	 * a method to read all mA values in the text boxes of dcs baseline and create instances of muscle, mA and data item
	 * FEHLT NOCH VERBINDUNG ZUM IOM PROTOKOLL!
	 */
	public void getDcsBaselineValues() {
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
		String dcsMepBaselineMeasurement = ontEdit.createNewIndividual(ontEdit.getBaselines().get("DCS MEP Baseline Messung"), "DcsMepBaselineMeasurement");
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
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle);
					mADcsBaselineCounter.increment();
				}
				// see if second text field is filled out, read the value, create new instances in ontology
				if(getTextField(nodeMapDcsBaselines.get( i  +"" + 2)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 2)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle);
					mADcsBaselineCounter.increment();
				}
				// see if third text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapDcsBaselines.get( i  +"" + 3)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 3)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle);
					mADcsBaselineCounter.increment();

				}
				// see if fourth text field is filled out, read the value, create new instances in ontology
				if (getTextField(nodeMapDcsBaselines.get( i  +"" + 4)).getText() != "") {
					String maValue = getTextField(nodeMapDcsBaselines.get( i  +"" + 4)).getText();
					String mA = ontEdit.createNewMiliampere("mADcsBaseline".concat(mADcsBaselineCounter.toString()));
					createDcsBaselineStatement(mA, maValue, dcsMepBaselineMeasurement, muscle);
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
	public void createDcsBaselineStatement(String mA, String maValue, String dcsMepBaselineMeasurement, String muscle) {
		ontEdit.addPropertiesToMiliampere(mA, maValue);
		ontEdit.addStatement(dcsMepBaselineMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle);
	}


	public void cbTes1isUsed(ActionEvent event) {
		if (!cbTes1.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes2isUsed(ActionEvent event) {
		if (!cbTes2.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes3isUsed(ActionEvent event) {
		if (!cbTes3.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes4isUsed(ActionEvent event) {
		if (!cbTes4.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes5isUsed(ActionEvent event) {
		if (!cbTes5.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes6isUsed(ActionEvent event) {
		if (!cbTes6.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes7isUsed(ActionEvent event) {
		if (!cbTes7.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes8isUsed(ActionEvent event) {
		if (!cbTes8.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes9isUsed(ActionEvent event) {
		if (!cbTes9.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes10isUsed(ActionEvent event) {
		if (!cbTes10.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes11isUsed(ActionEvent event) {
		if (!cbTes11.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes12isUsed(ActionEvent event) {
		if (!cbTes12.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes13isUsed(ActionEvent event) {
		if (!cbTes13.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes14isUsed(ActionEvent event) {
		if (!cbTes14.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes15isUsed(ActionEvent event) {
		if (!cbTes15.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
	}
	public void cbTes16isUsed(ActionEvent event) {
		if (!cbTes16.getSelectionModel().getSelectedItem().isBlank()) {
			TESMEPPane.setTextFill(Color.GREEN);}
		else {
			TESMEPPane.setTextFill(Color.BLACK);}
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

	//Counter for the Rows in the GridPane. Row 0 is Empty and Row 1 already has Nodes. Therefore the counter starts at 2.
	private int row = 2;

	private Map<String, Node> nodeList = new HashMap<String, Node>();

	//private ObservableList<String> entryList = FXCollections.observableArrayList("entry1", "entry2");

	// create the list for the first dropdown
	private ObservableList<String> categoryList = FXCollections.observableArrayList(ontEdit.getAllEntitiesToBeShown().keySet());

	// create all the lists for the second dropdown of the IOM Documentation
	private ObservableList<String> vepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000403").keySet());
	private ObservableList<String> mappingMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000373").keySet());
	private ObservableList<String> technicalIssuesList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000154").keySet());
	private ObservableList<String> dwaveMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000369").keySet());
	//private ObservableList<String> tesMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000378").keySet());
	//private ObservableList<String> dcsMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000238").keySet());
	//private ObservableList<String> vepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000244").keySet());
	//private ObservableList<String> sepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000242").keySet());
	private ObservableList<String> aepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000240").keySet());
	private ObservableList<String> cbtMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000371").keySet());
	private ObservableList<String> surgeryProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000058").keySet());
	private ObservableList<String> reflexFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000234").keySet());
	private ObservableList<String> eegFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000231").keySet());
	private ObservableList<String> anesthesyProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000159").keySet());
	private ObservableList<String> mappingFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000376").keySet());
	private ObservableList<String> iomProcessList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000057").keySet());
	private ObservableList<String> sepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000401").keySet());
	private ObservableList<String> aepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000402").keySet());
	private ObservableList<String> dwaveFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000387").keySet());
	private ObservableList<String> emgFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000370").keySet());
	private ObservableList<String> mepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000400").keySet());
	private ObservableList<String> actionList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://medicis/spm.owl/OntoSPM#manipulating_action_by_human").keySet());

	// a list for the muscles chosen in tes mep baseline
	private ObservableList<String> tesMepMuscleChoice = FXCollections.observableArrayList();

	// a list for the muscles chosen in dcs mep baseline
	private ObservableList<String> dcsMepMuscleChoice = FXCollections.observableArrayList();

	// a map for the comboboxes in tes mep
	private Map<Integer, ComboBox<String>> cbTesMap = new HashMap<Integer, ComboBox<String>>();

	// a map for the comboboxes in dcs mep
	private Map<Integer, ComboBox<String>> cbDcsMap = new HashMap<Integer, ComboBox<String>>();





	/**
	 * Adds a new Set of Nodes in the next empty Row of the GridPane.
	 * @param event
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public  void addRow(ActionEvent event) throws IOException, URISyntaxException {
		//create nessecary nodes
		TextField timeTF = new TextField();	
		Tooltip tp = new Tooltip();
		tp.setText("this is a test");
		timeTF.setTooltip(tp);
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

		//Texfield for value is only visible when a category with measurement selected. 
		categoryCB.setOnAction ((selectedItem) -> {
			if(categoryCB.getSelectionModel().getSelectedItem().contains(I18n.getString("rec.measurement"))) {
				valueTF.setDisable(false);
				valueTF.setVisible(true);
			}else {
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
		deleteBtn.setGraphic(view);

		//add delete event on delete Button
		deleteBtn.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				infoGrid.getChildren().remove(deleteBtn); 
				infoGrid.getChildren().remove(timeTF);
				infoGrid.getChildren().remove(categoryCB);
				infoGrid.getChildren().remove(entryCB);
				infoGrid.getChildren().remove(valueTF);
				infoGrid.getChildren().remove(commentTF);
				row--;
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



	// IOM Documentation Category Combobox
	// MUSS NOCH IM FXML HINZUGEFÜGT WERDEN!!
	@FXML
	void categoryIOMStartOnAction(ActionEvent event) {
		showItem(categoryIOMStart, entryIOMStart);

		gatherTesMuscles();

		gatherDcsMuscles();

	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public  void save(ActionEvent event) throws IOException {
		// Patient Data start
		//savePatient();
		//getSepBaselineValues();
		//getTesBaselineValues();
		//getDcsBaselineValues();
		//getAepBaselineValues();
		//getVepBaselineValues();
		//getReflexBaselineValues();
		// Patient Data end






		//IOM actual Recording------------------------------------------------

		String document = ontEdit.createNewIOMDocument("IOMDocumentTest");
		String NS = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/";
		String has_data_item = "IOMO_0000282";

		String entry1= entryIOMStart.getSelectionModel().getSelectedItem();


		EntryMap map = new EntryMap();


		//Only a Kategory with Meassurement will be saved into the Ontology.  new Baseline not included! Basline measurment can be removed 
		String category1 =categoryIOMStart.getSelectionModel().getSelectedItem();
		String category1Uri = map.getUri(category1);
		System.out.println(category1Uri);
		if(category1.contains(I18n.getString("rec.measurement"))) {
			ontEdit.addStatement(document, NS + has_data_item , category1Uri );
			//value.getText();
			//timestamp funktioniert noch nicht!
			//ontEdit.addTimestampToEntity(category1Uri, "timestamp");
			//Add Value to measurement needs to be added!
			ontEdit.saveNewOWLFile();
		}; 



		int rowsToRead = nodeList.size()/4;
		for(int i= 2; i <= rowsToRead + 1; i++) {
			String time = getTextField(  nodeList.get( i  +"" + 1)).getText();
			String category = getComboBox(nodeList.get( i  +"" + 2)).getSelectionModel().getSelectedItem();

			if(category1.contains(I18n.getString("rec.measurement"))) {
				//uri fehlt noch!
				ontEdit.addStatement(document, NS + has_data_item , category);
				//ontEdit.addTimestampToEntity(category, time);
				value.getText();

				//Add Value to measurement needs to be added!
			}else {
				String entry = getComboBox(nodeList.get( i  +"" + 3)).getSelectionModel().getSelectedItem();
				String entryUri = map.getUri(entry);
				ontEdit.addStatement(document, NS + has_data_item , entryUri);
				//ontEdit.addTimestampToEntity(entryUri, time);
			}



			String comment = getTextField(nodeList.get( i  +"" + 5)).getText();
			if(!comment.isEmpty()) {
				//save comment
			}

		}

		//ontEdit.saveNewOWLFile();

		//String has_document = "0000285";

		/*
		System.out.println("Zeit: " + timeStartTF.getText());
		System.out.println("Kategorie: " + categoryIOMStart.getSelectionModel().getSelectedItem());
		System.out.println("Eintrag: " + entryIOMStart.getSelectionModel().getSelectedItem());
		System.out.println("Kommentar: " + commentIOMStart.getText());


		int rowsToRead = nodeList.size()/4;
		for(int i= 2; i <= rowsToRead + 1; i++) {
			System.out.println("Zeit" + i + ": "+ getTextField(  nodeList.get( i  +"" + 1)).getText());
			System.out.println("Kategorie"+ i +": "+ getComboBox(nodeList.get( i  +"" + 2)).getSelectionModel().getSelectedItem());
			System.out.println("Eintrag" + i + ": "+getComboBox(nodeList.get( i  +"" + 3)).getSelectionModel().getSelectedItem());
			System.out.println("Kommentar" + i+ ": "+getTextField(nodeList.get( i  +"" + 5)).getText());
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
			entry.setItems(vepFindingList);
			break;
		case "Mapping Messung":
			entry.setItems(mappingMeasurementList);
			break;
		case "Technische Probleme":
			entry.setItems(technicalIssuesList);
			break;
		case "D-Welle Messung":
			entry.setItems(dwaveMeasurementList);
			break;
		case "TES MEP Messung":
			entry.setItems(tesMepMuscleChoice);
			break;
		case "DCS MEP Messung":
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
			entry.setItems(aepMeasurementList);
			break;
		case "CBT Messung":
			entry.setItems(cbtMeasurementList);
			break;
		case "Operationsprozess":
			entry.setItems(surgeryProcessList);
			break;
		case "Reflex Beobachtung":
			entry.setItems(reflexFindingList);
			break;
		case "EEG Beobachtung":
			entry.setItems(eegFindingList);
			break;
		case "Anästhesie Prozess":
			entry.setItems(anesthesyProcessList);
			break;
		case "Mapping Beobachtung":
			entry.setItems(mappingFindingList);
			break;
		case "IOM Prozess":
			entry.setItems(iomProcessList);
			break;
		case "SEP Beobachtung":
			entry.setItems(sepFindingList);
			break;
		case "AEP Beobachtung":
			entry.setItems(aepFindingList);
			break;
		case "D-Welle Beobachtung":
			entry.setItems(dwaveFindingList);
			break;
		case "EMG Beobachtung":
			entry.setItems(emgFindingList);
			break;
		case "MEP Beobachtung":
			entry.setItems(mepFindingList);
			break;
		case "Aktion":
			entry.setItems(actionList);
		case " ":
			break;
		}	
	}

	/**
	 * a method to gather all the muscles which were chosen in the baseline (TES MEP) section 
	 */
	public void gatherTesMuscles() {
		cbTesMap.put(1, cbTes1); cbTesMap.put(2, cbTes2); cbTesMap.put(3, cbTes3); cbTesMap.put(4, cbTes4); 
		cbTesMap.put(5, cbTes5); cbTesMap.put(6, cbTes6); cbTesMap.put(7, cbTes7); cbTesMap.put(8, cbTes8);
		cbTesMap.put(9, cbTes9); cbTesMap.put(10, cbTes10); cbTesMap.put(11, cbTes11); cbTesMap.put(12, cbTes12);
		cbTesMap.put(13, cbTes13); cbTesMap.put(14, cbTes14); cbTesMap.put(15, cbTes15); cbTesMap.put(16, cbTes16);

		for (int i = 1; i <= cbTesMap.size(); i ++) {
			if (cbTesMap.get(i).getSelectionModel().getSelectedItem() != null) {
				tesMepMuscleChoice.addAll(cbTesMap.get(i).getSelectionModel().getSelectedItem());
			}	
		}
	}


	/**
	 * a method to gather all the muscles which were chosen in the baseline (TES MEP) section and put them in a map
	 */
	public void gatherDcsMuscles() {
		cbDcsMap.put(1, cbDcs1); cbDcsMap.put(2, cbDcs2); cbDcsMap.put(3, cbDcs3); cbDcsMap.put(4, cbDcs4); cbDcsMap.put(5, cbDcs5);
		cbDcsMap.put(6, cbDcs6); cbDcsMap.put(7, cbDcs7); cbDcsMap.put(8, cbDcs8); cbDcsMap.put(9, cbDcs9); cbDcsMap.put(10, cbDcs10);

		for (int i = 1; i <= cbDcsMap.size(); i ++) {
			if (cbDcsMap.get(i).getSelectionModel().getSelectedItem() != null) {
				dcsMepMuscleChoice.addAll(cbDcsMap.get(i).getSelectionModel().getSelectedItem());
			}	
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
