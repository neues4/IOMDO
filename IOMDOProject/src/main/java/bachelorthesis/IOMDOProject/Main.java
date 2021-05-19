package bachelorthesis.IOMDOProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * JavaFX App
 */
public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		//Defaut language is german.
		I18n.setLocale(new Locale("de_CH"));
		//I18n.setLocale(new Locale("en"));
		Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"),I18n.getResourceBundle());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());
		stage.setTitle(I18n.getString("start.titel"));
		stage.setScene(scene);
		stage.show();
	}
		
	public static void main(String[] args) {
		launch();
	}

	
}