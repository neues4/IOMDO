package bachelorthesis.IOMDOProject.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ExpandOntologyController {

	@FXML
	private Button btnProtege;

	@FXML
	void openProtege(ActionEvent event) throws IOException {

		// mac
		File file = new File("/Users/stefanie/Desktop/exceptionTest.owl");
		// windows
		//File file = new File("HIER BITTE PATH ZU ONTOLOGIE FILE AUF DESKTOP EINFÜGEN")
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().open(file);

		}
	}
}
