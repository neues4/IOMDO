package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bachelorthesis.IOMDOProject.I18n;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class IOMRecordingController {



	//Start Patient and Surgery Data

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

	//End Patient and Surgery Data

	//Start IOM Documentation 

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

	//End IOM Documentation


	//Start Baselines Section

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


	//End Baseline Section









	public  void saveToVariable(ActionEvent event) throws IOException {

	}


	//IOM Documentation Start
	OntologyEditor ontEdit = OntologyEditor.getInstance();

	//Counter for the Rows in the GridPane. Row 0 is Empty and Row 1 already has Nodes. Therefore the counter starts at 2.
	private int row = 2;

	private Map<String, Node> nodeList = new HashMap<String, Node>();

	private ObservableList<String> entryList = FXCollections.observableArrayList("entry1", "entry2");

	private ObservableList<String> categoryList = FXCollections.observableArrayList(ontEdit.getAllEntitiesToBeShown().keySet());


	public void initialize() {

		//IOM Documentation start
		categoryIOMStart.setItems(categoryList);
		entryIOMStart.setItems(entryList);
		//IOM Documentation end
	}

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
		entryCB.setItems(entryList);
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

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public  void save(ActionEvent event) throws IOException {
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


	@SuppressWarnings("exports")
	public TextField getTextField(Node node) {
		return (TextField) node.getClass().cast(node);
	}


	@SuppressWarnings("unchecked")
	public ComboBox<String> getComboBox(Node node) {
		return (ComboBox<String>) node.getClass().cast(node);
	}

	//IOM Documentation End



}
