package bachelorthesis.IOMDOProject.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 
 * @author neues4
 *
 */
public class SettingsController {

	@FXML
    private Button btnUserManual;

	/**
	 * Method to open the user manual on google drive
	 * @param event
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
    @FXML
    void openUserManual(ActionEvent event) throws MalformedURLException, IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URL("https://drive.google.com/file/d/1CF98Sj5RvkDCqt_3ylkTebegoBzIKjXm/view?usp=sharing").toURI());
	
    }


}
