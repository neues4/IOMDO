package bachelorthesis.IOMDOProject.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import bachelorthesis.IOMDOProject.I18n;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author romap1
 *
 */
public class ProtocolOverviewController  {

	@FXML
	private GridPane gridPaneProtocolOverview;
	
	@FXML
	private TextField searchTF;
	
	
	
	@FXML
	private Button resetBtn;
	
	@FXML
	private TableView<Patient> protocolTblView;
	
	@FXML
	private TableColumn<Patient, String> surnameColumn;
	
	@FXML
	private TableColumn<Patient, String> firstnameColumn;
	
	@FXML
	private TableColumn<Patient, String> caseNrColumn;
	@FXML
	private TableColumn<Patient, String> dateOfBirthColumn;	
	@FXML
	private TableColumn<Patient, String> diagnosisColumn;
	@FXML
	private TableColumn<Patient, String> surgeryColumn;
	@FXML
	private TableColumn<Patient, String> dateOfSurgeryColumn;
	@FXML
	private TableColumn<Patient, String> FIDColumn;
	@FXML
	private TableColumn<Patient, String> PIDColumn;
	@FXML
	private TableColumn<Patient, String> surgeonColumn;
	@FXML
	private TableColumn<Patient, String> assistantColumn;
	
	
	private OntologyEditor oe = new OntologyEditor("src\\main\\resources\\bachelorthesis\\IOMDOProject\\IOMO_19.owl");
	
	private ObservableList<Patient> list = FXCollections.observableArrayList();
	
	
	 @FXML
	    public void initialize() {
		 
			surnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
			firstnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
			
			//date of birth funktioniert nicht!!!
			dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("birthday"));
			FIDColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("FID"));
			PIDColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("PID"));
			
			//protocolTblView.setPlaceholder(new Label("placeholder"));
			protocolTblView.setItems(list);
			
 
			list.add(loadPatient(oe, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262"));
			list.add(loadPatient(oe, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000218"));
			
			
			FilteredList<Patient> filteredData = new FilteredList<>(FXCollections.observableList(list));
			protocolTblView.setItems(filteredData);
		    searchTF.textProperty().addListener((observable, oldValue, newValue) ->
			filteredData.setPredicate(createPredicate(newValue))
		);
			
	 }
	

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void reset(ActionEvent event) throws IOException{
		
		//protocolTblView.setPlaceholder(new Label("Nothing found"));		
		searchTF.setText("");
        event.consume();
        //System.out.println(oe.getBirthday("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262").toString().substring(0, 10));
		
	}
	
	/**
	 * Loads all Properties from a Patient
	 * @param oe
	 * @param indvUri
	 * @return
	 */
	public Patient loadPatient(OntologyEditor oe, String indvUri) {
		return new Patient(oe.getSurname(indvUri).toString(), oe.getFirstName(indvUri).toString(), oe.getBirthday(indvUri).toString(), oe.getPID(indvUri).toString(), oe.getFID(indvUri).toString());
	}
	
	/**
	 * 
	 * @param patient
	 * @param searchText
	 * @return
	 */
	private boolean searchFindsPatient(Patient patient, String searchText) {
		return (patient.getSurname().toLowerCase().contains(searchText.toLowerCase())) ||
		            (patient.getFirstname().toLowerCase().contains(searchText.toLowerCase())) ||
		            patient.getbirthday().toLowerCase().contains(searchText.toLowerCase()) ||
		            patient.getFID().toLowerCase().contains(searchText.toLowerCase()) ||
		            patient.getPID().toLowerCase().contains(searchText.toLowerCase()) ;
		          //  Integer.valueOf(patient.getFID()).toString().equals(searchText.toLowerCase());
		}
	
	
	
	/**
	 * 
	 * @param searchText
	 * @return
	 */
	private Predicate<Patient> createPredicate(String searchText){
	    return patient -> {
	        if (searchText == null || searchText.isEmpty()) return true;
	        return searchFindsPatient(patient, searchText);
	    };
	}
		
	}
	



