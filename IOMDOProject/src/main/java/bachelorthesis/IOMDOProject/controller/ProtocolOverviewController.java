package bachelorthesis.IOMDOProject.controller;

import java.io.IOException;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


public class ProtocolOverviewController  {

	@FXML
	private GridPane gridPaneProtocolOverview;
	
	@FXML
	private TextField TBDtestTF;
	
	@FXML
	private Button searchBtn;
	
	
	@FXML
	private TableView<Patient> protocolTblView;
	
	@FXML
	private TableColumn<Patient, String> surnameTblColumn;
	
	@FXML
	private TableColumn<Patient, String> firstnameTblColumn;
	
	 @FXML
	    public void initialize() {
		 
			surnameTblColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
			firstnameTblColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
			//protocolTblView.setPlaceholder(new Label("placeholder"));
			protocolTblView.setItems(list);
	 }
	
	OntologyEditor oe = new OntologyEditor("C:\\Users\\patty\\Desktop\\IOMO_19.owl");

	
	public ObservableList<Patient> list = FXCollections.observableArrayList(
			new Patient("Tidus", "Test"),
			new Patient("Yuna", "West"));
	
	
	public void test(ActionEvent event) throws IOException{
		oe.getPropertyOfIndividual("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262", "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_253");
		//surnameTblColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
		//firstnameTblColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
		//protocolTblView.setPlaceholder(new Label("placeholder"));
		//protocolTblView.setItems(list);
		//System.out.println(oe.getOntologyClass("http://medicis/spm.owl/OntoSPM#patient"));
		//System.out.println(oe.getAllIndividuals());
		
		
	}
	

}

