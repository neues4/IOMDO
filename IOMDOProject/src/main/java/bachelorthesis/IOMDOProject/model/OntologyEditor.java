package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.iterator.ExtendedIterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * 
 * @author neues4
 * 
 */
public class OntologyEditor {

	//Namesspace
	private final static String NS = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/";


	// a new instance of an ontology model // romap1: habe static rausgenommen, weil ich vorteil nicht gesehen habe
	private  OntModel ontModel;

	private static OntologyEditor editor;

	private int counter = 500;

	private String id = "IOMO_0000000";

	//Object Properties IDs
	private String has_timestamp = "IOMO_0000266";
	private String has_document = "IOMO_0000285";
	private String has_measurement_unit = "IOMO_0000275";
	private String has_data_item = "IOMO_0000282";
	
	private String documents_process = "IOMO_0000283";
	//is_documented_in ODER documented_in löschen!!!!
	private String is_documented_in = "IOMO_0000284";
	private String documented_in ="IOMO_0000236";
	
	private String data_item_of = "IOMO_0000367";
	private String document_of = "IOMO_0000368";
	private String measurement_unit_of = "IOMO_0000386";
	private String is_answer_about = "IOMO_0000468";
	private String gives_answer_in = "IOMO_0000469";
	private String concretized_by_at_some_time = "http://purl.obolibrary.org/obo/BFO_0000058";
	private String concretizes_at_some_time ="http://purl.obolibrary.org/obo/BFO_0000059";

	


	//Data Properties IDs
	private String has_surgeon = "IOMO_0000363";
	private String has_assistant = "IOMO_0000364";
	private String has_date_of_surgery = "IOMO_0000365";
	private String has_device = "IOMO_0000366";
	private String has_comment = "IOMO_0000459";

	public static OntologyEditor getInstance()
	{
		if (editor == null)
			//Windows
			editor = new OntologyEditor("src\\\\main\\\\resources\\\\bachelorthesis\\\\IOMDOProject\\\\IOMO_34.owl");
		//mac
		//editor = new OntologyEditor("/Users/stefanie/Documents/maven.1619428611109/IOMDOProject/src/main/resources/bachelorthesis/IOMDOProject/IOMO_32.owl");

		return editor;
	}

	@SuppressWarnings("exports")
	public Model getModel() {
		Model model = OntologyEditor.getInstance().ontModel;
		return model;
	}


	/**
	 * Constructor to create a new Ontology Editor.
	 * @param filePath the local path to the Ontology File (.owl)
	 */
	public OntologyEditor(String filePath) {

		ontModel = ModelFactory.createOntologyModel();
		try {
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			ontModel.read(reader, null);
			//ontModel.write(System.out, "RDF/XML-ABBREV");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getId() {
		String  counterAsString = Integer.toString(counter);
		int sizeOfInt = counterAsString.length();
		switch (sizeOfInt) {

		case 3:
			id = "IOMO_0000" + counter;
			System.out.println(id);
			break;
		case 4:
			id = "IOMO_000" + counter;
			System.out.println(id);
			break;
		case 5:
			id = "IOMO_00" + counter;		
			break;
		case 6:
			id = "IOMO_0" + counter;
			break;
		case 7:
			id = "IOMO_" + counter;
			break;
		}
		counter ++;
		return id;
	}




	public ArrayList<OntClass> getAllOntologyClasses() {
		Iterator classIter = ontModel.listClasses();
		ArrayList<OntClass> list= new ArrayList<OntClass>();
		while (classIter.hasNext()) {
			OntClass ontClass = (OntClass) classIter.next();
			//String uri = ontClass.getURI();

			if (ontClass.getURI() != null)
				list.add(ontClass);
		}
		return list;
	}



	/**
	 * Method to put all the Subclasses of the Class "Diagnosis" into a Hash Map
	 * Label as key and URL as value
	 * @author neues4
	 * @return the HashMap with all the Labels and URLs of the Subclasses of Diagnosis
	 */
	public Map<String, String> getAllDiagnosis() {
		OntClass diagnosis = ontModel.getOntClass("http://purl.obolibrary.org/obo/OGMS_0000073");
		HashMap<String, String> diagMap = new HashMap<>();
		Iterator diagnosisIter = diagnosis.listSubClasses();
		while (diagnosisIter.hasNext()) {
			OntClass sub = (OntClass) diagnosisIter.next();
			diagMap.put(sub.getLabel("DE"), sub.getURI());
		}
		return diagMap;
	}


	/**
	 * Method to put all the Subclasses of the Class "Surgery" into a Hash Map
	 * Label as key and URL as value
	 * @author neues4
	 * @return the HashMap with all the Labels and URLs of the Subclasses of Surgery
	 */
	public Map<String, String> getAllSurgeries() {
		OntClass surgery = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000333");
		HashMap<String, String> surgMap = new HashMap<>();
		Iterator surgeryIter = surgery.listSubClasses();
		while (surgeryIter.hasNext()) {
			OntClass sub = (OntClass) surgeryIter.next();
			surgMap.put(sub.getLabel("DE"), sub.getURI());
		}
		return surgMap;
	}

	/**
	 * Method to return the URI of the loaded ontology
	 * @author neues4
	 * @return the URI of the loaded ontology
	 */
	public String getOntologyURL() {
		String ontologyURI = null;
		Iterator iter = ontModel.listOntologies();

		if(iter.hasNext()) {
			Ontology onto = (Ontology) iter.next();
			ontologyURI = onto.getURI();
			return "Ontology URI = "+ ontologyURI;
		}
		return null; 

	}

	/**
	 * Method to create a new patient
	 * @author neues4
	 * @param indvLabel the label for the new patient instance (e.g. Patient1) 
	 * @return the URI of the newly created individual
	 */
	public String createNewPatient(String indvLabel) {
		OntClass ontClass = ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#patient");
		Individual indv = ontClass.createIndividual(NS + getId());
		//Individual indv = ontClass.createIndividual(createNewURI(indvLabel));
		indv.addLabel(indvLabel, "DE");
		return indv.getURI();
	}


	/**
	 * Method to create a new individual
	 * @author neues4
	 * @param classURI the URI of the class for which we want to create a new individual
	 * @param indvLabel the label for the new individual (e.g. dcsMepMeasurement 1"
	 * @return the URI of the newly created individual
	 */
	public String createNewIndividual(String classURI, String indvLabel) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		Individual indv = ontClass.createIndividual(NS + getId());
		//Individual indv = ontClass.createIndividual(createNewURI(indvLabel));
		indv.addLabel(indvLabel, "DE");

		return indv.getURI();
	}


	//Deprecated? romap1
	/**
	 * Method to create a new URI with "#", used to create new individuals in the ontology
	 * (this method is only used in OntologyEditor Class in every method where we want to create a new individual)
	 * @author neues4
	 * @param label the label of the individual for which we want to create a new URI
	 * @return the new URI
	 */
	public String createNewURI(String label) {
		String iomoURI = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO#";
		String newURI = iomoURI.concat(label);
		return newURI;
	}


	/**
	 * method to add the dataproperties to the patient
	 * @author neues4
	 * @param pat the uri of the patient individual
	 * @param nr the case number to add to the patient
	 * @param PID the PID to add to the patient
	 * @param FID the FID to add to the patient
	 * @param firstname the firstname to add to the patient
	 * @param surname the surname to add to the patient
	 * @param birthday the birthday to add to the patient
	 */
	public void addPatientProperties(String pat, String nr, String PID, String FID, String firstname, String surname, String birthday) {
		Individual indv = ontModel.getIndividual(pat);
		DatatypeProperty datPropNr = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000250");
		DatatypeProperty datPropPid = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000251");
		DatatypeProperty datPropFid = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000252");
		DatatypeProperty datPropFirstName = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000253");
		DatatypeProperty datPropSurname = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000254");
		DatatypeProperty datPropBirthday = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255");
		Literal nrInt = ontModel.createTypedLiteral(new Integer(nr));
		/*
		Literal pidString = ontModel.createTypedLiteral(new String(PID));
		Literal fidString = ontModel.createTypedLiteral(new String(FID));
		Literal firstnameString = ontModel.createTypedLiteral(new String(firstname));
		Literal surnameString = ontModel.createTypedLiteral(new String(surname));
		Literal dataBirthday = ontModel.createTypedLiteral(new String(birthday));
		 */
		indv.addProperty(datPropNr, nrInt);
		indv.addProperty(datPropPid, PID);
		indv.addProperty(datPropFid, FID);
		indv.addProperty(datPropFirstName, firstname);
		indv.addProperty(datPropSurname, surname);
		indv.addProperty(datPropBirthday, birthday);
	}
	/**
	 * method to add the dataproperties to the surgery
	 * @author neues4
	 * @param surgery the label of the surgery individual
	 * @param surgeryDate the date of the surgery
	 * @param surgeon the name of the surgeon
	 * @param assistant the name of the assistant
	 * @param isisDevice the name of the device
	 */
	public void addSurgeryProperties(String surgery, String surgeryDate, String surgeon, String assistant, String isisDevice) {
		Individual indv = ontModel.getIndividual(surgery);
		DatatypeProperty datPropSurgeryDate = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000365");
		DatatypeProperty datPropSurgeon = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000363");
		DatatypeProperty datPropAssistant = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000364");
		DatatypeProperty datPropIsisDevice = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000366");
		indv.addProperty(datPropSurgeryDate, surgeryDate);
		indv.addProperty(datPropSurgeon, surgeon);
		indv.addProperty(datPropAssistant, assistant);
		indv.addProperty(datPropIsisDevice, isisDevice);
	}

	/**
	 * ToDelete
	 * Adds the data property timestamp to the given entity and saves it into the ontology.
	 * @author romap1
	 * @param entity The URI of the entity
	 * @param timestamp the time as String
	 */
	public void addTimestampToEntity(String entityUri, String label, String timestamp) {
		String indivUri= createNewIndividual(entityUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		//String has_timestamp = "IOMO_0000266";
		DatatypeProperty datPropSurgeryDate = ontModel.getDatatypeProperty( NS + has_timestamp);
		indv.addProperty(datPropSurgeryDate, timestamp);
		System.out.println(indivUri);

	} 

	/**
	 * ToDElete
	 * @author romap1
	 * @param entityUri
	 * @param label
	 * @param comment
	 */
	public void addCommentToEntity(String entityUri, String label, String comment) {
		String indivUri= createNewIndividual(entityUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		DatatypeProperty datPropComment = ontModel.getDatatypeProperty( NS + has_comment);
		indv.addProperty(datPropComment, comment);
		System.out.println(indivUri);
		saveNewOWLFile();
	} 

	/**
	 * Adds a Finding to Document and saves it into the Ontology.
	 * @author romap1
	 * @param entityUri
	 * @param label
	 * @param timestamp
	 * @param comment
	 * @param doccumentUri
	 */
	public void addFindings(String entityUri, String label,String timestamp, String comment, String doccumentUri) {
		String indivUri= createNewIndividual(entityUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		DatatypeProperty datPropTimeStamp = ontModel.getDatatypeProperty( NS + has_timestamp);
		indv.addProperty(datPropTimeStamp, timestamp);
		//Only writtes comment if something is written
		if(!comment.equals("")){
			DatatypeProperty datPropComment = ontModel.getDatatypeProperty( NS + has_comment);
			indv.addProperty(datPropComment, comment);
		}
		addStatement(doccumentUri, NS + has_data_item , indivUri);
		addStatement(indivUri, NS + data_item_of , doccumentUri);

		saveNewOWLFile();
	}

	/**
	 * @author romap1
	 * @param entityUri
	 * @param label
	 * @param timestamp
	 * @param comment
	 * @param doccumentUri
	 * @param MeasurementValue
	 */
	public void addMeasurement(String entityUri, String label,String timestamp, String comment, String doccumentUri, String MeasurementValue) {
		String indivUri= createNewIndividual(entityUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		DatatypeProperty datPropTimeStamp = ontModel.getDatatypeProperty( NS + has_timestamp);
		indv.addProperty(datPropTimeStamp, timestamp);

		//Only writtes comment if something is written
		if(!comment.equals("")){
			DatatypeProperty datPropComment = ontModel.getDatatypeProperty( NS + has_comment);
			indv.addProperty(datPropComment, comment);
		}

		addStatement(doccumentUri, NS + has_data_item , indivUri);

		String maUri = createNewMiliampere("mA");
		addPropertiesToMiliampere(maUri, MeasurementValue );

		addStatement(indivUri, NS + has_measurement_unit, maUri);
		addStatement(maUri, NS + measurement_unit_of, indivUri);

		saveNewOWLFile();
	}

	/**
	 * FOR DCS MEP and TES MEP Measurement
	 * @author romap1
	 * @param entityUri
	 * @param label
	 * @param timestamp
	 * @param comment
	 * @param doccumentUri
	 * @param measurementValue
	 * @param muscleUri
	 * @param muscleLabel
	 */
	public void addMeasurement(String entityUri, String label,String timestamp, String comment, 
			String doccumentUri, List<String> measurementValue, List<String> muscleUri, List<String> muscleLabel) {
		String indivUri= createNewIndividual(entityUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		DatatypeProperty datPropTimeStamp = ontModel.getDatatypeProperty( NS + has_timestamp);
		indv.addProperty(datPropTimeStamp, timestamp);

		//Only writtes comment if something is written
		if(!comment.equals("")){
			DatatypeProperty datPropComment = ontModel.getDatatypeProperty( NS + has_comment);
			indv.addProperty(datPropComment, comment);
		}

		addStatement(doccumentUri, NS + has_data_item , indivUri);


		Iterator<String> valueIter = measurementValue.iterator();
		Iterator<String> muscleIter = muscleLabel.iterator();
		Iterator<String> muscleUriIter = muscleUri.iterator();

		//add values und muscles
		while (muscleIter.hasNext()) {
			String maUri = createNewMiliampere("mA");
			addPropertiesToMiliampere(maUri, valueIter.next());
			addStatement(indivUri, NS + has_measurement_unit, maUri);
			addStatement(maUri, NS + measurement_unit_of, indivUri);
			String muscle= createNewIndividual(muscleUriIter.next(), muscleIter.next());
			addStatement(maUri, NS + is_answer_about , muscle);	
			addStatement(muscle, NS + gives_answer_in , maUri);
		}	
		saveNewOWLFile();
	}

	//im aufbau
	public void addPatient(String caseNumber, String pid, String fid, String firstname, String surname, String birthday, String documentUri) {

		String patientUri = createNewPatient("patient"); 
		addPatientProperties(patientUri, caseNumber, pid , fid, firstname, surname, birthday);
		addStatement(patientUri, NS + has_document, documentUri);
		addStatement(documentUri, NS + document_of, patientUri);
		saveNewOWLFile(); 
	}

	public void addSurgery(String surgeryUri,String label,  String dateOfSurgery, String surgeon, String assistant, String device, String documentUri) {
		String indivUri= createNewIndividual(surgeryUri, label);
		Individual indv = ontModel.getIndividual(indivUri);
		//addSurgeryProperties(surgery, dateOfSurgery, surgeon, assistant, device);
		DatatypeProperty datPropSurgeryDate = ontModel.getDatatypeProperty(NS + has_date_of_surgery);
		DatatypeProperty datPropSurgeon = ontModel.getDatatypeProperty(NS + has_surgeon);
		DatatypeProperty datPropAssistant = ontModel.getDatatypeProperty(NS + has_assistant);
		DatatypeProperty datPropIsisDevice = ontModel.getDatatypeProperty(NS + has_device);
		indv.addProperty(datPropSurgeryDate, dateOfSurgery);
		indv.addProperty(datPropSurgeon, surgeon);
		indv.addProperty(datPropAssistant, assistant);
		indv.addProperty(datPropIsisDevice, device);
		addStatement(documentUri, NS +  has_data_item, indivUri);
		addStatement(indivUri, NS +  data_item_of, documentUri);
		saveNewOWLFile(); 
	}

	/**
	 * @author romap1
	 * @param diagnosisUri
	 * @param label
	 * @param documentUri
	 */
	public void addDiagnosis(String diagnosisUri, String label,String documentUri) {
		String indivUri= createNewIndividual(diagnosisUri, label);
		//Individual indv = ontModel.getIndividual(indivUri);
		addStatement(documentUri, NS + has_data_item, indivUri);
		addStatement(indivUri, NS + data_item_of, documentUri);

	}
	
	public void addProcessObservationDatum (String processUri, String label,String timestamp, String comment, String documentUri) {
		String processObservationDatumUri = NS + "IOMO_0000287";
		String observationIndvUri= createNewIndividual(processObservationDatumUri, "Observation");
		Individual indv = ontModel.getIndividual(observationIndvUri);
		addStatement(documentUri, NS + has_data_item, observationIndvUri);
		addStatement(observationIndvUri, NS + data_item_of, documentUri);
		
		DatatypeProperty datPropTimeStamp = ontModel.getDatatypeProperty( NS + has_timestamp);
		indv.addProperty(datPropTimeStamp, timestamp);
		if(!comment.equals("")){
			DatatypeProperty datPropComment = ontModel.getDatatypeProperty( NS + has_comment);
			indv.addProperty(datPropComment, comment);
		}
		
		
		String indivUri= createNewIndividual(processUri, label);
		addStatement(observationIndvUri, NS + documents_process, indivUri);
		addStatement(indivUri, NS + documented_in, observationIndvUri);
		
		
		
		
		saveNewOWLFile(); 
	}

	/**
	 * @author romap1
	 * @return
	 */
	public ObservableList<String> getPostoperativeDisposition() {
		String abnormalNervousSystemPhysiologyHP = "http://purl.obolibrary.org/obo/HP_0012638";
		String postoperativeDisposition = "IOMO_0000381";
		String centralNervousSystemDisease = "http://purl.obolibrary.org/obo/MONDO_0002602";
		//add Subclasses of postoperative Disposition
		
		Map<String, String> map = getSubclasses(NS + postoperativeDisposition);
		//add SubSubclass of postoperative Disposition
		Map<String, String> map2 = getSubclasses(abnormalNervousSystemPhysiologyHP);
		Map<String, String> map3 = getSubclasses(centralNervousSystemDisease);
		ObservableList<String> list = FXCollections.observableArrayList();

		OntClassMap ontClassMap = new OntClassMap();
		//add a stand alone subsubclass of postoperative Disposition
		//list.add(ontClassMap.getLabelFromURI(cerebrospinalFluidLeak));

		list.addAll(map.keySet());
		list.addAll(map2.keySet());
		list.addAll(map3.keySet());
		//remove Entities without German label
		list.remove(null);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		//add empty String, so that the user can undo his selection
		list.add("");
		return list;
	}


	/**
	 * Method to create a new rdf triple statement
	 * @author neues4
	 * @param subject the subject (e.g.patient)
	 * @param property the property (e.g. has document)
	 * @param object the object (e.g. iom document)
	 */
	public void addStatement(String subject, String property, String object ) {

		Statement stmt = ontModel.createStatement
				(     
						ontModel.createResource( subject ), 
						ontModel.createProperty( property ), 
						ontModel.createResource( object ) 
						);

		ontModel.add( stmt );

	}


	/**
	 * Method to save the edited ontology into an owl file
	 * @author neues4
	 */
	public void saveNewOWLFile() {
		StringWriter sw = new StringWriter();
		ontModel.write(sw, "RDF/XML-ABBREV");
		String owlCode = sw.toString();
		File file = new File("myModel.owl");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(owlCode);
			fw.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}


	/**
	 * Method to return a specific value of a property of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @param propURI the URI of the property (for example the URI of "has first name")
	 * @return 
	 */
	public RDFNode getPropertyOfIndividual(String indvURI, String propURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty(propURI);
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the first name of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @return the first name of the individual
	 */
	public RDFNode getFirstName(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000253");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the surname of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @return the surname of the individual
	 */
	public RDFNode getSurname(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000254");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the PID of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @return the PID of the individual
	 */
	public RDFNode getPID(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000251");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the FID of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @return the FID of the individual
	 */
	public RDFNode getFID(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000252");
		return indv.getPropertyValue(property);
	}                              

	/**
	 * Method to get the birthday of a specific individual
	 * @author neues4
	 * @param indvURI the URI of the individual
	 * @return the birthday of the individual
	 */
	public RDFNode getBirthday(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the case number of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the case number of the individual
	 */
	public RDFNode getCaseNumber(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000250");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the birthday of a specific individual as String
	 * @author romap1
	 * @param indvURI
	 * @return the birthday of the individual as String
	 */ 
	public String getBirthdayValue(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		RDFNode node  = indv.getPropertyValue(ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255"));
		return node.toString().replace("^^" + XSDDatatype.XSDdate.getURI(), "").toString();
	}

	/**
	 * Method to get all entities to be shown in the category dropdown of the gui
	 * @author neues4
	 * @return a hashmap of all entities to be shown
	 */
	public Map<String, String> getAllEntitiesToBeShown() {
		OntClass mepFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000400");
		OntClass sepFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000401");
		OntClass vepFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000403");
		OntClass aepFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000402");
		OntClass eegFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000231");
		OntClass reflexFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000234");
		OntClass dwaveFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000387");
		OntClass emgFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000370");
		OntClass mappingFinding = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000376");
		OntClass cbtMeasurement = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000371");
		//OntClass dwaveMeasurement = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000369");
		OntClass mappingMeasurement = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000373");
		//OntClass sepMeasurement = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000242");

		//OntClass vepMeasurement = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000244");
		OntClass anesthesiaProcess = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000159");
		OntClass surgicalProcess = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000058");
		//OntClass humanAction = ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#manipulating_action_by_human");
		OntClass technicalIssues = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000154");
		//OntClass iomProcess = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000057");
		OntClass gridPositioning = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000064");
		String iOMEnd = "IOMO_0000462";
		OntClass iOMEndClass = ontModel.getOntClass(NS + iOMEnd);
		String other = "IOMO_0000460";
		OntClass otherClass = ontModel.getOntClass(NS + other);


		HashMap<String, String> showEntityMap = new HashMap<>();

		showEntityMap.put(mepFinding.getLabel("DE"), mepFinding.getURI());
		showEntityMap.put(sepFinding.getLabel("DE"), sepFinding.getURI());
		showEntityMap.put(vepFinding.getLabel("DE"), vepFinding.getURI());
		showEntityMap.put(aepFinding.getLabel("DE"), aepFinding.getURI());
		showEntityMap.put(eegFinding.getLabel("DE"), eegFinding.getURI());
		showEntityMap.put(reflexFinding.getLabel("DE"), reflexFinding.getURI());
		showEntityMap.put(dwaveFinding.getLabel("DE"), dwaveFinding.getURI());
		showEntityMap.put(emgFinding.getLabel("DE"), emgFinding.getURI());
		showEntityMap.put(mappingFinding.getLabel("DE"), mappingFinding.getURI());
		showEntityMap.put(cbtMeasurement.getLabel("DE"), cbtMeasurement.getURI());
		//showEntityMap.put(dwaveMeasurement.getLabel("DE"), dwaveMeasurement.getURI());
		showEntityMap.put(mappingMeasurement.getLabel("DE"), mappingMeasurement.getURI());
		//showEntityMap.put(sepMeasurement.getLabel("DE"), sepMeasurement.getURI());

		//showEntityMap.put(vepMeasurement.getLabel("DE"), vepMeasurement.getURI());
		showEntityMap.put(anesthesiaProcess.getLabel("DE"), anesthesiaProcess.getURI());
		showEntityMap.put(surgicalProcess.getLabel("DE"), surgicalProcess.getURI());
		//showEntityMap.put(humanAction.getLabel("DE"), humanAction.getURI());
		showEntityMap.put(technicalIssues.getLabel("DE"), technicalIssues.getURI());
		//showEntityMap.put(iomProcess.getLabel("DE"), iomProcess.getURI());
		showEntityMap.put(gridPositioning.getLabel("DE"), gridPositioning.getURI());
		showEntityMap.put(iOMEndClass.getLabel("DE"), iOMEndClass.getURI());
		showEntityMap.put(otherClass.getLabel("DE"), otherClass.getURI());

		return showEntityMap;
	}


	/**
	 * Returns a Hasmap with Measurements in the intraoperative Neuromonitoring that are documented with a value. 
	 * @return a Hashmap with label as key and uri as value
	 */
	public HashMap<String, String> getAllMeasurementsWithValues(){
		OntClass tesMepMeasurement = ontModel.getOntClass(NS + "IOMO_0000378");
		OntClass dcsMepMeasurement = ontModel.getOntClass(NS + "IOMO_0000238");
		OntClass aepMeasurement = ontModel.getOntClass(NS + "IOMO_0000240");

		HashMap<String, String> map = new HashMap<>();
		map.put(tesMepMeasurement.getLabel("DE"), tesMepMeasurement.getURI());
		map.put(dcsMepMeasurement.getLabel("DE"), tesMepMeasurement.getURI());
		map.put(aepMeasurement.getLabel("DE"), aepMeasurement.getURI());
		return map;

	} 



	/**
	 * Method to get all the subclasses of a given class
	 * @param classURI the uri of the class
	 * @return a hash map with the german label as key and the uri as value
	 */
	public Map<String, String> getSubclasses(String classURI) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		HashMap<String, String> subClassMap = new HashMap<>();
		Iterator iter = ontClass.listSubClasses();
		while (iter.hasNext()) {
			OntClass sub = (OntClass) iter.next();
			subClassMap.put(sub.getLabel("DE"), sub.getURI());
		}
		return subClassMap;
	}

	/**
	 * 
	 * @author romap1
	 * @param classURI
	 * @return a hash map with the uri as key and the label as value
	 */
	public Map<String, String> getSubclassMapForLabel(String classURI) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		HashMap<String, String> subClassMap = new HashMap<>();
		Iterator iter = ontClass.listSubClasses();
		while (iter.hasNext()) {
			OntClass sub = (OntClass) iter.next();
			subClassMap.put(sub.getURI(), sub.getLabel("DE"));
		}
		return subClassMap;
	}


	/**
	 * Method to create a new instance of iom document in the ontology
	 * @author neues4
	 * @param indvLabel the label for the new individual (e.g. iom document 1)
	 * @return the URI of the newly created individual
	 */
	public String createNewIOMDocument(String indvLabel) {
		OntClass ontClass = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000277");
		//Individual indv = ontClass.createIndividual(createNewURI(indvLabel));
		Individual indv = ontClass.createIndividual( NS + getId());
		indv.addLabel(indvLabel, "EN");
		return indv.getURI();
	}

	/**
	 * Method to create a new instance of miliampere in the ontology
	 * @author neues4
	 * @param indvLabel the label for the new individual (e.g. miliampere 1)
	 * @return the URI of the newly created individual
	 */
	public String createNewMiliampere(String indvLabel) {
		OntClass ontClass = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000268");
		//Individual indv = ontClass.createIndividual(createNewURI(indvLabel));
		Individual indv = ontClass.createIndividual(NS + getId());
		indv.addLabel(indvLabel, "DE");
		return indv.getURI();
	}

	/**
	 * Method add the dataproperty "has measurement unit" to the miliampere individual and add the value (e.g. 120)
	 * @author neues4
	 * @param miliampere the label of the miliampere instance we want to add a new value to
	 * @param value the value of miliampere (e.g. 120)
	 */
	public void addPropertiesToMiliampere(String miliampere, String value) {
		Individual indv = ontModel.getIndividual(miliampere);
		//has_measurement_value = IAO_0000004
		DatatypeProperty miliAmpValue = ontModel.getDatatypeProperty("http://purl.obolibrary.org/obo/IAO_0000004");
		indv.addProperty(miliAmpValue, value);

	}

	/**
	 * Method to create a new instance of milisecond in the ontology
	 * @author neues4
	 * @param indvLabel the label for the new individual (e.g. milisecond 1)
	 * @return the URI of the newly created individual
	 */
	public String createNewMilisecond(String indvLabel) {
		OntClass ontClass = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000384");
		Individual indv = ontClass.createIndividual(createNewURI(indvLabel));
		indv.addLabel(indvLabel, "EN");
		return indv.getURI();
	}

	/**
	 * Method add the dataproperty "has measurement unit" to the milisecond individual and add the value (e.g. 6)
	 * @author neues4
	 * @param milisecond the label of the milisecond instance we want to add a new value to
	 * @param value the value of milisecond (e.g. 6)
	 */
	public void addPropertiesToMilisecond(String miliampere, String value) {
		Individual indv = ontModel.getIndividual(miliampere);
		DatatypeProperty miliSecValue = ontModel.getDatatypeProperty("http://purl.obolibrary.org/obo/IAO_0000004");
		indv.addProperty(miliSecValue, value);

	}


	/**
	 * Method to get all muscles of the ontology
	 * @author neues4
	 * @return a hash map with all the muscles (label, URI)
	 */
	public Map<String, String> getAllMuscles() {
		OntClass muscle = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000227");
		OntClass ringMuscle = ontModel.getOntClass("http://purl.obolibrary.org/obo/FMA_46841");
		HashMap<String, String> muscleMap = new HashMap<>();
		Iterator surgeryIter = muscle.listSubClasses();
		while (surgeryIter.hasNext()) {
			OntClass sub = (OntClass) surgeryIter.next();
			//muscleMap.put(sub.getLabel("DE"), sub.getURI());
			Iterator test = sub.listSubClasses();
			while (test.hasNext()) {
				OntClass subTest = (OntClass) test.next();
				muscleMap.put(subTest.getLabel("DE"),subTest.getURI());
			}
		}
		muscleMap.put(ringMuscle.getLabel("DE"), ringMuscle.getURI());
		return muscleMap;
	}

	/**
	 * Method to get all the types of baseline of the ontology
	 * @author neues4
	 * @return a hashmap with the types of baselines (label, URI)
	 */
	public Map<String, String> getBaselines() {
		HashMap<String, String> baselineMap = new HashMap<>();
		OntClass ssepBaseline = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000243");
		OntClass tesMepBaseline = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000379");
		OntClass dcsMepBaseline = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000239");
		OntClass aepBaseline = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000241");
		OntClass vepBaseline = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000245");
		baselineMap.put(ssepBaseline.getLabel("DE"), ssepBaseline.getURI());
		baselineMap.put(tesMepBaseline.getLabel("DE"), tesMepBaseline.getURI());
		baselineMap.put(dcsMepBaseline.getLabel("DE"), dcsMepBaseline.getURI());
		baselineMap.put(aepBaseline.getLabel("DE"), aepBaseline.getURI());
		baselineMap.put(vepBaseline.getLabel("DE"), vepBaseline.getURI());
		return baselineMap;
	}

	//Returns a Map of all NsPrefixes
	@SuppressWarnings("exports")
	public Map<String, String> test() {
		//return ontModel.listClasses();
		return ontModel.getNsPrefixMap();

	}

	/**
	 * Returns the Label of a given OntClassUri
	 * @author romap1
	 * @param uri
	 * @return Label of a given OntClass
	 */
	public String getOntClassName(String uri) {
		OntClass ontClass = ontModel.getOntClass(uri);
		String label = ontClass.getLabel("DE");
		return label;
	}






}
