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

	@FXML
	void openProtege(ActionEvent event) throws IOException, URISyntaxException {		
		Desktop.getDesktop().browse(new URL("https://webprotege.stanford.edu/#projects/4ff1be76-81df-425e-b567-99996c664c9f/edit/Classes?selection=Class(%3Chttp://purl.obolibrary.org/obo/BFO_0000002%3E)").toURI());
	}
}
