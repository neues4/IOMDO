package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProtocolOverviewController {

	@FXML
	private GridPane gridPaneProtocolOverview;
	
	@FXML
	private TextField TBDtestTF;
	
	@FXML
	private Button searchBtn;
	
	
	public void test(ActionEvent event) throws IOException{
		System.out.println("test");
	}
}
