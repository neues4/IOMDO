package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.model.Counter;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Controller for the recording durinig Intraoperative Neuromonitoring
 * @author romap1, neues4
 *
 */
public class IOMRecordingController {

//This class is split in 3 Parts for a better Overview: Part1: Patient/Surgery Data, Part2: Baselines, Part3: IOM actual Recording

	//Variables Patient/Surgery Data-----------------------------------

	@FXML
	private TextField caseNrTF;

	@FXML
	public TextField surnameTF;

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

	//-----------------------------------Variables Patient/Surgery Data

	
	//Variables Baselines ---------------------------------------------

	@FXML
	private Accordion accordion;

	@FXML
	private GridPane tesMepGridpane;

	@FXML
	private ComboBox<String> cbTes1, cbTes2, cbTes3, cbTes4, cbTes5, cbTes6, cbTes7, cbTes8, cbTes9, cbTes10, cbTes11, cbTes12, cbTes13, cbTes14;

	@FXML
	private ComboBox<String> cbDcs1, cbDcs2, cbDcs3, cbDcs4, cbDcs5, cbDcs6, cbDcs7, cbDcs8, cbDcs9, cbDcs10;

	@FXML
	private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14;

	@FXML
	private TextField tfDcs1, tfDcs2, tfDcs3, tfDcs4, tfDcs5, tfDcs6, tfDcs7, tfDcs8, tfDcs9, tfDcs10;


	@FXML
	private TextField tfMedLN, tfMedRN, tfTibLN, tfTibRN, tfMedLP, tfMedRP, tfTibLP, tfTibRP;

	@FXML
	private Button btnSaveTesMep, btnSaveSsep, btnSaveDcsMep;


	//-------------------------------------------------------Variables Baselines
	
	//Variables IOM actual Recording-------------------------------------------------

		@FXML
		private Button addRowBtn;

		@FXML
		private RowConstraints row2;

		@FXML
		private GridPane infoGrid;

		@FXML
		private SplitPane splitpane;

		@FXML
		private Button save;

		@FXML
		private TextField timeStartTF;

		@FXML
		private ComboBox<String> categoryIOMStart;

		@FXML
		private ComboBox<String> entryIOMStart;

		@FXML
		private TextField commentIOMStart;

		@FXML
		private Label patientNameLbl;

		@FXML
		private Label birthdayLbl;

		@FXML
		private Label diagnoseLbl;

		@FXML
		private Label surgeryLbl;

		@FXML
		private Label dateLbl;

		//---------------------------------------------------Variables IOM actual Recording
	
	

	// new instance of Ontology Editor
	OntologyEditor ontEdit = OntologyEditor.getInstance();
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

		ontEdit.addPropertiesToPatient(pat, caseNrTF.getText(), pidTF.getText(), fidTF.getText(), firstNameTF.getText(), surnameTF.getText(), birthdayTF.getText());

		String surgery= ontEdit.createNewIndividual(ontEdit.getAllSurgeries().get(surgeryCB.getSelectionModel().getSelectedItem()), "Surgery");

		ontEdit.addPropertiesToSurgery(surgery, dateOfSurgeryTF.getText(), surgeonCB.getSelectionModel().getSelectedItem(), assistantCB.getSelectionModel().getSelectedItem(), deviceCB.getSelectionModel().getSelectedItem());

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




	public  void saveToVariable(MouseEvent event) throws IOException {

	}
	
	
	// Baseline Start
	ObservableList<String> muscleList = FXCollections.observableArrayList(ontEdit.getAllMuscles().keySet());
	
	Counter muscNumber = new Counter(1);
	Counter mANumber = new Counter(1);
	// Baseline End
	
	
	


	//IOM Recording Start-----------------------------------------------

	//Counter for the Rows in the GridPane. Row 0 is Empty and Row 1 already has Nodes. Therefore the counter starts at 2.
	private int row = 2;

	private Map<String, Node> nodeList = new HashMap<String, Node>();

	private ObservableList<String> entryList = FXCollections.observableArrayList("entry1", "entry2");

	// create the list for the first dropdown
	private ObservableList<String> categoryList = FXCollections.observableArrayList(ontEdit.getAllEntitiesToBeShown().keySet());

	// create all the lists for the second dropdown of the IOM Documentation
	private ObservableList<String> vepFindingList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000403").keySet());
	private ObservableList<String> mappingMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000373").keySet());
	private ObservableList<String> technicalIssuesList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000154").keySet());
	private ObservableList<String> dwaveMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000369").keySet());
	private ObservableList<String> tesMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000378").keySet());
	private ObservableList<String> dcsMepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000238").keySet());
	private ObservableList<String> vepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000244").keySet());
	private ObservableList<String> sepMeasurementList = FXCollections.observableArrayList(ontEdit.getSubclasses("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000242").keySet());
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


	
	/**
	 * Adds a new Set of Nodes in the next empty Row of the GridPane.
	 * @param event
	 * @throws IOException
	 */
	public  void addRow(ActionEvent event) throws IOException {
		//create nessecary nodes
		TextField timeTF = new TextField();	
		ComboBox<String> categoryCB = new ComboBox<String>();
		categoryCB.setItems(categoryList);
		ComboBox<String> entryCB = new ComboBox<String>();
		//entryCB.setItems(entryList);
		TextField commentTF = new TextField();
		Button deleteBtn = new Button();

		//E:\Bachelorthesis2021Workspace\maven.1618306824553\IOMDOProject\src\main\resources\bachelorthesis\IOMDOProject
		//Image img = new Image("/src/main/resources/bachelorthesis/IOMDOProject/173-bin.png");
		//ImageView view = new ImageView(img);
		// deleteBtn.setGraphic(view);

		//add nodes to HashMap, Key is ROW + Columnnumber. eg. Key = 21 for Node in ROW 2, Columne 1. 
		nodeList.put(row + "1", timeTF );
		nodeList.put(row + "2", categoryCB );
		nodeList.put(row + "3", entryCB );
		nodeList.put(row + "5", commentTF );

		// register an action event for the dynamically created category combobox
		categoryCB.addEventHandler(ActionEvent.ACTION, 
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				showItem(categoryCB, entryCB);
			}
		});


		//add delete event on delete Button
		deleteBtn.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				infoGrid.getChildren().remove(deleteBtn); 
				infoGrid.getChildren().remove(timeTF);
				infoGrid.getChildren().remove(categoryCB);
				infoGrid.getChildren().remove(entryCB);
				infoGrid.getChildren().remove(commentTF);
				row--;
			}
		});

		//Add nodes to Grid
		infoGrid.add(timeTF, 1, row);
		infoGrid.add(categoryCB , 2, row); 
		infoGrid.add(entryCB, 3, row);
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
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public  void save(ActionEvent event) throws IOException {
		// Patient Data start
		savePatient();
		// Patient Data end
		
		//IOM Documentation start
		System.out.println("Zeit: " + timeStartTF.getText());
		System.out.println("Kategorie: " + categoryIOMStart.getSelectionModel().getSelectedItem());
		System.out.println("Eintrag: " + entryIOMStart.getSelectionModel().getSelectedItem());
		System.out.println("Kommentar: " + commentIOMStart.getText());

		int rowsToRead = nodeList.size()/4;
		for(int i= 1; i <= rowsToRead; i++) {
			System.out.println("Zeit: " + getTextField(  nodeList.get( i + 1 +"" + 1)).getText());
			System.out.println("Kategorie: " + getComboBox(nodeList.get( i + 1 +"" + 2)).getSelectionModel().getSelectedItem());
			System.out.println("Eintrag: " + getComboBox(nodeList.get( i + 1 +"" + 3)).getSelectionModel().getSelectedItem());
			System.out.println("Kommentar: " + getTextField(nodeList.get( i + 1 +"" + 5)).getText());
			//IOM Documentation end
		}

	}

	/**
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
			entry.setItems(tesMepMeasurementList);
			break;
		case "DCS MEP Messung":
			entry.setItems(dcsMepMeasurementList);
			break;
		case "VEP Messung":
			entry.setItems(vepMeasurementList);
		case "SEP Messung":
			entry.setItems(sepMeasurementList);
			break;
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
