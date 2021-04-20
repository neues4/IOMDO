package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Registers new Users and saves the Information to a file
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
		file = new File("src\\\\main\\\\resources\\\\bachelorthesis\\\\IOMDOProject\\\\loginInfo.txt");
	}


	public void cancel(ActionEvent event) throws IOException{

		Main main = new Main();
		main.changeScene("LogIn.fxml");
	}

	public void signIn(ActionEvent event) throws IOException{
		if(usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty()) {
			errorTextLbl.setText(I18n.getString("errorMsg.emptyFileds"));
		}
		else if(hasUsername(usernameTF.getText().toString())) {
			errorTextLbl.setText(I18n.getString("errorMsg.usernameExists"));
		}else{
			errorTextLbl.setText("");
			saveFile();
			cancel(event);	
			UserConfirmation();

		}
	}



	/**
	 * 	
	 */
	private void UserConfirmation() {
		//Creating a dialog
		Stage dialogStage = new Stage();
		dialogStage.setMinWidth(500);
		dialogStage.setMinHeight(200);
		Button okButton = new Button( "OK" );
		VBox vbox = new VBox(new Text(I18n.getString("confirmationMsg.userCreated")), okButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15));
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
		okButton.setOnAction( new EventHandler<ActionEvent>() {
			@Override public void handle( ActionEvent e ) {
				dialogStage.close();
			}
		} );
	}

	/**
	 * 
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
	 * Returns True if Username is in the file.
	 * @param name
	 * @return
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
