package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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
	private BorderPane borderPaneMenu;
	
	@FXML
	private GridPane gridPane;

	

	public void openNewProtocol(ActionEvent event) throws IOException {
		Main m = new Main();
		Parent p = m.returnParent("RecordDocument_PatientData.fxml");
		borderPaneMenu.setCenter(p);
		//gridPane.getChildren().removeAll();	
		//gridPane.getChildren().setAll(p);
	}

	public  void openProtocolOverview(ActionEvent event) throws IOException {
		Main m = new Main();
		Parent p = m.returnParent("ProtocolOverview.fxml");
		borderPaneMenu.setCenter(p);
		
		//borderPaneMenu.setCenter(p);
		//BorderPane bp = (BorderPane) this.borderPaneMenu;
		//bp.setCenter(p);
		
		//gridPane.getChildren().removeAll();	
		
		//ersetzt nur Children aus ProtocolOverviev
		//gridPane.getChildren().setAll(p.getChildrenUnmodifiable());
		
		//Ersetzt GridPane mit ProtocolOverview 
		//gridPane.getChildren().setAll(p);


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
