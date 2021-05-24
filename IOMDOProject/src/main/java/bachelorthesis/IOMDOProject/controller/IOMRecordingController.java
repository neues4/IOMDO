package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	
	
	
	

	public  void save(ActionEvent event) throws IOException {

	}

	public  void addRow(ActionEvent event) throws IOException {

	}

	public  void saveToVariable(ActionEvent event) throws IOException {

	}


}
