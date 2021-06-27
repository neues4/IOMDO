package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Registers new Users and saves the information to a file
 * @author romap1
 *
 */
public class NewAccountController {

	@FXML
	private Button cancelBtn;

	@FXML
	private Label errorTextLbl;

	@FXML
	private Button signInBtn;

	@FXML
	private TextField usernameTF;

	@FXML
	private PasswordField passwordTF;

	private File file;


	public NewAccountController(){
		file = new File("src\\main\\resources\\bachelorthesis\\IOMDOProject\\loginInfo.txt");
	}

	/**
	 * Returns to the logIn Screen
	 * @param event: Action event
	 * @throws IOException
	 */
	public void cancel(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(Main.class.getResource("LogIn.fxml"),  I18n.getResourceBundle());
		Scene scene = new Scene(root);
		Stage window =  (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	/**
	 * Creates a new account if user name is new.  
	 * @param event: Action event
	 * @throws IOException
	 */
	public void signIn(ActionEvent event) throws IOException{
		//Checks if input data is not empty 
		if(usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty()) {
			errorTextLbl.setText(I18n.getString("errorMsg.emptyFileds"));
		}
		//Checks if user name already exists
		else if(hasUsername(usernameTF.getText().toString())) {
			errorTextLbl.setText(I18n.getString("errorMsg.usernameExists"));
			//saves user input into file and confirms that the registration was successful
		}else{
			errorTextLbl.setText("");
			saveFile();
			cancel(event);	
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(I18n.getString("confirmationMsg.succsess"));
			alert.setContentText(I18n.getString("confirmationMsg.userCreated"));
			alert.show();
		}
	}

	/**
	 * Saves user name and passwort into a file
	 * @throws IOException
	 */
	private void saveFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("\n" + "\n" + usernameTF.getText().toString()+ "\n" );
		sb.append(passwordTF.getText().toString() );
		FileWriter fw = new FileWriter(file, true);
		fw.write(sb.toString());
		fw.close();

	}

	/**
	 * Returns true if user name already exists in the file.
	 * @param name
	 * @return true if user name exists, false otherwise
	 * @throws IOException
	 */
	private boolean hasUsername(String name) throws IOException {
		Scanner sc= new Scanner(file);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.equals(name)) { 
				sc.close();
				return true;
			}
		}
		sc.close();
		return false;
	}

}
