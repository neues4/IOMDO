package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * @author romap1
 *
 */
public class LogInController {

	
	public LogInController() {
		
	}
	@FXML
	private ResourceBundle resources;
	@FXML
	private Button logInBtn;
	@FXML
	private TextField passwordTF;
	@FXML
	private TextField usernameTF;
	@FXML
	private Label wrongUsernameLabel;

/**
 * Starts event after pressing the Log in button
 * @param event
 * @throws IOException
 */
	public void userLogIn(ActionEvent event) throws IOException{
		checkLogIn();
	}

/**
 * Checks the user name and password input.
 * @throws IOException
 */
	private void checkLogIn() throws IOException {
		Main main = new Main();
		wrongUsernameLabel.setText("Falsche Angabe");
		main.changeScene("Homescreen.fxml");
	}

	

	
}
