package bachelorthesis.IOMDOProject.controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.OntologyReader;
import bachelorthesis.IOMDOProject.model.IOMCase;
import bachelorthesis.IOMDOProject.model.Surgery;
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
	private TableView<IOMCase> protocolTblView;
	
	@FXML
	private TableColumn<IOMCase, String> surnameColumn;
	
	@FXML
	private TableColumn<IOMCase, String> firstnameColumn;
	@FXML
	private TableColumn<IOMCase, String> caseNrColumn;
	@FXML
	private TableColumn<IOMCase, String> dateOfBirthColumn;	
	@FXML
	private TableColumn<Surgery, String> diagnosisColumn;
	@FXML
	private TableColumn<Surgery, String> surgeryColumn;
	@FXML
	private TableColumn<Surgery, String> dateOfSurgeryColumn;
	@FXML
	private TableColumn<IOMCase, String> FIDColumn;
	@FXML
	private TableColumn<IOMCase, String> PIDColumn;
	@FXML
	private TableColumn<Surgery, String> surgeonColumn;
	@FXML
	private TableColumn<Surgery, String> assistantColumn;
	
	
	//private OntologyEditor oe = new OntologyEditor("src\\main\\resources\\bachelorthesis\\IOMDOProject\\IOMO_28.owl");
	//private OntologyEditor oe = new OntologyEditor("myModel.owl");
	OntologyReader oe = OntologyReader.getInstance();
	private ObservableList<IOMCase> list = FXCollections.observableArrayList();
	
	private String patientURI;
	
	
	 @FXML
	    public void initialize() throws ParseException {
		 
		 caseNrColumn.setCellValueFactory(new PropertyValueFactory<>("caseNr"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("surname"));
			firstnameColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("firstname"));
			dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("birthday"));
			//diagnosisColumn.setCellValueFactory(new PropertyValueFactory<Surgery, String>("diagnosis"));
			//surgeryColumn.setCellValueFactory(new PropertyValueFactory<Surgery, String>("surgery"));
			//dateOfSurgeryColumn.setCellValueFactory(new PropertyValueFactory<Surgery, String>("surgerydate"));
			//surgeonColumn.setCellValueFactory(new PropertyValueFactory<Surgery, String>("surgeon"));
			//assistantColumn.setCellValueFactory(new PropertyValueFactory<Surgery, String>("assistant"));
			//dateOfBirthColumn.setCellValueFactory(( new PropertyValueFactory<Patient, LocalDate>("birthday")).getValue().birthdayProperty());
			//dateOfBirthColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
			
		    
			
			FIDColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("FID"));
			PIDColumn.setCellValueFactory(new PropertyValueFactory<IOMCase, String>("PID"));
			
			
			//protocolTblView.setPlaceholder(new Label("placeholder"));
			protocolTblView.setItems(list);

			// Add all Patients to the List
			ArrayList<String> AL = new ArrayList<String>(oe.getAllPatientURIs());
			for (int i = 0; i < AL.size(); i++) {
				patientURI = AL.get(i);
				list.add(loadPatient(oe, patientURI));
			}
			
			//list.add(loadPatient(oe, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262"));
			//list.add(loadPatient(oe, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000276"));
			
			
			FilteredList<IOMCase> filteredData = new FilteredList<>(FXCollections.observableList(list));
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
	 * @throws ParseException 
	 */
	public IOMCase loadPatient(OntologyReader oe, String indvUri) throws ParseException {
		//System.out.println(oe.getBirthday(indvUri).toString().replace("^^" + XSDDatatype.XSDdate.getURI(), ""));
		//System.out.println(XSDDatatype.XSDdate.getURI());
		//System.out.println(XSDDatatype.XSDdate.unparse(oe.getBirthday(indvUri)));
		//System.out.println(XSDDatatype.XSDdate.parse("2002-09-24"));
		//System.out.println(XSDDatatype.XSDdate.trimPlus(XSDDatatype.XSDdate.getURI()));
		
		//.replace("^^" + XSDDatatype.XSDint.getURI(), "eger").toString()
		//^^http://www.w3.org/2001/XMLSchema#integer
		return new IOMCase(oe.getSurname(indvUri).toString(), oe.getFirstName(indvUri).toString(), 
				oe.getBirthdayValue(indvUri), oe.getPID(indvUri).toString(), oe.getFID(indvUri).toString(), 
				Integer.parseInt(oe.getCaseNumber(indvUri).toString().replace("^^" + XSDDatatype.XSDint.getURI(), "").toString()));
	}
	
	/**
	 * 
	 * @param iOMCase
	 * @param searchText
	 * @return
	 */
	private boolean searchFindsPatient(IOMCase iOMCase, String searchText) {
		return (iOMCase.getSurname().toLowerCase().contains(searchText.toLowerCase())) ||
		            (iOMCase.getFirstname().toLowerCase().contains(searchText.toLowerCase())) ||
		            iOMCase.getBirthday().toLowerCase().contains(searchText.toLowerCase()) ||
		            iOMCase.getFID().toLowerCase().contains(searchText.toLowerCase()) ||
		            iOMCase.getPID().toLowerCase().contains(searchText.toLowerCase()) ;
		          //  Integer.valueOf(patient.getFID()).toString().equals(searchText.toLowerCase());
		}
	
	/**
	 * 
	 * @param searchText
	 * @return
	 */
	private Predicate<IOMCase> createPredicate(String searchText){
	    return patient -> {
	        if (searchText == null || searchText.isEmpty()) return true;
	        return searchFindsPatient(patient, searchText);
	    };
	}
		
	}
	



