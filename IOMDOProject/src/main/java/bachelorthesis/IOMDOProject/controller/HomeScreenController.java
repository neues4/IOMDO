package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author neues4
 *
 */

public class HomeScreenController  {

	@FXML
    private Button btnNewProtocol;

    @FXML
    private Button btnProtocolOverview;

    @FXML
    private Button btnCreateQuery;

    @FXML
    private Button btnExpandOntology;

    @FXML
    private Button BtnSettings;

    @FXML
    private Label lblLoggedInAs;

    @FXML
    private Label lblName;

    @FXML
    private Button btnLogout;
    
    
    // <aus MenuController
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
	
	private Main m;
	

	
	public HomeScreenController() {
		m = new Main();
	}
	
	// >
    
    public void goToCreateNewProtocol(ActionEvent event) throws IOException  {
    	//Main main = new Main();
    	m.changeScene("RecordDocument_PatientData.fxml");
    }
	
 
    public void openProtocolOverview(ActionEvent event) throws IOException {
    	m.changeScene("Menu.fxml");
    	
    
    	Parent p = m.returnParent("ProtocolOverview.fxml");
    	borderPaneMenu.setCenter(p);
	
    }

    
    
    
    //<
	public void openNewProtocol(ActionEvent event) throws IOException {
		Parent p = m.returnParent("RecordDocument_PatientData.fxml");
		borderPaneMenu.setCenter(p);
	
	}

	public  void openProtocolOverview2(ActionEvent event) throws IOException {
		Parent p = m.returnParent("ProtocolOverview.fxml");
		borderPaneMenu.setCenter(p);
		
	}
	
	
	
	
	public void openCreatQuery(ActionEvent event) throws IOException {

	}
	public void openExpandOntology(ActionEvent event) throws IOException {

	}
	public void openSettings(ActionEvent event) throws IOException {

	}

	
	//
    
    
    
}
