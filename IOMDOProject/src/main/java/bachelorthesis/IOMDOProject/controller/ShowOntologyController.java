package bachelorthesis.IOMDOProject.controller;

import java.awt.Desktop;
import java.io.IOException;
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
public class ShowOntologyController {

	@FXML
	private Button btnProtege;

	/**
	 * method to open the ontology in webprotege
	 * @param event
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@FXML
	void openProtege(ActionEvent event) throws IOException, URISyntaxException {		
		Desktop.getDesktop().browse(new URL("https://webprotege.stanford.edu/#projects/1b446c21-46aa-482a-9c54-36235b2e36c5/edit/Classes").toURI());
	}
}
