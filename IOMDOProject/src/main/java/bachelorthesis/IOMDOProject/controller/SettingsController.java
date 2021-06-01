package bachelorthesis.IOMDOProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class SettingsController {

	@FXML
	private RadioButton rbGerman;

	@FXML
	private RadioButton rbEnglish;
	
	private ToggleGroup tg = new ToggleGroup();
	
	public void initialize() {
		rbGerman.setToggleGroup(tg);
		rbEnglish.setToggleGroup(tg);
	}

	@FXML
	void setEnglish(ActionEvent event) {
		
	}

	@FXML
	void setGerman(ActionEvent event) {
		
	}

}
