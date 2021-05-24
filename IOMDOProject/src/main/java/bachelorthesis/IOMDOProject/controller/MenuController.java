package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import bachelorthesis.IOMDOProject.model.PatientSurgeryData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * 
 * @author romap1
 *
 */
public class MenuController {

	@FXML
	private Button newProtocolBtn;
	@FXML
	private Button protocolOverviewBtn;
	@FXML
	private Button createQueryBtn;
	@FXML
	private Button expandOntologyBtn;
	@FXML
	private Button settingsBtn;
	@FXML
	private Button logOutBtn;
	@FXML
	private Button saveBtn;

	// ÜBERGANGSLÖSUNG
	@FXML
	private Button baselinesBtn;
	@FXML
	private Button patientDataBtn;
	@FXML
	private Button iomDocumentationBtn;

	@FXML
	private Label userName;


	@FXML public BorderPane borderPaneMenu;

	@FXML
	private GridPane gridPane;

	public void initialize() {
		
	}

	//übergangslösung für verknüpfung zuIOMDocumentation.fxml
	public  void openIOMDocumentation(ActionEvent event) throws IOException {
		
		
		
		changeCenter("IOMDocumentation.fxml");
		baselinesBtn.setDisable(false);
		patientDataBtn.setDisable(true);
		iomDocumentationBtn.setDisable(false);
		saveBtn.setDisable(false);
		

	}

	public void openNewProtocol(ActionEvent event) throws IOException {
		//changeCenter("RecordDocument_PatientData.fxml");
		changeCenter("IOMRecording.fxml");
		baselinesBtn.setVisible(true);
		patientDataBtn.setVisible(true);
		iomDocumentationBtn.setVisible(true);
		saveBtn.setVisible(true);
		baselinesBtn.setDisable(false);
		patientDataBtn.setDisable(true);
		iomDocumentationBtn.setDisable(true);
		saveBtn.setDisable(true);
	}

	public  void openProtocolOverview(ActionEvent event) throws IOException {
		changeCenter("ProtocolOverview.fxml");
		baselinesBtn.setVisible(false);
		patientDataBtn.setVisible(false);
		iomDocumentationBtn.setVisible(false);
		saveBtn.setVisible(false);
	}
	// ÜBERGANGSLÖSUNG
	public void openBaselines(ActionEvent event) throws IOException {
		
//Reads Name of Patient and prints it. Doesnt work, getName() returns "";
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("RecordDocument_PatientData.fxml"));
		loader.setResources(I18n.getResourceBundle());
		GridPane pane = loader.load();
		RDPatDatController controller = loader.<RDPatDatController>getController();
		//controller.initialize();
		String s = controller.getName();
		System.out.println( "Name:" + s);
		
		String s0 = PatientSurgeryData.getInstance().getSurname();
		System.out.println(s0);
		
		
		
		changeCenter("Baselines.fxml");
		patientDataBtn.setDisable(false);
		iomDocumentationBtn.setDisable(false);
		saveBtn.setDisable(true);
		
	}
	
	@FXML
	public void test(MouseEvent event) throws IOException {
		
		String s0 = PatientSurgeryData.getInstance().getSurname();
		System.out.println(s0);
		System.out.println("menu exited");
		
		
	}
	public void openPatientData(ActionEvent event) throws IOException {
		//borderPaneMenu.setCenter(FXMLLoader.load(Main.class.getResource("RecordDocument_PatientData.fxml"),  I18n.getResourceBundle()));
		changeCenter("RecordDocument_PatientData.fxml");
		iomDocumentationBtn.setDisable(true);
	}



	public void saveDocumentation(ActionEvent event) throws IOException {
		baselinesBtn.setDisable(true);
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("LogIn.fxml"),  I18n.getResourceBundle());
		Scene scene = new Scene(root);
		Stage window =  (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
	}

	public void openCreatQuery(ActionEvent event) throws IOException {

	}
	public void openExpandOntology(ActionEvent event) throws IOException {

	}
	public void openSettings(ActionEvent event) throws IOException {

	}

	public void setLabelText(String text){
		userName.setText(text);
	}
	

	/**
	 * 
	 * @param url
	 * @throws IOException
	 */
	private void changeCenter(String url) throws IOException {
		borderPaneMenu.setCenter(FXMLLoader.load(Main.class.getResource(url),  I18n.getResourceBundle()));
	}



}
