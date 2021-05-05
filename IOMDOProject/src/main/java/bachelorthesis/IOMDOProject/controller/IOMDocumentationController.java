package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class IOMDocumentationController {
	
	
	
	@FXML
	private Button addRowBtn;

	@FXML
	private RowConstraints row2;
	
	@FXML
	private GridPane infoGrid;
	
	private static int ROW =2;
	

	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	
	public  void addRow(ActionEvent event) throws IOException {
		System.out.println("addRow");
	
		Button btn = new Button();
		
		btn.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Button btn2 = new Button();
					infoGrid.add(btn2, 1, ROW);
					ROW++;
				}
				});
		
		infoGrid.add(btn, 1, ROW);
		ROW++;

	
	}
	
}
