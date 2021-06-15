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

    @FXML
    void openUserManual(ActionEvent event) throws MalformedURLException, IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URL("https://drive.google.com/file/d/1tFdDVWYZRq8LIY9NCA-uJNqGNe0KmqmW/view?usp=sharing").toURI());
	
    }


}
