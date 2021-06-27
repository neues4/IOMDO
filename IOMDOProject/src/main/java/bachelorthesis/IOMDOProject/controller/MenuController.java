package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.effect.Glow;
import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Navigates through the different views
 * @author romap1
 *
 */
public class MenuController {

	@FXML
	private Button newProtocolBtn, protocolOverviewBtn,createQueryBtn,showOntologyBtn,settingsBtn,logOutBtn; 
	@FXML
	private Label userName, labelEn, labelDe;
	@FXML
	public BorderPane borderPaneMenu;
	@FXML
	private GridPane gridPane;

/**
 * Changes the center of the borderPane of the the Menu View to IOMRecording View
 * @param event: an Action event
 * @throws IOException
 */
	public void openNewProtocol(ActionEvent event) throws IOException {
		changeCenter("IOMRecording.fxml");
		//selected stays highlighted for better orientation
		newProtocolBtn.setStyle("-fx-background-color: #629c9e");
		protocolOverviewBtn.setStyle("-fx-background-color: white");
		createQueryBtn.setStyle("-fx-background-color: white");
		showOntologyBtn.setStyle("-fx-background-color: white");
		settingsBtn.setStyle("-fx-background-color: white");
		
	}

	/**
	 * Changes the center of the borderPane of the the Menu View to ProtocolOverview View
	 * @param event: an Action event
	 * @throws IOException
	 */
	public  void openProtocolOverview(ActionEvent event) throws IOException {
		changeCenter("ProtocolOverview.fxml");
		//selected stays highlighted for better orientation
		newProtocolBtn.setStyle("-fx-background-color: white");
		protocolOverviewBtn.setStyle("-fx-background-color: #629c9e");
		createQueryBtn.setStyle("-fx-background-color: white");
		showOntologyBtn.setStyle("-fx-background-color: white");
		settingsBtn.setStyle("-fx-background-color: white");	
	}

	/**
	 * Changes the center of the borderPane of the the Menu View to CreateQuery View
	 * @param event: an Action event
	 * @throws IOException
	 */
	public void openCreatQuery(ActionEvent event) throws IOException {
		changeCenter("Query.fxml");
		//selected stays highlighted for better orientation
		newProtocolBtn.setStyle("-fx-background-color: white");
		protocolOverviewBtn.setStyle("-fx-background-color: white");
		createQueryBtn.setStyle("-fx-background-color: #629c9e");
		showOntologyBtn.setStyle("-fx-background-color: white");
		settingsBtn.setStyle("-fx-background-color: white");
	}
	
	/**
	 * Changes the center of the borderPane of the the Menu View to ShowOntology View
	 * @param event: an Action event
	 * @throws IOException
	 */
	public void openShowOntology(ActionEvent event) throws IOException {
		changeCenter("ShowOntology.fxml");
		//selected stays highlighted for better orientation
		newProtocolBtn.setStyle("-fx-background-color: white");
		protocolOverviewBtn.setStyle("-fx-background-color: white");
		createQueryBtn.setStyle("-fx-background-color: white");
		showOntologyBtn.setStyle("-fx-background-color: #629c9e");
		settingsBtn.setStyle("-fx-background-color: white");
	}
/**
 * Changes the center of the borderPane of the the Menu View to Settings View 
 * @param event: an Action event
 * @throws IOException
 */
	public void openSettings(ActionEvent event) throws IOException {
		changeCenter("Settings.fxml");
		//selected stays highlighted for better orientation
		newProtocolBtn.setStyle("-fx-background-color: white");
		protocolOverviewBtn.setStyle("-fx-background-color: white");
		createQueryBtn.setStyle("-fx-background-color: white");
		showOntologyBtn.setStyle("-fx-background-color: white");
		settingsBtn.setStyle("-fx-background-color: #629c9e");
	}
	
	/**
	 * Changes to the Login View
	 * @param event: an Action event
	 * @throws IOException
	 */
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Main.class.getResource("LogIn.fxml"),  I18n.getResourceBundle());
		Scene scene = new Scene(root);
		Stage window =  (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();	
	}

	/**
	 * Set the user name on the top right of the screen
	 * @param text: String of the user name
	 */
	public void setLabelText(String text){
		userName.setText(text);
	}

	/**
	 * Changes the center of the BorderPane of the Menu View
	 * @param url
	 * @throws IOException
	 */
	private void changeCenter(String url) throws IOException {
		borderPaneMenu.setCenter(FXMLLoader.load(Main.class.getResource(url),  I18n.getResourceBundle()));
	}
	
	/**
	 * Sets the language in German
	 * @param event
	 */
	@FXML
    void setLanguageDe(MouseEvent event) {
		I18n.setLocale(new Locale("de_CH"));
	
    }

	/**
	 * Sets the language in English
	 * @param event
	 */
    @FXML
    void setLanguageEn(MouseEvent event) {
    	I18n.setLocale(new Locale("en"));
   
    }

}
