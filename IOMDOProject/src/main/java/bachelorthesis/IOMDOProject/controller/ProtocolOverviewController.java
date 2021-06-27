package bachelorthesis.IOMDOProject.controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import bachelorthesis.IOMDOProject.model.OntologyReader;
import bachelorthesis.IOMDOProject.model.PatientDataQuery;
import bachelorthesis.IOMDOProject.model.IOMCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * Shows the recorded Patients in a table. 
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
	private TableView<IOMCase> protocolTblView;
	@FXML
	private TableColumn<IOMCase, String> surnameColumn, firstnameColumn,caseNrColumn,dateOfBirthColumn,	diagnosisColumn,surgeryColumn, FIDColumn, PIDColumn;

	private OntologyReader oe = OntologyReader.getInstance();
	private ObservableList<IOMCase> list = FXCollections.observableArrayList();
	private String patientURI;

	/**
	 * Initializes the table with the needed Properties 
	 * @throws ParseException
	 * @throws IOException
	 */
	@FXML
	public void initialize() throws ParseException, IOException {
		caseNrColumn.setCellValueFactory(new PropertyValueFactory<>("caseNr"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("surname"));
		firstnameColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("firstname"));
		dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("birthday"));
		diagnosisColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("diagnosis"));
		surgeryColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("surgery"));
		FIDColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("FID"));
		PIDColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("PID"));

		protocolTblView.setItems(list);

		// Add all Patients to the List
		ArrayList<String> AL = new ArrayList<String>(oe.getAllPatientURIs());
		for (int i = 0; i < AL.size(); i++) {
			patientURI = AL.get(i);
			list.add(loadPatient(oe, patientURI));
		}
		//dynamycaly changes the content display of the table according to whats entered into the searchfield
		FilteredList<IOMCase> filteredData = new FilteredList<>(FXCollections.observableList(list));
		protocolTblView.setItems(filteredData);
		searchTF.textProperty().addListener((observable, oldValue, newValue) ->
		filteredData.setPredicate(createPredicate(newValue)));	
	}


	/**
	 * Resets the searchField that searches in the table.
	 * @param event: an actionEvent
	 * @throws IOException
	 */
	public void reset(ActionEvent event) throws IOException{	
		searchTF.setText("");
		event.consume();
	}

	/**
	 * Returns a Class with information about the patient and the surgery
	 * Loads all Properties from a Patient
	 * @param oe: OntologyReader 
	 * @param indvUri: Uri of a Patient
	 * @return a Class with information about the patient and the surgery
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public IOMCase loadPatient(OntologyReader oe, String indvUri) throws ParseException, IOException {
		//Queries an array with the patient [0], his diagnosis [1] and his surgery[2]
		ArrayList<String> list = PatientDataQuery.sparqlTest(indvUri);
		String diagnosis = "";
		String surgery = "";
		if (!list.isEmpty()) {
			diagnosis = list.get(1);
			surgery = list.get(2);
		}

		String surname = oe.getSurname(indvUri).toString();
		String firstname = oe.getFirstName(indvUri).toString();
		String birthday = oe.getBirthdayValue(indvUri);
		String PID = oe.getPID(indvUri).toString();
		String FID = oe.getFID(indvUri).toString();
		//removes datatype Uri of XDSint to use it as int
		int caseNumber = Integer.parseInt(oe.getCaseNumber(indvUri).toString().replace("^^" + XSDDatatype.XSDint.getURI(), "").toString());
		//creating a new IOM case with patient and surgery properties
		IOMCase iomCase = new IOMCase(surname, firstname, birthday,PID, FID, caseNumber, diagnosis, surgery);
		return iomCase;
	}

	/**
	 * private method that searches if a property of the IOM case contains the searched string
	 * @param iOMCase: the IOM case where the search happens
	 * @param searchText: the string that is searched
	 * @return true or false
	 */
	private boolean filterCaseData(IOMCase iOMCase, String searchText) {
		return (iOMCase.getSurname().toLowerCase().contains(searchText.toLowerCase())) ||
				(iOMCase.getFirstname().toLowerCase().contains(searchText.toLowerCase())) ||
				iOMCase.getBirthday().toLowerCase().contains(searchText.toLowerCase()) ||
				iOMCase.getFID().toLowerCase().contains(searchText.toLowerCase()) ||
				iOMCase.getPID().toLowerCase().contains(searchText.toLowerCase()) ||
				iOMCase.getSurgery().toLowerCase().contains(searchText.toLowerCase())
				|| iOMCase.getDiagnosis().toLowerCase().contains(searchText.toLowerCase());
	}

	/**
	 * private method that creates a predicate for the seached text
	 * @param searchText
	 * @return a Predicate
	 */
	private Predicate<IOMCase> createPredicate(String searchText){
		return patient -> {
			if (searchText == null || searchText.isEmpty()) return true;
			return filterCaseData(patient, searchText);
		};
	}

}




