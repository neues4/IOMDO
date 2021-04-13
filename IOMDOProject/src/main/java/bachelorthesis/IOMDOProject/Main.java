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
	
	private static Stage staticStage;
	private static Scene scene;
	@Override
	public void start(Stage stage) throws IOException {
		//Defaut language is german.
		I18n.setLocale(new Locale("de_CH"));
		staticStage = stage;
		scene = new Scene(loadFXML("LogIn"));
		stage.setTitle(I18n.getString("start.titel"));
		stage.setScene(scene);
		stage.show();
	}
	
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}
	
	/**
	 * Changes scene on same window.
	 * @param fxml
	 * @throws IOException
	 */
	public void changeScene(String fxml) throws IOException {
		Parent window = FXMLLoader.load(getClass().getResource(fxml),I18n.getResourceBundle());
		staticStage.getScene().setRoot(window);
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"),I18n.getResourceBundle());
		return fxmlLoader.load();
	}
	public static void main(String[] args) {
		launch();
	}
}