package bachelorthesis.IOMDOProject.controller;
import java.io.IOException;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import bachelorthesis.IOMDOProject.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ChoiceBox<String> columnSelectChoiceBox;
	
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
			columnSelectChoiceBox.setItems(FXCollections.observableArrayList("casenr", "firstname", "surname", "birthday", "diagnosis", "surgeryType", "surgeryDate", "FID", "PID", "surgeon", "assistant" ));
			columnSelectChoiceBox.setTooltip(new Tooltip("fillertext"));
	 }
	
	OntologyEditor oe = new OntologyEditor("src\\main\\resources\\bachelorthesis\\IOMDOProject\\IOMO_19.owl");
	public ObservableList<Patient> list = FXCollections.observableArrayList();
	
	
	public void test(ActionEvent event) throws IOException{
		//oe.getPropertyOfIndividual("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262", "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_253");
		//protocolTblView.setPlaceholder(new Label("placeholder"));		
		list.add(loadPatient(oe, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262"));
		
	}
	
	/**
	 * Loads all Properties from a Patient
	 * @param oe
	 * @param indvUri
	 * @return
	 */
	public Patient loadPatient(OntologyEditor oe, String indvUri) {
		return new Patient(oe.getSurname(indvUri).toString(), oe.getFirstName(indvUri).toString(), oe.getBirthday(indvUri).toString() , oe.getPID(indvUri).toString(), oe.getFID(indvUri).toString());
	}
	

}

