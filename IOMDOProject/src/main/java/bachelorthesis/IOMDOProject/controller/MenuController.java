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
	private Label userName;


	@FXML public BorderPane borderPaneMenu;

	@FXML
	private GridPane gridPane;

	public void initialize() {
		
	}


	public void openNewProtocol(ActionEvent event) throws IOException {
		//changeCenter("RecordDocument_PatientData.fxml");
		changeCenter("IOMRecording.fxml");
		
	}

	public  void openProtocolOverview(ActionEvent event) throws IOException {
		changeCenter("ProtocolOverview.fxml");
		
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
