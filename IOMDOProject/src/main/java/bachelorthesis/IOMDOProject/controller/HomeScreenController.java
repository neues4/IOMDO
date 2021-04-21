package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 
 * @author neues4
 *
 */

public class HomeScreenController {

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
    
    
    public void goToCreateNewProtocol(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("RecordDocument_PatientData.fxml");
    }
	
    public void openProtocolOverview(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Menu.fxml");
		//Parent p = main.returnParent("ProtocolOverview.fxml");
		
	
    }
    
}
