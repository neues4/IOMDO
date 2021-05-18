package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


/**
 * 
 * @author romap1
 *
 */
public class MenuController implements Initializable {


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
	private Label userDisplay;
	

	@FXML
	private BorderPane borderPaneMenu;

	@FXML
	private GridPane gridPane;

	private Main m;



	public MenuController() {
		m = new Main();
	}

	//übergangslösung für verknüpfung zuIOMDocumentation.fxml
	public  void openIOMDocumentation(ActionEvent event) throws IOException {
		Parent p = m.returnParent("IOMDocumentation.fxml");
		borderPaneMenu.setCenter(p);
		baselinesBtn.setDisable(false);
		patientDataBtn.setDisable(true);
		iomDocumentationBtn.setDisable(false);
		saveBtn.setDisable(false);
		
	}

	public void openNewProtocol(ActionEvent event) throws IOException {
		//Main m = new Main();
		Parent p = m.returnParent("RecordDocument_PatientData.fxml");
		borderPaneMenu.setCenter(p);
		//gridPane.getChildren().removeAll();	
		//gridPane.getChildren().setAll(p);
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
		//Main m = new Main();
		Parent p = m.returnParent("ProtocolOverview.fxml");
		borderPaneMenu.setCenter(p);
		baselinesBtn.setVisible(false);
		patientDataBtn.setVisible(false);
		iomDocumentationBtn.setVisible(false);
		saveBtn.setVisible(false);

		//borderPaneMenu.setCenter(p);
		//BorderPane bp = (BorderPane) this.borderPaneMenu;
		//bp.setCenter(p);

		//gridPane.getChildren().removeAll();	

		//ersetzt nur Children aus ProtocolOverviev
		//gridPane.getChildren().setAll(p.getChildrenUnmodifiable());

		//Ersetzt GridPane mit ProtocolOverview 
		//gridPane.getChildren().setAll(p);


	}
	// ÜBERGANGSLÖSUNG
	public void openBaselines(ActionEvent event) throws IOException {
		Parent p = m.returnParent("Baselines.fxml");
		borderPaneMenu.setCenter(p);
		patientDataBtn.setDisable(false);
		iomDocumentationBtn.setDisable(false);
		saveBtn.setDisable(true);
		
	}
	
	public void openPatientData(ActionEvent event) throws IOException {
		Parent p = m.returnParent("RecordDocument_PatientData.fxml");
		borderPaneMenu.setCenter(p);
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
		m.changeScene("Login.fxml");
	}

	public void openCreatQuery(ActionEvent event) throws IOException {

	}
	public void openExpandOntology(ActionEvent event) throws IOException {

	}
	public void openSettings(ActionEvent event) throws IOException {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	
}
