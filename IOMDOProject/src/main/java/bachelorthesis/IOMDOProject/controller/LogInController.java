package bachelorthesis.IOMDOProject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

	@FXML
	private Hyperlink newAccountHL;

	private File file;

	/**
	 * Starts event after pressing the Log in button
	 * @param event
	 * @throws IOException
	 */
	public void userLogIn(ActionEvent event) throws IOException{

		// Für Windows
		file = new File("src\\\\main\\\\resources\\\\bachelorthesis\\\\IOMDOProject\\\\loginInfo.txt");
		// Für Mac:
		//file = new File("/Users/stefanie/Documents/maven.1619428611109/IOMDOProject/src/main/resources/bachelorthesis/IOMDOProject/loginInfo.txt");
		Scanner sc= new Scanner(file);
		int lineNr = searchUsername(usernameTF.getText().toString());
		skipLines(sc, lineNr);
		if(usernameTF.getText().toString().equals(sc.next()) && passwordTF.getText().toString().equals(sc.next())) {
			//Parent parent = FXMLLoader.load(Main.class.getResource("Menu.fxml"),  I18n.getResourceBundle());
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Menu.fxml"));
			loader.setResources(resources);
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage window =  (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
			//sets Username from Menu View.
			MenuController controller = loader.getController();
			controller.setLabelText(usernameTF.getText());
			sc.close();

		} else {wrongUsernameLabel.setText(I18n.getString("errorMsg.wrongCredentials"));
		sc.close();}

	}

	public void newAccountScreen(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Main.class.getResource("NewAccount.fxml"),  I18n.getResourceBundle());
		Scene scene = new Scene(root);
		Stage window =  (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	private int searchUsername(String name) throws IOException {
		Scanner sc= new Scanner(file);

		//now read the file line by line...
		int lineNr = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			lineNr++;
			if(line.equals(name)) { 
				sc.close();
				return lineNr -1;
			}
		}
		sc.close();
		return 0;
	}	

	/**
	 * Skips the line in a text file.
	 * @param sc
	 * @param lineNr
	 */
	private static void skipLines(Scanner sc,int lineNr){
		for(int i = 0; i < lineNr;i++){
			if(sc.hasNextLine())sc.nextLine();
		}
	}
}



